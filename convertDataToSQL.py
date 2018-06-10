#Script to build database from million song files
#Usage: you need to have mysql installed and create a database
#within it called music. Then on line 37 you need to change the 
#username and password to your own.
#then type python ./convertDataToSQL.py train.txt test.txt output.db
#This should populate the database and may take a while.
#You can always run it for a period of time and stop whenever you want.
import os
import sys
import mysql.connector
from mysql.connector import Error


def encode_string(s):
    return "'" + s.replace("'", "''") + "'"



if __name__ == '__main__':

    # params
    trainf = sys.argv[1]
    testf = sys.argv[2]
    outputf = sys.argv[3]

    # sanity checks
    if not os.path.isfile(trainf):
        print 'ERROR: %s does not exist.' % trainf
        sys.exit(0)
    if not os.path.isfile(testf):
        print 'ERROR: %s does not exist.' % testf
        sys.exit(0)
    if os.path.exists(outputf):
        print 'ERROR: %s already exists.' % outputf
        sys.exit(0)

    conn = mysql.connector.connect(host = 'localhost', database='music', user='root', password='happify132413')
    cursor = conn.cursor()
    cursor.execute("CREATE TABLE words (word VARCHAR(300) PRIMARY KEY, ROWID int)")
    q = "CREATE TABLE lyrics (track_id VARCHAR(100),"
    q += " mxm_tid INT,"
    q += " word VARCHAR(300),"
    q += " count INT,"
    q += " is_test INT,"
    q += " FOREIGN KEY(word) REFERENCES words(word));"
    cursor.execute(q)
    conn.commit()

    # get words, put them in the words table
    f = open(trainf, 'r')
    for line in f.xreadlines():
        if line == '':
            continue
        if line[0] == '%':
            topwords = line.strip()[1:].split(',')
            f.close()
            break
    fakeRow = 1
    for w in topwords:
        try:
            q = "INSERT INTO words (word, ROWID) VALUES("
            q += encode_string(w) + ", {});".format(fakeRow)
            print("Full call: {}".format(q))
            cursor.execute(q)
            conn.commit()
            #newCursor.close()
            fakeRow += 1
            #sys.exit(0)
        except Error as E:
            print("something is wrong")

    # we put the train data in the dataset
    f = open(trainf, 'r')
    cnt_lines = 0
    for line in f.xreadlines():
        print("running still...")
        if line == '' or line.strip() == '':
            continue
        if line[0] in ('#', '%'):
            continue
        lineparts = line.strip().split(',')
        tid = lineparts[0]
        mxm_tid = lineparts[1]
        for wordcnt in lineparts[2:]:
            wordid, cnt = wordcnt.split(':')
            q = "INSERT INTO lyrics"
            q += " SELECT '" + tid + "', " + mxm_tid + ", "
            q += " words.word, " + cnt + ", 0"
            q += " FROM words WHERE words.ROWID=" + wordid + ";"
            cursor.execute(q)
            #cursor.close()
            conn.commit()
            #conn.close()
            #sys.exit(0)
        # verbose
        cnt_lines += 1
        if cnt_lines % 15000 == 0:
            print 'Done with %d train tracks.' % cnt_lines
            #cursor.commit()
    f.close()
    #cursor.commit()
    print 'Train lyrics added.'

    # we put the test data in the dataset
    # only difference from train: is_test is now 1
    f = open(testf, 'r')
    cnt_lines = 0
    for line in f.xreadlines():
        print("running...")
        if line == '' or line.strip() == '':
            continue
        if line[0] in ('#', '%'):
            continue
        lineparts = line.strip().split(',')
        tid = lineparts[0]
        mxm_tid = lineparts[1]
        for wordcnt in lineparts[2:]:
            wordid, cnt = wordcnt.split(':')
            q = "INSERT INTO lyrics"
            q += " SELECT '" + tid + "', " + mxm_tid + ", "
            q += " words.word, " + cnt + ", 1"
            q += " FROM words WHERE words.ROWID=" + wordid + ";"
            cursor.execute(q)
            conn.commit()
        # verbose
        cnt_lines += 1
        if cnt_lines % 15000 == 0:
            print 'Done with %d test tracks.' % cnt_lines
            #cursor.commit()
    f.close()
    #cursor.commit()
    print 'Test lyrics added.'

    # create indices
    q = "CREATE INDEX idx_lyrics1 ON lyrics ('track_id')"
    cursor.execute(q)
    q = "CREATE INDEX idx_lyrics2 ON lyrics ('mxm_tid')"
    cursor.execute(q)
    q = "CREATE INDEX idx_lyrics3 ON lyrics ('word')"
    cursor.execute(q)
    q = "CREATE INDEX idx_lyrics4 ON lyrics ('count')"
    cursor.execute(q)
    q = "CREATE INDEX idx_lyrics5 ON lyrics ('is_test')"
    cursor.execute(q)
    conn.commit()
    #cursor.commit()
    print 'Indices created.'

    # close output SQLite connection
    cursor.close()
    conn.close()