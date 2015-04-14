package game.Menu;

import processing.core.PApplet;
import processing.core.PImage;

public class Menu extends PApplet
{
	//DT228-2 
	//Programming Assignment 3
	//By Luke O'Brien, C13435702
	//Main Menu
	//

	PImage doom;  //Loading a picture
	PImage mydoom;  //Loading a picture
	PImage gameby;  //Loading a picture
	PImage treb;  //Loading a picture
	PImage start;  //Loading a picture
	PImage help;  //Loading a picture
	PImage gear;  //Loading a picture
	PImage score;  //Loading a picture
	PImage arrow;  //Loading a picture
	PImage options2;  //Loading a picture
	PImage highscores;  //Loading a picture
	PImage helptitle;  //Loading a picture
	PImage doomonics;  //Loading a picture


	boolean mainMenu;  //Boolean for the menu
	boolean helpscreen;  //Boolean for the menu
	boolean optionsmenu;  //Boolean for the menu
	boolean scorescreen;  //Boolean for the menu

	int circleSize = 501;  //for pulsing
	int shrinkOrGrow = 1;  //for pulsing

	float transparency = 1;  //Used for image fade-in
	float y;     // location
	float menu;
	//PFont f;                //Declare font variable

	public void setup()
	{
	  size(displayWidth, displayHeight);  //Fullscreen
	  noCursor();  //Remove mouse cursor from within the boundry of the project window
	  
	  //f = createFont("ARCADECLASSIC.TTF", 500);    //Creates font
	  doom = loadImage("final2.png");       //Load picture
	  mydoom = loadImage("MYDOOM.png");       //Load picture
	  gameby = loadImage("GAMEBY.png");       //Load picture
	  treb = loadImage("trebS.gif");       //Load picture
	  start = loadImage("Start.png");       //Load picture
	  help = loadImage("Help.png");       //Load picture
	  gear = loadImage("gear.png");       //Load picture
	  score = loadImage("Scores.png");       //Load picture
	  arrow = loadImage("arrow.png");       //Load picture
	  options2 = loadImage("Options.png");       //Load picture
	  highscores = loadImage("highscores.png");       //Load picture
	  helptitle = loadImage("helptitle.png");       //Load picture
	  doomonics = loadImage("doomonics.png");       //Load picture
	  
	  menu=1;
	  
	  mainMenu=false;  //Boolean
	  helpscreen=false;  //Boolean
	  optionsmenu=false;  //Boolean
	  scorescreen=false;  //Boolean
	  
	  y=height-height*2;
	}

	//--------------------------------------------------------------------------------------

	public void draw()
	{
	  splash();
	  
	  if (mainMenu)
	  {
	    mainMenu();  //Calls mainmenu
	  }
	  if (helpscreen)
	  {
	    helpScreen();  //Calls help
	  }
	  if (optionsmenu)
	  {
	    options();  //Calls options
	  }
	   if (scorescreen)
	  {
	    highscores();  //Calls scorescreen
	  }
	}

	//--------------------------------------------------------------------------------------

	void splash()
	{
	  background(255);  //background colour
	  //textFont(f, 50);      //Choose font
	  //stroke(0);
	  //fill(0);

	  if (millis() > 2000)  //Image fade in delay
	  {
	    if (transparency < 255)  //If the image isnt fully faded in
	    { 
	      transparency += 2;  //Fades in
	    }
	    tint(255, transparency);
	    imageMode(CENTER);
	    image(gameby, width/2, height/9);
	    image(mydoom, width/2, height/5*4);
	    image(doom, width/2, height/5*2);
	  }
	   
	   if (y>height+2000)
	   {
	     y=0;
	   }
	      
	  if (millis() > 6400)  //Image fade in delay
	  {
	    stroke(255, 0, 0);
	    //fill(#E3130B);
	    fill(255, 0, 0);
	     
	    rect(0,y,width,height-height*2);
	    y=y+30;
	  }
	    
	  if (millis() > 7200)  //Image fade in delay
	  {
	    mainMenu();
	  }
	  
	  if (keyPressed == true) 
	  {
	    mainMenu=true;
	  }
	  
	}

