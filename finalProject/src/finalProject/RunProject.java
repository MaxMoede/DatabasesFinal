package finalProject;
import java.io.*;
import java.net.*;
import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.LayeredWordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.PixelBoundryBackground;
import com.kennycason.kumo.font.FontWeight;
import com.kennycason.kumo.font.KumoFont;
import com.kennycason.kumo.font.scale.SqrtFontScalar;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import com.kennycason.kumo.palette.ColorPalette;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.BasicConfigurator;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.activation.DataSource;

import java.io.InputStream;

import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.font.scale.SqrtFontScalar;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import com.kennycason.kumo.palette.ColorPalette;

public class RunProject{
	private String query;
	public RunProject(){
		
	}

	public void runQuery(int numWords, String photoName, int most, int lowRange, int highRange, String similarTo) throws SQLException, IOException {
	
		BasicConfigurator.configure();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/music?useUnicode=true&characterEncoding=utf8&rewriteBatchedStatements=true&useConfigs=maxPerformance&useSSL=false&serverTimezone=UTC", "root", "happify132413");
			Statement stmt = conn.createStatement();
		
			if (most == 1){
				query = String.format("SELECT SUM(count) as count, word from lyrics where word LIKE '%%%s%%' AND CHAR_LENGTH(word) >= %d and CHAR_LENGTH(word) <= %d GROUP BY word ORDER BY count DESC LIMIT %d;", similarTo, lowRange, highRange, numWords);
			} else {
				query = String.format("SELECT SUM(count) as count, word from lyrics where word LIKE '%%%s%%' AND CHAR_LENGTH(word) >= %d and CHAR_LENGTH(word) <= %d GROUP BY word ORDER BY count ASC LIMIT %d;", similarTo, lowRange, highRange, numWords);
			}
			ResultSet rs = stmt.executeQuery(query);
			List<WordFrequency> theWords = new ArrayList<WordFrequency>();
			while (rs.next()){
				String foundWord = rs.getString("word");
				String someInt = rs.getString("count");
				int convInt = Integer.parseInt(someInt);
				theWords.add(new WordFrequency(foundWord, convInt));
				System.out.println("recieved string " + foundWord);
				System.out.println("received int: " + someInt);
			}
			final FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
		
			if (photoName.equals("pi")){
				final Dimension dimension = new Dimension(384, 384);
				final WordCloud wordCloud = new WordCloud(dimension, CollisionMode.PIXEL_PERFECT);
				wordCloud.setPadding(2);
				wordCloud.setBackground(new PixelBoundryBackground(new FileInputStream("resources/pi.png")));
				wordCloud.setColorPalette(new ColorPalette(new Color(0x800000), new Color(0xEC8117), new Color(0xE65738), new Color(0xD1DA30), new Color(0xFF7872), new Color(0xFFFFFF)));
				wordCloud.setFontScalar(new SqrtFontScalar(10, 40));
				wordCloud.build(theWords);
				wordCloud.writeToFile("NewPiWordCloud.png");
			} else if (photoName.equals("mountain")){
				final Dimension dimension = new Dimension(1499, 555);
				final WordCloud wordCloud = new WordCloud(dimension, CollisionMode.PIXEL_PERFECT);
				wordCloud.setPadding(2);
				wordCloud.setBackground(new PixelBoundryBackground(new FileInputStream("resources/mountain.png")));
				wordCloud.setColorPalette(new ColorPalette(new Color(0x20B2AA), new Color(0xD5E7BA), new Color(0x9ACD32), new Color(0xF5DEB3), new Color(0xFFFFC6), new Color(0xA0DB8E)));
				wordCloud.setFontScalar(new SqrtFontScalar(10, 40));
				wordCloud.build(theWords);
				wordCloud.writeToFile("NewMountainWordCloud.png");
			} else if (photoName.equals("whale")){
				final Dimension dimension = new Dimension(990, 618);
				final WordCloud wordCloud = new WordCloud(dimension, CollisionMode.PIXEL_PERFECT);
				wordCloud.setPadding(2);
				wordCloud.setBackground(new PixelBoundryBackground(new FileInputStream("resources/whale.png")));
				wordCloud.setColorPalette(new ColorPalette(new Color(0x4055F1), new Color(0x408DF1), new Color(0x40AAF1), new Color(0x40C5F1), new Color(0x40D3F1), new Color(0xFFFFFF)));
				wordCloud.setFontScalar(new SqrtFontScalar(10, 40));
				wordCloud.build(theWords);
				wordCloud.writeToFile("NewWhaleWordCloud.png");
			} else if (photoName.equals("thumbs up")){
				final Dimension dimension = new Dimension(570, 597);
				final WordCloud wordCloud = new WordCloud(dimension, CollisionMode.PIXEL_PERFECT);
				wordCloud.setPadding(2);
				wordCloud.setBackground(new PixelBoundryBackground(new FileInputStream("resources/thumbsUp.png")));
				wordCloud.setColorPalette(new ColorPalette(new Color(0x4055F1), new Color(0x408DF1), new Color(0x40AAF1), new Color(0x40C5F1), new Color(0x40D3F1), new Color(0xFFFFFF)));
				wordCloud.setFontScalar(new SqrtFontScalar(10, 40));
				wordCloud.build(theWords);
				wordCloud.writeToFile("NewThumbsUpWordCloud.png");
			} else {
				final Dimension dimension = new Dimension(600, 600);
				final WordCloud wordCloud = new WordCloud(dimension, CollisionMode.PIXEL_PERFECT);
				wordCloud.setPadding(2);
				wordCloud.setBackground(new CircleBackground(300));
				wordCloud.setColorPalette(new ColorPalette(new Color(0x9CFDFF), new Color(0xfff68f), new Color(0x20B2AA), new Color(0xF08080), new Color(0xA6CCFF), new Color(0xD7B8FF)));
				wordCloud.setFontScalar(new SqrtFontScalar(10, 40));
				wordCloud.build(theWords);
				wordCloud.writeToFile("NewCircleWordCloud.png");
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static InputStream getInputStream(final String path) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
    }
}
