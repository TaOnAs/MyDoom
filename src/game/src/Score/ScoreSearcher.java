package game.src.Score;

import java.sql.*;

public class ScoreSearcher {
	
	//private String songTitle = "song1";
	
	//function to load previous scores for a song from the database
	public void loadScores()
	{
		Connection conn = null;
		PreparedStatement s = null;
		ResultSet r = null;
		
		try
		{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:/Users/Mark/Documents/workspace/MyDoom/MyDoom.db");
			s = conn.prepareStatement("SELECT * FROM Score");
			r = s.executeQuery();
			
			while (r.next())
			{
				System.out.println(r.getString("pos"));
				System.out.println(r.getString("name"));
				System.out.println(r.getString("score"));
				System.out.println(r.getString("title"));
			}
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("Could not load driver");
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			System.out.println("SQL ERROR OCCURED");
			e.printStackTrace();
		}
		finally
		{
			if(conn != null)
			{
				try
				{
					conn.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}
