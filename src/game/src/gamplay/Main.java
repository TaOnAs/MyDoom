package game.src.gamplay;

import java.util.ArrayList;
import processing.core.PApplet;

public class Main extends PApplet
{
	boolean[] buttons;
	
	int noArrows; //The current amount of arrows on screen
	int maxNoArrows; //Maximum number of arrows that will be made
	int spawnSpot;
	int score;
	float life;
	
	float ArrowHei = 50; //Sets Arrow height
	float ArrowLen = 50; //Sets Arrow length
	
	ArrayList<Arrow> Arrows;
	checkArea scoreSpot;
	Collision collision;
	
	public void setup()
	{
		size(800, 580);
		
		score = 0;
		life = 100;
		
		buttons = new boolean[4];
		
		Arrows = new ArrayList<Arrow>();
		scoreSpot = new checkArea(ArrowHei);
		collision = new Collision(0, scoreSpot.awayFromTop, scoreSpot.checkLen, scoreSpot.checkHei);
		
		frameRate(60);
		
	    noArrows = 0;
	    maxNoArrows = 32;
	    
	    //Starts up the buttons as off
	    buttons[0] = false;
	    buttons[1] = false;
	    buttons[2] = false;
	    buttons[3] = false;
	}

	public void draw()
	{
	    background(255);
	    //Gameplay()
	    
	    text(noArrows, 50, 50);
	    text(life, 100, 110);
	    text(score, 100, 120);
	    
	    scoreSpot.drawArea();
	    
	    if(noArrows == 0)
	    {
	    	for(int i = 0; i < maxNoArrows; i++)
	    	{
	    		spawnSpot = (int)random(0, 4);
	    		
		        Arrows.add(new Arrow(ArrowHei, ArrowHei, 200, spawnSpot, noArrows));
		        noArrows += 1; //Increments number of arrows
	    	}
	    }
	    
    	for(int i = 0; i < Arrows.size(); i++)
    	{
    		Arrows.get(i).arrowMovement(2f);
    	}
	    
	    //Checks to see if arrows go off screen
	    for(int i = 0; i < Arrows.size(); i++)
	    {
	    	if(Arrows.get(i).arrowOffScreen())
	    	{
	    		Arrows.remove(i);
	    		noArrows -= 1;
	    	}
	    }
	    
	    //Checks to see if arrow is inside check box
	    for(int i = 0; i < Arrows.size(); i++)
	    {
	    	boolean arrowsPressed = false;
	    	
	    	//If note is in box and the wrong key is pressed
	    	/*if(collision.collisionConnect(Arrows.get(i).arrowCurrXPos, Arrows.get(i).arrowCurrYPos, Arrows.get(i).arrowHei, Arrows.get(i).arrowLen))
	    	{
	    		if(((Arrows.get(i).kPos == 0)) && ((buttons[1]) || (buttons[2]) || (buttons[3])))
	    		{
	    			life -= 10;
	    			arrowsPressed = true;
	    		}
	    		
	    		if(((Arrows.get(i).kPos == 1)) && ((buttons[0]) || (buttons[3]) || (buttons[2])))
	    		{
	    			life -= 10;
	    			arrowsPressed = true;
	    		}
	    		
	    		if(((Arrows.get(i).kPos == 2)) && ((buttons[0]) || (buttons[3]) || (buttons[1])))
	    		{
	    			life -= 10;
	    			arrowsPressed = true;
	    		}
	    		
	    		if(((Arrows.get(i).kPos == 3)) && ((buttons[1]) || (buttons[2]) || (buttons[0])))
	    		{
	    			life -= 10;
	    			arrowsPressed = true;
	    		}
	    	}*/
	    	
	    	//If note is in box and the right key is pressed
	    	if(collision.collisionConnect(Arrows.get(i).arrowCurrXPos, Arrows.get(i).arrowCurrYPos, Arrows.get(i).arrowHei, Arrows.get(i).arrowLen))
	    	{
	    		if((Arrows.get(i).kPos == 0) && (buttons[0]))
	    		{
	    			score += 10;
	    			arrowsPressed = true;
	    		}
	    		else if(((Arrows.get(i).kPos == 0)) && ((buttons[1]) || (buttons[2]) || (buttons[3])))
	    		{
	    			life -= 10;
	    			arrowsPressed = true;
	    		}
	    		
	    		
	    		if((Arrows.get(i).kPos == 1) && (buttons[1]))
	    		{
	    			score += 10;
	    			arrowsPressed = true;
	    		}
	    		else if(((Arrows.get(i).kPos == 1)) && ((buttons[0]) || (buttons[3]) || (buttons[2])))
	    		{
	    			life -= 10;
	    			arrowsPressed = true;
	    		}
	    		
	    		if((Arrows.get(i).kPos == 2) && (buttons[2]))
	    		{
	    			score += 10;
	    			arrowsPressed = true;
	    		}
	    		else if(((Arrows.get(i).kPos == 2)) && ((buttons[0]) || (buttons[3]) || (buttons[1])))
	    		{
	    			life -= 10;
	    			arrowsPressed = true;
	    		}
	    		
	    		if((Arrows.get(i).kPos == 3) && (buttons[3]))
	    		{
	    			score += 10;
	    			arrowsPressed = true;
	    		}
	    		else if(((Arrows.get(i).kPos == 3)) && ((buttons[1]) || (buttons[2]) || (buttons[0])))
	    		{
	    			life -= 10;
	    			arrowsPressed = true;
	    		}
	    	}
	    	
	    	if(arrowsPressed)
	    	{
	    		buttons[0] = false;
	    	    buttons[1] = false;
	    	    buttons[2] = false;
	    	    buttons[3] = false;
	    		
	    		Arrows.remove(i);
	    		noArrows -= 1;
	    	}
	    }
	    
	    //Press buttons to match with buttons
	    
	}
	
