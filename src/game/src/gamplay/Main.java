package game.src.gamplay;

import java.util.ArrayList;
import processing.core.PApplet;

public class Main extends PApplet
{
	int noArrows; //The current amount of arrows on screen
	int maxNoArrows; //Maximum number of arrows that will be made
	ArrayList<Arrow> Arrows;
	
	public void setup()
	{
		size(800, 580);
		  
		Arrows = new ArrayList<Arrow>();
		
		frameRate(60);
		
	    noArrows = 0;
	    maxNoArrows = 4;
	}

	public void draw()
	{
	    background(255);
	    //Gameplay()
	    
	    if(noArrows != maxNoArrows)
	    {
	        Arrows.add(new Arrow(200, 20, 200, 1, noArrows));
	        noArrows += 1; //Increments number of arrows
	    }
	    
	    if(noArrows == maxNoArrows)
	    {
	    	for(int i = 0; i < noArrows; i++)
	    	{
	    		Arrows.get(i).arrowMovement(2);
	    	}
	    }
	}

	/*void Gameplay()
	{
	    if(noArrows != 1)
	    {
	       Arrows.add(new Arrow(200, 20, 200, 1));
	       noArrows += 1; //Increments number of arrows
	    }
	}*/
	
	class Arrow
	{
		float arrowLen; //Length of arrow
		float arrowHei; //Height of arrow
		float arrowCurrXPos; //Arrow X position
		float arrowCurrYPos; //Arrow Y position
		int k; //The position of arrow key
	  
	    public Arrow(float len, float hei, float xPos, int k, int noInQueue)
	    {
	        float awayFromBorder = xPos;
	    
	        //Sets arrow dimenision
	        arrowLen = len;
	        arrowHei = hei;
	    
	        //Sets arrow
	        arrowCurrYPos = (height + arrowHei) - noInQueue;
	    
	        //Sets arrows in their right X positions depending on which key is used
	        switch(k)
	        {
	        	case 1:
	        	{
	        		 arrowCurrXPos = awayFromBorder;
	        		 break;
	        	}
	    
			    case 2:
		        {
			        arrowCurrXPos = awayFromBorder + arrowLen; 
			        break;
		        }
	      
		        case 3:
		        {
		            arrowCurrXPos = awayFromBorder + arrowLen*2; 
		            break;
		        }   
	      
		        case 4:
		        {
		            arrowCurrXPos = awayFromBorder + arrowLen*3; 
		            break;
		        }
	        }// Endswitch()
	     }//End arrow()
	  
		 public void arrowMovement(int spd)
	     {
		     arrowCurrYPos -= spd;
		     
		     fill(0);
		     rect(arrowCurrXPos, arrowCurrYPos, arrowLen, arrowHei);
		 }
	  
		 public void arrowCollison()
		 {
		 }
	}
}