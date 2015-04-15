/* 
Author: Trieu Nguyen
 */

package game.Gameplay;

import java.util.ArrayList;
import processing.core.PApplet;

public class Gameplay
{
	boolean[] buttons;
	
	int noArrows; //The current amount of arrows on screen
	int maxNoArrows; //Maximum number of arrows that will be made
	int spawnSpot; //
	public int score;
	int chainGauge;
	
	float arrowSpeedVariable; //Speed of the arrow
	float speedConstant; //Constant that is needed to adjust arrows to constant speed regardless of screensize or arrow size
	
	public float life;
	
	float ArrowHei = 100; //Sets Arrow height
	float ArrowLen = 100; //Sets Arrow length
	public PApplet parent;
	
	ArrayList<Arrow> Arrows;
	checkArea scoreSpot;
	Collision collision;
	
	public Gameplay(PApplet mainApplet)
	{
		parent = mainApplet;
	}
	
	public void setup()
	{	
		//parent.size(parent.displayWidth,parent.displayHeight);
		score = 0;
		life = 100;
		
		buttons = new boolean[4];
		
		Arrows = new ArrayList<Arrow>();
		scoreSpot = new checkArea(ArrowHei);
		collision = new Collision(0, scoreSpot.awayFromTop, scoreSpot.checkLen, scoreSpot.checkHei);
		
		parent.frameRate(60);
		
	    noArrows = 0;
	    maxNoArrows = 32;
	    
	    arrowSpeedVariable = 35f;
	    
	    //Starts up the buttons as off
	    buttons[0] = false;
	    buttons[1] = false;
	    buttons[2] = false;
	    buttons[3] = false;
	    
	}
	
	public void draw()
	{
		parent.background(135, 135, 135, 191);
		aestheics();
	    lifeDraw();
	    
	    parent.text(noArrows, 50, 50);
	    parent.text(life, 100, 210);
	    
	    parent.textSize(20);
	    parent.text("Combo: " + chainGauge, 650, 150);
	    parent.text("Points: " + score, 650, 175);
	    
	    scoreSpot.drawArea();

	    Gameplay();
	    
	    if(life > 100)
	    {
	    	life = 100;
	    }
	    
	    if(life < 0)
	    {
	    	life = 0;
	    }
	}//End draw()
	
	public void lifeDraw()
	{
		parent.fill(0, 0, 0, 191);
		parent.rect(0, 0, 180, parent.height);
		
		if(life >= 75)
		{
			parent.fill(0, 178, 0, 191);
			parent.rect(0, 0, 180, (parent.height*life/100));
		}
		
		if((life < 75) &&(life >= 30))
		{
			parent.fill(250, 255, 84, 191);
			parent.rect(0, 0, 180, (parent.height*life/100));
		}
		
		if(life < 30)
		{
			parent.fill(128, 8, 45);
			parent.rect(0, 0, 180, (parent.height*life/100));
		}
		
		parent.fill(0);
	}
	
	public void aestheics()
	{
		parent.fill(0);
		parent.rect(180, 0, 440, parent.height);
		
		parent.stroke(0);
		parent.fill(255, 255, 255, 171);
		parent.rect(200, 0, 400, parent.height);
	}
	
