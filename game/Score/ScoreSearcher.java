package game.Score;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import processing.core.PApplet;

public class ScoreSearcher{
	
	//variables to use as parameters for searching the database
	private String songTitle;
	private String name;
	private String position;
	public String score;
	private String path;
	
	//database variables
	private Connection conn;
	private ResultSet r;
	private PreparedStatement pstatm;
	private String sql;
	private Scanner user_input;
	
	public String[] entryArray = new String[10];
	public String[] scoreArray = new String[10];

	public PApplet parent;
	
	public ScoreSearcher(PApplet mainApplet)
	{
		this.parent = mainApplet;
	}

	//function to load the previous top ten scores for a specific song from the database
	public void loadScores(String _song)
	{
		conn = null;
		r = null;
		try
		{
			Class.forName("org.sqlite.JDBC");
			setSongTitle(_song);
			path = this.getClass().getResource("MyDoom.db").getPath();	//gets file path for the database 
			System.out.println(path);
			conn = DriverManager.getConnection("jdbc:sqlite:" + path);	//Connects to the database ///Users/Mark/Documents/workspace/MyDoom/MyDoom.db
			sql = "SELECT * FROM Score WHERE title = ? ORDER BY score DESC LIMIT 10";	//sql statement using a prepared statement
			pstatm = conn.prepareStatement(sql);
			pstatm.setString(1, songTitle);	//inserts the variable songTitle in place of the ? in the sql statement
			r = pstatm.executeQuery();		//query the database
			
			System.out.println("Highscores for this song: " + songTitle);
			
			int i = 0;
			while (r.next())	//while there are entries left that have been returned print out the data
			{
				
				setScore(r.getString("score"));
				setName(r.getString("name"));
				//entry = score + "\t .................. \t" + name;
				entryArray[i] = name;
				scoreArray[i] = score;
				System.out.println(name + "\t" + score);
				//text("test", 20, 20);
				i++;
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
	
	//Function to add new scores to the database
	public void addScores(String _song, String _score)
	{
		conn = null;
		setScore(_score);
		setSongTitle(_song);
		
		user_input = new Scanner(System.in);
		System.out.println("Enter Your Name");
		name = user_input.next();
		
		try
		{
			Class.forName("org.sqlite.JDBC");
			path = this.getClass().getResource("MyDoom.db").getPath();	//gets file path for the database 
			System.out.println(path);
			conn = DriverManager.getConnection("jdbc:sqlite:" + path);									//Connects to the database
			//sql = "INSERT INTO Score (title,name,score) VALUES ('testSong', 'mark', '2456');
			sql = "INSERT INTO Score (title, name, score) Values ( ?, ? ,?)";	//sql insert using a prepared statement
			pstatm = conn.prepareStatement(sql);
			pstatm.setString(1, songTitle);	//inserts the songTitle variable in place of the first ? in the sql
			pstatm.setString(2, name);		//inserts the name variable in place of the second ? in the sql
			pstatm.setString(3, score);		//inserts the score variable in place of the third ? in the sql
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
		System.out.println("Record created");
	}

	
	//getters and setters for variables
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