package boot;
/*Gal Ezra and Sahar Mizrahi Sokoban project
 * */

import java.io.File;
import java.util.List;

import org.hibernate.SessionFactory;

import Controler.SokobanController;
import Model.MyModel;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import view.DBWindowController;
import view.MainWindowController;


public class Main extends Application {

	MediaPlayer m;
	private static SessionFactory factory;
	@Override

	public void start(Stage primaryStage) {
		try {
			String musicFile="./resources/Music/song.mp3";
			Media song=new Media(new File(musicFile).toURI().toString());
			this.m=new MediaPlayer(song);
			FXMLLoader fl1=new FXMLLoader();
			FXMLLoader fl2=new FXMLLoader();
			BorderPane gameRoot = fl1.load(getClass().getResource("MainWindow.fxml").openStream());
			BorderPane DBRoot=fl2.load(getClass().getResource("test.fxml").openStream());
			MainWindowController mwc=fl1.getController();
			DBWindowController dbwc=fl2.getController();
			SokobanController sc=new SokobanController();
			MyModel mm=new MyModel();
			Scene gameScene = new Scene(gameRoot,650,650);
			Scene dbScene=new Scene(DBRoot, 650, 650);
			gameScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(gameScene);
			mwc.setDbScene(dbScene);
			
			mwc.setPrimarystage(primaryStage);
			dbwc.setPrimarystage(primaryStage);
			dbwc.setGameScene(gameScene);
			primaryStage.show();
			mwc.addObserver(sc);
			sc.setMv(mwc);
			sc.setMM(mm);
			mm.addObserver(sc);




			Thread serverThread=new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					sc.runServer();
				}
			});

			Thread musicThread=new Thread(new Runnable() {


						@Override
						public void run() {
							// TODO Auto-generated method stub


							m.cycleCountProperty().set(5);
							m.play();


						}
					});
			//runs the gui
			Platform.runLater(new Runnable()
			{

				@Override
				public void run()
				{
					// TODO Auto-generated method stub
					primaryStage.show();
				}
			});

			List<String> args=getParameters().getRaw();
			if (args.size()>0)
			{
				int port=0;
				int size=0;
				port=Integer.parseInt(args.get(1));

				sc.getMs().setPort(port);
			serverThread.start();
			}
			//musicThread.start();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void stop()
	{

	}
	public static void main(String[] args) {



		launch(args);
		
		
		



	}
}
