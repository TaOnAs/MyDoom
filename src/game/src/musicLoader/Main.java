/*
 * 
 * Author:Ciaran Strutt
 * Date:08/04/2015
 * Description:Loads music files player wants to add to game and passes them to the analyzer class
 * 
 */
package game.src.musicLoader;

import java.io.File;
import processing.core.*; //PApplet is in here
import ddf.minim.*; //Processing sound methods

public class Main extends PApplet{

	Minim musicLoader = new Minim(this); //Create a minim instance to load the song into musicPlayer
	AudioPlayer musicPlayer; //Object that will actually play the sound
	
	public void setup()
	{
		size(800,800);
		smooth();
		showFileDialog(); //GUI open file dialog like what is built into windows 
	}
	
	public void showFileDialog()
	{
		/*
		 * Opens systems default file dialog and
		 * sets the title to the first parameter of selectInput(first,second)
		 * the second parameter is a callback which means when selectInput is called
		 * it will then call the method specified in the second parameter
		 */
		selectInput("Select a music file to open","getMusicFile");  
	}
	
	public void getMusicFile(File musicFile)
	{
		if(musicFile == null) //Make sure the file points to something, will add audio extension filtering 
		{
			println("Error while loading file");
		}
		else
		{
			println("Path to file: " + musicFile.getAbsolutePath()); //Print the absolute path to the file 
			musicPlayer = musicLoader.loadFile(musicFile.getAbsolutePath()); //Load the music file into the musicPlayer
		}
		playMusicFile(); 
	}

	public void playMusicFile()
	{
		musicPlayer.play(); //Play the audio file
	}
	
}
