package application;

import java.io.File;

import javafx.application.Application;
import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import view.MonopolyView;

public class Main extends Application {

   @FXML Button btn;

	MediaPlayer mediaPlayer;

	@Override
	public void start(Stage primaryStage) throws Exception {

	//Media music = new Media("images/music_Monopoly.mp3");
	/*File file = new File("images\\musique.mp3");  
	final Media music = new Media(file.toURI().toString());
		mediaPlayer=new MediaPlayer(music);
		mediaPlayer.setAutoPlay(true);
		mediaPlayer.setVolume(10);*/

		try { 
			new MonopolyView(primaryStage); 
		} 
		catch(Exception e) {e.printStackTrace();}
		}
	
		public static void main(String[] args) {
			launch(args);
		}
}
