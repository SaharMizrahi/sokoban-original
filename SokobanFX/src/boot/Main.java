package boot;
/*Gal Ezra and Sahar Mizrahi Sokoban project
 * */

import db.Member;
import db.RecLevel;
import db.Record;
import db.RecordKey;
import javafx.application.Application;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;


public class Main extends Application {

	MediaPlayer m;
	@Override
//sasasasa
	public void start(Stage primaryStage) {
		/*try {
			System.out.println("test");
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
		}*/
	}
	public void stop()
	{

	}
	public static void main(String[] args) {

/*

		launch(args);
		*/
		
		Member m1=new Member("c","1234");
		Member m2=new Member("d","5678");
		RecLevel l=new RecLevel(1,10,2);
		Record r1=new Record(new RecordKey("c",1),30,5);
		Record r2=new Record(new RecordKey("d",1),31,5);

		m1.addRecord();
		System.out.println("finish update");

		m2.addRecord();
		l.addRecord();
		r1.addRecord();
		r2.addRecord();
		System.out.println("finish update");
		



	}
}