	//--------------------------------------------------------------------------------------

	void mainMenu()
	{
	  background(255, 0, 0);

	  if (millis() > 7600)  //Image fade in delay
	  {
	    if (transparency < 255)  //If the image isnt fully faded in
	    { 
	      transparency += 1;  //Fades in
	    }
	    tint(255, transparency);
	    imageMode(CENTER);
	    
	  }

	   image(start, width/5*4, height/4 - 40);
	   image(help, width/5*4, height/4*2 - 40);
	   image(gear, width/5*4, height/4*3 - 40);
	   image(score, width/5*4, height/4*4 - 40);
	   
	   fill(229, 194, 51);
	   
	   if (menu==1)
	   {
	      image(arrow, width/5*4, height/4 - 40);
	    // triangle(width/2-50, height/5/2, width/2-50, height/5/2+30, width/2-10, height/5+15);
	    //ellipse(width/5*2, height/5, width/50, height/50);
	   }
	   
	   if (menu==2)
	   {
	     image(arrow, width/5*4, height/4*2 - 40);
	   }
	   
	   if (menu==3)
	   {
	      image(arrow, width/5*4, height/4*3 - 40);
	   }
	   
	   if (menu==4)
	   {
	      image(arrow, width/5*4, height/4*4 - 40);
	   }
	   
	  /* if (keyPressed)
	   {
	      if (key == CODED)
	      {
	        if (keyCode == UP)
	        {
	          menu=menu-1;
	        }
	        if (keyCode == DOWN)
	        {
	          menu=menu+1;
	        }
	      }
	   }  */  //Not needed with keyreleased

	  if (menu<1)  //theres only 4 options
	   {
	     menu=1;
	   }
	   
	   if (menu>4)  //theres only 4 options
	   {
	      menu=4;
	   }

	  stroke(0);

	  if (circleSize > 650) 
	  {
	        shrinkOrGrow = 0; 
	    } 
	    else if (circleSize < 350) 
	    {
	        shrinkOrGrow = 1;
	    }
	    if (shrinkOrGrow == 1)
	    {
	        circleSize += 7;
	    } 
	    else if (shrinkOrGrow == 0)
	    {
	        circleSize -= 3;  
	    } 
	    
	    fill(255);
	    ellipse(width/7*2, height/2, circleSize, circleSize);
	    
	    image(doomonics, width/7*2, height/2);
	  
	}

	//--------------------------------------------------------------------------------------

	void helpScreen()
	{
	  mainMenu=false;
	  background(255, 0, 0);	  
	  imageMode(CENTER);
	  image(helptitle, width/2, height/5);
	}

	//--------------------------------------------------------------------------------------

	void options()
	{
	  mainMenu=false;
	  background(255, 0, 0);
	  
	  imageMode(CENTER);
	  image(options2, width/2, height/5);
	}

	//--------------------------------------------------------------------------------------

	void highscores()
	{
	  mainMenu=false;
	  background(255, 0, 0);
	  
	  imageMode(CENTER);
	  image(highscores, width/2, height/5);
	}

	//--------------------------------------------------------------------------------------

	public void keyReleased()
	{
	    if (key == CODED)
	      {
	        if (keyCode == UP)
	        {
	          menu=menu-1;
	        }
	        if (keyCode == DOWN)
	        {
	          menu=menu+1;
	        }
	      }
	      
	      if (key == 'x' || key == 'X')
	    {
	       if (menu==1)
	       {
	          
	       }
	       
	       if (menu==2)
	       {
	          helpscreen=true;
	       }
	       
	       if (menu==3)
	       {
	          optionsmenu=true;
	       }
	       
	       if (menu==4)
	       {
	          scorescreen=true;
	       }
	    }
	    
	     if (key == 'z' || key == 'Z')
	    {
	      helpscreen=false;
	      optionsmenu=false;
	      scorescreen=false;  //Boolean
	      mainMenu=true;
	    }
	}

}