package finalProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RunProject {
	public static void main(String[] args) throws SQLException{
		//System.out.println("hello");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/music?useUnicode=true&characterEncoding=utf8&rewriteBatchedStatements=true&useConfigs=maxPerformance&useSSL=false&serverTimezone=UTC", "root", "happify132413");
			Statement stmt = conn.createStatement();
			String query = "SELECT * from lyrics L WHERE L.word = 'understand';";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()){
				String someString = rs.getString("word");
				System.out.println("recieved string " + someString);
			}
			//System.out.println(rs.getString(1));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
