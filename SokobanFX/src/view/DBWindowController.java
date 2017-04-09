package view;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DBWindowController
{
	Stage primarystage;
	Scene gameScene;
	@FXML
	public void openGameScene()
	{
		if (primarystage!=null)
		{
			
			if(gameScene!=null)
			{
				primarystage.setScene(gameScene);
				primarystage.show();
				System.out.println("xff");

			}
		}
	}
	public Stage getPrimarystage()
	{
		return primarystage;
	}
	public void setPrimarystage(Stage primarystage)
	{
		this.primarystage = primarystage;
	}
	public Scene getGameScene()
	{
		return gameScene;
	}
	public void setGameScene(Scene gameScene)
	{
		this.gameScene = gameScene;
	}
	
	
}
