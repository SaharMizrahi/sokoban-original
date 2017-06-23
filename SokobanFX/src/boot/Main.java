package boot;
/*Gal Ezra and Sahar Mizrahi Sokoban project
 * */

import java.io.File;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import Controler.SokobanController;
import Model.MyModel;
import db.RecLevel;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javassist.compiler.ast.Member;
import view.DBWindowController;
import view.MainWindowController;

//
public class Main extends Application {

	private static SessionFactory factory;
	@Override

	public void start(Stage primaryStage) {
		try {
			MediaPlayer m;
			//initalizeDataBase(); אם תרצה להעלות קצת נתונים לדטא בייס אצלך
			String musicFile="./resources/Music/song.mp3";
			Media song=new Media(new File(musicFile).toURI().toString());
			m=new MediaPlayer(song);
			FXMLLoader fl1=new FXMLLoader();
			FXMLLoader fl2=new FXMLLoader();
			BorderPane gameRoot = fl1.load(getClass().getResource("MainWindow.fxml").openStream());
			BorderPane DBRoot=fl2.load(getClass().getResource("DBWindow.fxml").openStream());
			MainWindowController mwc=fl1.getController();
			DBWindowController dbwc=fl2.getController();
			SokobanController sc=new SokobanController();
			MyModel mm=new MyModel();
			Scene gameScene = new Scene(gameRoot,650,650);
			Scene dbScene=new Scene(DBRoot, 650, 650);
			gameScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(gameScene);
			mwc.setDBScene(dbScene);
			mwc.setPrimarystage(primaryStage);
			mwc.setDbwc(dbwc);
			dbwc.setPrimarystage(primaryStage);
			dbwc.setGameScene(gameScene);
			primaryStage.show();
			mwc.addObserver(sc);
			sc.setMv(mwc);
			sc.setMM(mm);
			mm.addObserver(sc);






			Thread musicThread=new Thread(new Runnable() {


						@Override
						public void run() {
							// TODO Auto-generated method stub

							
							//m.cycleCountProperty().set(5);
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
					musicThread.start();
				}
			});



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
	//so you will have all the data that in our data base
	public void initalizeDataBase()
	{
		
		Configuration configuration = new Configuration();
		configuration.configure();
		factory = configuration.buildSessionFactory();
		Transaction tx = null;
		int recID = 0;
		Session session = factory.openSession();
		try {
			tx = session.beginTransaction();
			session.save(new RecLevel(1, 10, 2));
			session.save(new RecLevel(2, 10, 3));
			session.save(new RecLevel(3, 10, 4));
			session.save(new Member("sahar"));
			session.save(new Member("gal"));
			session.save(new Member("eden"));
			session.save(new Member("roee"));
			session.save(new Member("maayan"));

			tx.commit();
			System.out.println("Record added...");
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
