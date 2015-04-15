/*
package game.Score;

import processing.core.PApplet;
import game.musicLoader.Loader;


public class Score
{
	Loader musicloader;
	ScoreSearcher searcher;
	int x;
	int y;
	
	public void setup()
	{
		searcher = new ScoreSearcher();
		size(800,600);
		background(0);
		//searcher.loadScores();
		searcher.addScores("song1", "12345");
		x = 20;
		y = 20;
	}
	
	public void draw()
	{
		
		searcher = new ScoreSearcher();
		searcher.loadScores("song1");
		//searcher.printScores();
	}
	
	public void keyPressed()
	{
		if(key == 'a')
		{
			searcher.loadScores("song1");
			//searcher.printScores();
		}
	}


}
*/