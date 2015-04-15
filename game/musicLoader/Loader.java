
/*
 * 
 * Author:Ciaran Strutt
 * Date:08/04/2015
 * Description:Loads music files player wants to add to game and passes them to the analyzer class
 * 
 */


package game.musicLoader;

import processing.core.PApplet; //PApplet is in here
import ddf.minim.*; //Processing sound methods
import ddf.minim.analysis.FFT;
import javax.swing.*;
import java.io.*;

public class Loader{

	public Minim musicLoader; //Create a variable that will be used to instantiate a minim object
	public AudioPlayer musicPlayer; //Object that will actually play the sound
	public AudioMetaData musicMetaData;
	public FFT frequencySpectrum;
	private PApplet parent;
	public boolean fileLoaded;
	public int metaXPos,metaYPos,metaSpacing,waveFormXPos,waveFormYPos; //Co-ordinates for displaying the metadata on the applet instead of console
	public String musicFileName;
	public File musicFile;
	final JFileChooser fileChooser = new JFileChooser();
	int fileChooserCheck = fileChooser.showOpenDialog(parent);
	
	
	public void setup()
	{
		//parent.smooth();
		//parent.noLoop(); //Run draw() once
		/*
		 * draw() was being ran even before the file was loaded but what was being drawn
		 * is dependent on data in the music file being loaded 
		 */
	}
	
	public Loader(PApplet mainApplet)
	{
		parent = mainApplet;
		fileLoaded = false;
		metaXPos = 800; //X cord
		metaYPos = 200; //Y cord
		waveFormXPos = 800;
		waveFormYPos = 100;
		metaSpacing = 20; //Spacing between each line of text
		parent.textFont(parent.createFont("Serif", metaSpacing));
		showFileDialog();
	}
	
	public void showFileDialog()
	{
		/*
		 * Opens systems default file dialog and
		 * sets the title to the first parameter of selectInput(first,second)
		 * the second parameter is a callback which means when selectInput is called
		 * it will then call the method specified in the second parameter
		 */
		//parent.selectInput("Select a music file to open","getMusicFile",null,this);  //Callback functions extremely dodgy with multiple classes
		if(fileChooserCheck == JFileChooser.APPROVE_OPTION)
		{
			musicFile = fileChooser.getSelectedFile();
			musicFileName = musicFile.getAbsolutePath();
			getMusicFile(musicFile);
		}
		else
		{
			PApplet.println("Error loading file");
		}
		
	}
	
	public void getMusicFile(File musicFile)
	{
		if(musicFile.isFile() != true) //Make sure the file points to something, will add audio extension filtering 
		{
			PApplet.println("Error while loading file");
		}
		else
		{
			musicLoader = new Minim(parent);
			musicPlayer = musicLoader.loadFile(musicFile.getAbsolutePath(),1024); //Load the music file into the musicPlayer
			//mainMenu.startGame = true;
			PApplet.println("File loaded");
			fileLoaded = true;
			//parseMetaData(); //Only want to call these 3 methods once
			//setupSpectrum(); //Having them in setup() was causing the rest of the program not to work
			//playMusicFile();
			/*
			 * File is loaded so we have the data we need to draw the spectrum 
			 * so re-enable looping of the draw() method
			 */
			//parent.loop(); 
			//parent.loop();
		}
	}
	 
	public void parseMetaData()
	{
		PApplet.println("Metadata reached");
		musicMetaData = musicPlayer.getMetaData(); //Gets the metadata for the current file loaded into musicPlayer
	}
	
	public void setupSpectrum()
	{
		frequencySpectrum = new FFT(musicPlayer.bufferSize(),musicPlayer.sampleRate());
		frequencySpectrum.forward(musicPlayer.mix);
	}
	
	public void displaySpectrum()
	{
		/*
		Just some debugging code to see what is actually getting parsed from the
		music file
		*/
		PApplet.println(frequencySpectrum.specSize());
		PApplet.println(frequencySpectrum.getBand(1));
	}

	public void playMusicFile()
	{
		musicPlayer.play(); //Play the audio file
	}
	
	public void rewindMusicFile()
	{
		if(musicPlayer.isPlaying())
		{
		musicPlayer.pause();
		musicPlayer.rewind(); //rewinds the audio file
		musicPlayer.close();
		}
	}
	
	public void displayMetaData()
	{
		/*
		 * Changed the original method so the metadata is 
		 * displayed on the applet and not the console
		 * 
		 */
		parent.fill(0, 102, 153, 204);
		parent.text("Filename: " + musicMetaData.fileName(),metaXPos,((parent.displayHeight / 8) * 5) + (metaSpacing * 1));
		parent.text("Title: " + musicMetaData.title(),metaXPos,((parent.displayHeight / 8) * 5) + (metaSpacing * 2));
		parent.text("Track: " + musicMetaData.track(),metaXPos,((parent.displayHeight / 8) * 5) + (metaSpacing * 3));
		parent.text("Artist: " + musicMetaData.author(),metaXPos,((parent.displayHeight / 8) * 5) + (metaSpacing * 4));
		parent.text("Composer: " + musicMetaData.composer(),metaXPos,((parent.displayHeight / 8) * 5)+ (metaSpacing * 5));
		parent.text("Genre: " + musicMetaData.genre(),metaXPos,((parent.displayHeight / 8) * 5) + (metaSpacing * 6));
		parent.text("Time: : " + musicMetaData.length(),metaXPos,((parent.displayHeight / 8) * 5) + (metaSpacing * 7));
	}
	
	public void displayWaveForm()
	{
		/*
		 * Spawn sound demon, needs to be fixed, most likely to do with the buffer length
		 * set when file is being read in
		 */
		parent.stroke(255);
		for(int buffer = 0; buffer < musicPlayer.bufferSize() - 1; ++buffer)
		{
			parent.line(buffer + (parent.displayWidth / 2),50 + musicPlayer.left.get(buffer) * 50,buffer + (parent.displayWidth / 2),musicPlayer.left.get(buffer + 1) * 50); //x1,y1,x2,y2
			parent.line(buffer + (parent.displayWidth / 2),150 + musicPlayer.right.get(buffer) * 50,buffer + (parent.displayWidth / 2),150 + musicPlayer.right.get(buffer + 1) * 50); //x1,y1,x2,y2
		}
	}
	
	
	
}