	public void Gameplay()
	{
		if(noArrows == 0)
	    {
	    	for(int i = 0; i < maxNoArrows; i++)
	    	{
	    		spawnSpot = (int)parent.random(0, 4);
	    		
		        Arrows.add(new Arrow(ArrowHei, ArrowLen, 200, spawnSpot, noArrows));
		        noArrows += 1; //Increments number of arrows
	    	}
	    }
	    
	    for(int i = 0; i < Arrows.size(); i++)
	    {
	    	boolean arrowsPressed = false;
	    	
	    	//If note is in box and the right key is pressed
	    	if(collision.collisionConnect(Arrows.get(i).arrowCurrXPos + Arrows.get(i).arrowLen, Arrows.get(i).arrowCurrYPos + Arrows.get(i).arrowHei, Arrows.get(i).arrowHei, Arrows.get(i).arrowLen) && ((Arrows.get(i).arrowCurrYPos + Arrows.get(i).arrowHei > scoreSpot.awayFromTop) && (Arrows.get(i).arrowCurrYPos + Arrows.get(i).arrowHei < scoreSpot.awayFromTop + scoreSpot.checkHei)))
	    	{
	    		if((Arrows.get(i).kPos == 0) && (buttons[0]))
	    		{
	    			score += scoreSystemInverse(Arrows.get(i).arrowCurrXPos + Arrows.get(i).arrowLen, Arrows.get(i).arrowCurrYPos + Arrows.get(i).arrowHei, Arrows.get(i).arrowHei, Arrows.get(i).arrowLen);
	    			life += 3;
	    			arrowsPressed = true;
	    		}
	    		else if(((Arrows.get(i).kPos == 0)) && ((buttons[1]) || (buttons[2]) || (buttons[3])))
	    		{
	    			chainGauge = 0;
	    			life -= 5;
	    			arrowsPressed = true;
	    		}
	    		
	    		if((Arrows.get(i).kPos == 1) && (buttons[1]))
	    		{
	    			score += scoreSystemInverse(Arrows.get(i).arrowCurrXPos + Arrows.get(i).arrowLen, Arrows.get(i).arrowCurrYPos + Arrows.get(i).arrowHei, Arrows.get(i).arrowHei, Arrows.get(i).arrowLen);
	    			life += 3;
	    			arrowsPressed = true;
	    		}
	    		else if(((Arrows.get(i).kPos == 1)) && ((buttons[0]) || (buttons[3]) || (buttons[2])))
	    		{
	    			chainGauge = 0;
	    			life -= 5;
	    			arrowsPressed = true;
	    		}
	    		
	    		if((Arrows.get(i).kPos == 2) && (buttons[2]))
	    		{
	    			score += scoreSystemInverse(Arrows.get(i).arrowCurrXPos + Arrows.get(i).arrowLen, Arrows.get(i).arrowCurrYPos + Arrows.get(i).arrowHei, Arrows.get(i).arrowHei, Arrows.get(i).arrowLen);
	    			life += 3;
	    			arrowsPressed = true;
	    		}
	    		else if(((Arrows.get(i).kPos == 2)) && ((buttons[0]) || (buttons[3]) || (buttons[1])))
	    		{
	    			chainGauge = 0;
	    			life -= 5;
	    			arrowsPressed = true;
	    		}
	    		
	    		if((Arrows.get(i).kPos == 3) && (buttons[3]))
	    		{
	    			score += scoreSystemInverse(Arrows.get(i).arrowCurrXPos + Arrows.get(i).arrowLen, Arrows.get(i).arrowCurrYPos + Arrows.get(i).arrowHei, Arrows.get(i).arrowHei, Arrows.get(i).arrowLen);
	    			life += 3;
	    			arrowsPressed = true;
	    		}
	    		else if(((Arrows.get(i).kPos == 3)) && ((buttons[1]) || (buttons[2]) || (buttons[0])))
	    		{
	    			chainGauge = 0;
	    			life -= 5;
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
	    
	    //Checks to see if arrow is inside check box
	    for(int i = 0; i < Arrows.size(); i++)
	    {
	    	boolean arrowsPressed = false;
	    	
	    	//Checks if bottom is in box
	    	if((collision.collisionConnect(Arrows.get(i).arrowCurrXPos, Arrows.get(i).arrowCurrYPos, Arrows.get(i).arrowHei, Arrows.get(i).arrowLen)) && ((Arrows.get(i).arrowCurrYPos > scoreSpot.awayFromTop) && (Arrows.get(i).arrowCurrYPos < scoreSpot.awayFromTop + scoreSpot.checkHei)))
	    	{
	    		if((Arrows.get(i).kPos == 0) && (buttons[0]))
	    		{
	    			score += scoreSystem(Arrows.get(i).arrowCurrXPos, Arrows.get(i).arrowCurrYPos, Arrows.get(i).arrowHei, Arrows.get(i).arrowLen);
	    			life += 5;
	    			arrowsPressed = true;
	    		}
	    		else if(((Arrows.get(i).kPos == 0)) && ((buttons[1]) || (buttons[2]) || (buttons[3])))
	    		{
	    			chainGauge = 0;
	    			life -= 10;
	    			arrowsPressed = true;
	    		}
	    		
	    		if((Arrows.get(i).kPos == 1) && (buttons[1]))
	    		{
	    			score += scoreSystem(Arrows.get(i).arrowCurrXPos, Arrows.get(i).arrowCurrYPos, Arrows.get(i).arrowHei, Arrows.get(i).arrowLen);
	    			life += 5;
	    			arrowsPressed = true;
	    		}
	    		else if(((Arrows.get(i).kPos == 1)) && ((buttons[0]) || (buttons[3]) || (buttons[2])))
	    		{
	    			chainGauge = 0;
	    			life -= 10;
	    			arrowsPressed = true;
	    		}
	    		
	    		if((Arrows.get(i).kPos == 2) && (buttons[2]))
	    		{
	    			score += scoreSystem(Arrows.get(i).arrowCurrXPos, Arrows.get(i).arrowCurrYPos, Arrows.get(i).arrowHei, Arrows.get(i).arrowLen);
	    			life += 5;
	    			arrowsPressed = true;
	    		}
	    		else if(((Arrows.get(i).kPos == 2)) && ((buttons[0]) || (buttons[3]) || (buttons[1])))
	    		{
	    			chainGauge = 0;
	    			life -= 10;
	    			arrowsPressed = true;
	    		}
	    		
	    		if((Arrows.get(i).kPos == 3) && (buttons[3]))
	    		{
	    			score += scoreSystem(Arrows.get(i).arrowCurrXPos, Arrows.get(i).arrowCurrYPos, Arrows.get(i).arrowHei, Arrows.get(i).arrowLen);
	    			life += 5;
	    			arrowsPressed = true;
	    		}
	    		else if(((Arrows.get(i).kPos == 3)) && ((buttons[1]) || (buttons[2]) || (buttons[0])))
	    		{
	    			chainGauge = 0;
	    			life -= 10;
	    			arrowsPressed = true;
	    		}
	    	}
	    	
	    	//Pressed buttons
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
	    
	    //Sets the arrows speed
    	for(int i = 0; i < Arrows.size(); i++)
    	{
    		speedConstant = arrowSpeedVariable/Arrows.get(i).arrowHei;
    		
    		Arrows.get(i).arrowMovement(arrowSpeedVariable*speedConstant, i);
    	}
	    
	    //Checks to see if arrows go off screen
	    for(int i = 0; i < Arrows.size(); i++)
	    {
	    	if(Arrows.get(i).arrowOffScreen())
	    	{
	    		Arrows.remove(i);
	    		noArrows -= 1;
	    		life -= 3;
	    	}
	    }
	}
	
	//Calculates score
	public int scoreSystem(float arrowXPos, float arrowYPos, float arrowHei, float arrowLen)
	{
		float closenessToTop;
		float coverageRatio;
		float amountCovered;
		float startingLine;
		int points = 0;
		
		closenessToTop = arrowYPos - scoreSpot.awayFromTop;
		
		//0 is completely covered
		amountCovered = arrowHei - closenessToTop;
		
		//Closeness to perfect sync
		coverageRatio = (amountCovered/arrowHei)*100;
		
		//Perfect
		if(coverageRatio >=90)
		{
			chainGauge += 1;
			points += 1000;
		}
		
		//Great
		if((coverageRatio < 90) && (coverageRatio >= 50))
		{
			chainGauge += 1;
			points += 800;
		}
		
		//Good
		if((coverageRatio < 50) && (coverageRatio >= 20))
		{
			chainGauge += 1;
			points += 300;
		}
		
		//Poor
		if((coverageRatio < 20) && (coverageRatio >= 0))
		{
			chainGauge = 0;
			points += 50;
			life -= 5;
		}
		
		return points;
	}
	
	public int scoreSystemInverse(float arrowXPos, float arrowYPos, float arrowHei, float arrowLen)
	{
		float closenessToTop;
		float coverageRatio;
		float amountCovered;
		float startingLine;
		int points = 0;
		
		closenessToTop = arrowYPos - scoreSpot.awayFromTop;
		
		//0 is completely covered
		amountCovered = arrowHei - closenessToTop;
		
		//Closeness to perfect sync
		coverageRatio = (amountCovered/arrowHei)*100;
		
		//Perfect
		if(coverageRatio >=90)
		{
			chainGauge = 0;
			points += 50;
			life -= 5;
		}
		
		//Great
		if((coverageRatio < 90) && (coverageRatio >= 50))
		{
			chainGauge += 1;
			points += 300;
		}
		
		//Good
		if((coverageRatio < 50) && (coverageRatio >= 30))
		{
			chainGauge += 1;
			points += 800;
		}
		
		//Poor
		if((coverageRatio < 30) && (coverageRatio >= 0))
		{
			chainGauge += 1;
			points += 1000;
		}
		
		return points;
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
			checkLen = parent.width;
		}
		
		//Draws the score area
		public void drawArea()
		{
			parent.fill(0, 0, 155);
			parent.rect(200, awayFromTop, 400, checkHei);
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
	        arrowCurrYPos = (parent.height + arrowHei) + noInQueue*arrowHei;
	    
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
	  
		 public void arrowMovement(float spd, int i)
	     {
		     arrowCurrYPos -= spd;
		     
		     switch(Arrows.get(i).kPos)
    		{
	    		case 0:
	    		{
	    			parent.fill(100, 155, 50);
	    			break;
	    		}
	    		
	    		case 1:
	    		{
	    			parent.fill(50, 155, 100);
	    			break;
	    		}
	    		
	    		case 2:
	    		{
	    			parent.fill(100, 155, 50);
	    			break;
	    		}
	    		
	    		case 3:
	    		{
	    			parent.fill(50, 155, 100);
	    			break;
	    		}
    		}
		     
		     //fill(0);
		     parent.rect(arrowCurrXPos, arrowCurrYPos, arrowLen, arrowHei);
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
		
		 if(parent.key == 'q' || parent.key == 'Q')
	     {
	        buttons[0] = true;
	     }
	     
	     if(parent.key == 'w' || parent.key == 'W')
	     {
	         buttons[1] = true;
	     }
	     
	     if(parent.key == 'e' || parent.key == 'E')
	     {
	         buttons[2] = true;
	     }
	     
	     if(parent.key == 'r' || parent.key == 'R')
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
		
		if(parent.key == 'q' ||parent.key == 'Q')
	    {
	        buttons[0] = false;
	    }
	     
	    if(parent.key == 'w' || parent.key == 'W')
        {
            buttons[1] = false;
        }
	     
        if(parent.key == 'e' || parent.key == 'E')
        {
            buttons[2] = false;
        }
	     
        if(parent.key == 'r' || parent.key == 'R')
        {     
        	buttons[3] = false;
	    }
	}
}