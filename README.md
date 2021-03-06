# Databases Final
# Authors: Max Moede and David Twyman

**Description:** 
This program generates word cloud images based on different sql queries from the 'Million Song Database' ---> https://labrosa.ee.columbia.edu/millionsong/ 
The database is a freely-available collection of audio metadata for a million 
music tracks. We decided to focus on the lyrics of songs, so each word cloud is an aggregate of lyrics from the million songs. 

The musiXmatch dataset is the lyrics of those million songs, and since they did 
not have an accessible mysql database, we decided to convert the files into a database. Now anyone can create their own database from the text files given.

We used the Kumo library ---> https://github.com/kennycason/kumo to create the wordclouds, and set it up so that the word clouds generated are highly customizable. 

The project uses maven as well.

**INTERNALS DESCRIPTION:**
For least/most popular words: We used "ORDER BY ASC/DESC" for our query

For specifiying length range: We used "CHAR_LENGTH(word) >= {low range here} AND CHAR_LENGTH(word) <= {high range here}"

For Specifying word similarity: We used "word LIKE '%{some string}%'" which returns words that include the substring given.

We got the total word usage by taking the SUM(count) of the words and grouping by each word.



**How to run the project:**
1. Open up the python script and follow the directions at the top of the file.
2. Run the script, populate your database.
3. The java part of the project is built with eclipse, so you need to open the project with eclipse.
The project is built in the folder 'finalProject'.
4. Click the green arrow in eclipse to run the project, the gui should open. 
5. Each word cloud generated can be found in the finalProject folder.

**LOG:** We worked together during each phase of the build.

6/1/18: Created the python script to build the database. This took about 
4 hours of work.

6/4/18: Started building the java project, worked about 3 hours learning 
how to use guis and various things.

6/6/18: Continued working on project, brainstormed ideas for queries. About 3 hours.

6/8/18: Built the gui and connected everything together. 4 hours

6/9/18: Final touches, made the powerpoint. 2 hours 
