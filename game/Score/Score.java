package game.Score;

import processing.core.PApplet;
import game.musicLoader.*;


public class Score extends PApplet
{
	
	ScoreSearcher searcher;
	int x;
	int y;
	musicLoader loader;
	
	public void setup()
	{
		searcher = new ScoreSearcher();
		loader = new musicLoader();
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
		printScores();
	}
	
	public void keyPressed()
	{
		if(key == 'a')
		{
			searcher.loadScores();
			printScores();
		}
	}
	
	public void printScores()
	{
		y = 20;
		for( int i = 0; i < searcher.entryArray.length; i ++)
		{
			if(searcher.entryArray[i] != null)
			{
				text(searcher.entryArray[i] , x, y);
			}
			
			y += 20;
		}

	}


}