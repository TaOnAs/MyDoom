package src.game.Score;

import processing.core.PApplet;


public class Main extends PApplet
{
	
	ScoreSearcher searcher;
	
	public void setup()
	{
		searcher = new ScoreSearcher();
		searcher.loadScores();
	}
	
	public void draw()
	{

	}
	
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
			
		System.out.println("I am on git now");
	}

}