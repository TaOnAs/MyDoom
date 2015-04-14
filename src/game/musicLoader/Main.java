/*
 * 
 * Author:Ciaran Strutt
 * Date:08/04/2015
 * Description:Loads music files player wants to add to game and passes them to the analyzer class
 * 
 */
package src.game.musicLoader;
//

import java.io.File;

import processing.core.PApplet; //PApplet is in here
import ddf.minim.*; //Processing sound methods
import ddf.minim.analysis.FFT;

public class Main extends PApplet{

	public Minim musicLoader; //Create a variable that will be used to instantiate a minim object
	public AudioPlayer musicPlayer; //Object that will actually play the sound
	public AudioMetaData musicMetaData;
	public FFT frequencySpectrum;
	public boolean fileLoaded;
	
	public void setup()
	{
		size(512,200);
		smooth();
		noLoop(); //Run draw() once
		/*
		 * draw() was being ran even before the file was loaded but what was being drawn
		 * is dependent on data in the music file being loaded 
		 */
		fileLoaded = false; 
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
		if(musicFile.isFile() != true) //Make sure the file points to something, will add audio extension filtering 
		{
			println("Error while loading file");
		}
		else
		{
			musicLoader = new Minim(this);
			musicPlayer = musicLoader.loadFile(musicFile.getAbsolutePath(),512); //Load the music file into the musicPlayer
			fileLoaded = true;
			println("File loaded");
			parseMetaData(); //Only want to call these 3 methods once
			setupSpectrum(); //Having them in setup() was causing the rest of the program not to work
			playMusicFile();
			/*
			 * File is loaded so we have the data we need to draw the spectrum 
			 * so re-enable looping of the draw() method
			 */
			loop(); 
		}
	}
	 
	public void parseMetaData()
	{
		println("Metadata reached");
		musicMetaData = musicPlayer.getMetaData(); //Gets the metadata for the current file loaded into musicPlayer
		println("Song information"); //Outputs some extra song information
		println("Filename:" + musicMetaData.fileName());
		println("Author:" + musicMetaData.author());
		println("Copyright:" + musicMetaData.copyright());
		println("Genre:" + musicMetaData.genre());
		println("Encoded:" + musicMetaData.encoded());
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
		println(frequencySpectrum.specSize());
		println(frequencySpectrum.getBand(1));
	}

	public void playMusicFile()
	{
		musicPlayer.play(); //Play the audio file
	}
	
	public void displayWaveForm()
	{
		/*
		 * Spawn sound demon, needs to be fixed, most likely to do with the buffer length
		 * set when file is being read in
		 */
		background(0);
		stroke(255);
		for(int buffer = 0; buffer < musicPlayer.bufferSize() - 1; ++buffer)
		{
			line(buffer,50 + musicPlayer.left.get(buffer) * 50,buffer + 1,musicPlayer.left.get(buffer + 1) * 50);
			line(buffer,150 + musicPlayer.right.get(buffer) * 50,buffer + 1,150 + musicPlayer.right.get(buffer + 1	) * 50);
		}
	}
	
	public void draw()
	{
		/*
		 * Only draw the spectrum if the file is loaded
		 * otherwise open a file dialog box to allow file 
		 * selection
		 */
		if(fileLoaded == false)
		{
			showFileDialog();
		}
		else if(fileLoaded == true)
		{
			//displaySpectrum(); Will come back to this method, going to work on drawing the waveform
			displayWaveForm();
		}
	}
	
	
}