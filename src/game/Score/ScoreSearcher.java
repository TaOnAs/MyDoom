package game.Score;

import java.sql.*;

public class ScoreSearcher {
	
	private String songTitle;
	private String name;
	private String position;
	private String score;
	//private String path;
	
	//database variables
	private Connection conn;
	private ResultSet r;
	private PreparedStatement pstatm;
	private Statement statm;
	private String sql;
	
	//function to load previous scores for a song from the database
	public void loadScores()
	{
		conn = null;
		//PreparedStatement s = null;
		r = null;
		try
		{
			Class.forName("org.sqlite.JDBC");
			setSongTitle("song1");
			//String path = this.getClass().getResource("MyDoom.db").getPath();	//gets file path for the database
			conn = DriverManager.getConnection("jdbc:sqlite:/Users/Mark/Documents/workspace/MyDoom/MyDoom.db");	///Users/Mark/Documents/workspace/MyDoom/MyDoom.db
			sql = "SELECT * FROM Score WHERE title = ? ORDER BY score DESC LIMIT 10";
			pstatm = conn.prepareStatement(sql);
			pstatm.setString(1, songTitle);
			r = pstatm.executeQuery();
			
			System.out.println("Highscores for this song: " + songTitle);
			while (r.next())
			{
				setScore(r.getString("score"));
				setName(r.getString("name"));
				System.out.println(name + "\t" + score);
			}
			conn.close();
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
	
	
	public void addScores()
	{
		conn = null;
		statm = null;
		setScore("3000000");
		setName("test");
		setSongTitle("song1");

		try
		{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:/Users/Mark/Documents/workspace/MyDoom/MyDoom.db");
			statm = conn.createStatement();
			sql = "INSERT INTO Score (title, name, score) Values ( ?, ? ,?)";
			pstatm = conn.prepareStatement(sql);
			pstatm.setString(1, songTitle);
			pstatm.setString(2, name);
			pstatm.setString(3, score);
			pstatm.executeUpdate();
			
			conn.close();
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Could not load driver");
			e.printStackTrace();
		}
		catch(SQLException e)
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
		System.out.println("Record created" + sql);
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getSongTitle() {
		return songTitle;
	}
	public void setSongTitle(String songTitle) {
		this.songTitle = songTitle;
	}
}
