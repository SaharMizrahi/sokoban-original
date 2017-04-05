package boot;


import java.io.File;
import java.util.List;

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
import view.MainWindowController;


public class Main extends Application {

	MediaPlayer m;
	@Override

	public void start(Stage primaryStage) {
		try {
			String musicFile="./resources/Music/song.mp3";
			Media song=new Media(new File(musicFile).toURI().toString());
			this.m=new MediaPlayer(song);
			FXMLLoader fl=new FXMLLoader();
			BorderPane root = fl.load(getClass().getResource("MainWindow.fxml").openStream());
			MainWindowController mwc=fl.getController();
			SokobanController sc=new SokobanController();
			MyModel mm=new MyModel();
			Scene scene = new Scene(root,650,650);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			mwc.addObserver(sc);
			sc.setMv(mwc);
			sc.setMM(mm);
			mm.addObserver(sc);

			sc.setMyStage(primaryStage);



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
				String s=args.get(1);
				int port=0;
				int size=0;
				port=Integer.parseInt(args.get(1));

				sc.getMs().setPort(port);
			serverThread.start();
			}
			musicThread.start();

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
