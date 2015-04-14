package game.Score;

import processing.core.PApplet;


public class Main extends PApplet
{
	
	ScoreSearcher searcher;
	int x;
	int y;
	
	public void setup()
	{
		searcher = new ScoreSearcher();
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
		for( int i = 0; i < searcher.scoreArray.length; i ++)
		{
			if(searcher.scoreArray[i] != null)
			{
				text(searcher.nameArray[i] + "\t" + searcher.scoreArray[i] , x, y);
			}
			
			y += 20;
		}

	}


}