	//Creates the area in which points can be gotten when button is pressed when square is on the area
	class checkArea
	{
		float checkHei;
		float checkLen;
		float awayFromTop;
		
		public checkArea(float arrowHei)
		{
			awayFromTop = 50;
			
			checkHei = arrowHei;
			checkLen = width;
		}
		
		//Draws the score area
		public void drawArea()
		{
			fill(255, 0, 0);
			rect(0, awayFromTop, checkLen, checkHei);
		}
	}
	
	class Arrow
	{
		float arrowLen; //Length of arrow
		float arrowHei; //Height of arrow
		float arrowCurrXPos; //Arrow X position
		float arrowCurrYPos; //Arrow Y position
		int kPos; //The position of arrow key
	  
	    public Arrow(float len, float hei, float xPos, int k, int noInQueue)
	    {
	        float awayFromBorder = xPos;
	    
	        //Sets arrow dimenision
	        arrowLen = len;
	        arrowHei = hei;
	    
	        kPos = k;
	        
	        //Sets arrow
	        arrowCurrYPos = (height + arrowHei) + noInQueue*arrowHei;
	    
	        //Sets arrows in their right X positions depending on which key is used
	        switch(k)
	        {
	        	case 0:
	        	{
	        		 arrowCurrXPos = awayFromBorder;
	        		 break;
	        	}
	    
			    case 1:
		        {
			        arrowCurrXPos = awayFromBorder + arrowLen; 
			        break;
		        }
	      
		        case 2:
		        {
		            arrowCurrXPos = awayFromBorder + arrowLen*2; 
		            break;
		        }   
	      
		        case 3:
		        {
		            arrowCurrXPos = awayFromBorder + arrowLen*3; 
		            break;
		        }
	        }// Endswitch()
	     }//End arrow()
	  
		 public void arrowMovement(float spd)
	     {
		     arrowCurrYPos -= spd;
		     
		     fill(0);
		     rect(arrowCurrXPos, arrowCurrYPos, arrowLen, arrowHei);
		 }
	  
