package game.Score;

import processing.core.PApplet;
import game.musicLoader.Loader;


public class Score extends PApplet
{
	Loader musicloader;
	ScoreSearcher searcher;
	int x;
	int y;
	
	public void setup()
	{
		searcher = new ScoreSearcher();
		musicloader = new Loader();
		size(800,600);
		background(0);
		//searcher.loadScores();
		//searcher.addScores();
		x = 20;
		y = 20;
	}
	
	public void draw()
	{
		
		searcher = new ScoreSearcher();
		searcher.loadScores();
		searcher.printScores();
	}
	
	public void keyPressed()
	{
		if(key == 'a')
		{
			searcher.loadScores();
			searcher.printScores();
		}
	}


}