		 public void arrowCollison()
		 {
		 }
		 
		 public boolean arrowOffScreen()
		 {
			if(arrowCurrYPos <= 0)
			{
				return true;
			}
			else
			{
				return false;
			}
		 }
	}//End class arrow
	
	class Collision
	{
	    //Score section area
	    float scoreSectionXCo;
	    float scoreSectionYCo;
	    float scoreSectionLen;
	    float scoreSectionHei;
	    
	    float scoreSectionXCo2;
	    float scoreSectionYCo2;
	    
	    //Arrows
	    float arrowXCo;
	    float arrowYCo;
	    float arrowLen;
	    float arrowHei;
	    
	    float arrowXCo2;
	    float arrowYCo2;
	    
	    public Collision(float ssX, float ssY, float ssLen, float ssHei)
	    {
	    	scoreSectionXCo = ssX;
		    scoreSectionYCo = ssY;
		    scoreSectionLen = ssLen;
		    scoreSectionHei = ssHei;
		    
		    scoreSectionXCo2 = ssX + ssLen;
		    scoreSectionYCo2 = ssY + ssHei;
	    }
	    
	    public boolean collisionConnect(float xCo, float yCo, float len, float hei)
	    {
	    	boolean hit = false;
	    	
	    	arrowXCo = xCo;
		    arrowYCo = yCo;
		    arrowLen = len;
		    arrowHei = hei;
		    
		    arrowXCo2 = xCo + len;
		    arrowYCo2 = yCo + hei;
		    
		    //Bottom left of arrow touches or is inside object1
	        if(((arrowYCo2 >= scoreSectionYCo) && (arrowYCo2 <= scoreSectionYCo2)) && 
	        ((arrowXCo >= scoreSectionXCo) && (arrowXCo <= scoreSectionXCo2)))
	        {
	            hit = true;
	        }
	        
	        //Top left of arrow touches or is inside object1
	        if(((arrowXCo >= scoreSectionXCo) && (arrowXCo <= scoreSectionXCo2)) && 
	        ((arrowYCo >= scoreSectionYCo) && (arrowYCo <= scoreSectionYCo2)))
	        {
	            hit = true;
	        }
	        
	        return hit;
	    }
	}
	
	//Mapping buttons
	public void keyPressed()
	{
		 //WASD key pad
	     /*if(key == 'w' || key == 'W')
	     {
	        buttons[0] = true;
	     }
	     
	     if(key == 'a' || key == 'A')
	     {
	         buttons[1] = true;
	     }
	     
	     if(key == 's' || key == 'S')
	     {
	         buttons[2] = true;
	     }
	     
	     if(key == 'd' || key == 'D')
	     {
	         buttons[3] = true;
	     }*/
		
		 if(key == 'q' || key == 'Q')
	     {
	        buttons[0] = true;
	     }
	     
	     if(key == 'w' || key == 'W')
	     {
	         buttons[1] = true;
	     }
	     
	     if(key == 'e' || key == 'E')
	     {
	         buttons[2] = true;
	     }
	     
	     if(key == 'r' || key == 'R')
	     {
	         buttons[3] = true;
	     }
	}

	public void keyReleased()
	{
		//WASD Keypad
		/*if(key == 'w' ||key == 'W')
	    {
	        buttons[0] = false;
	    }
	     
	    if(key == 'a' || key == 'A')
        {
            buttons[1] = false;
        }
	     
        if(key == 's' || key == 'S')
        {
            buttons[2] = false;
        }
	     
        if(key == 'd' || key == 'D')
        {     
        	buttons[3] = false;
	    }*/
		
		if(key == 'q' ||key == 'Q')
	    {
	        buttons[0] = false;
	    }
	     
	    if(key == 'w' || key == 'W')
        {
            buttons[1] = false;
        }
	     
        if(key == 'e' || key == 'E')
        {
            buttons[2] = false;
        }
	     
        if(key == 'r' || key == 'R')
        {     
        	buttons[3] = false;
	    }
	}
}