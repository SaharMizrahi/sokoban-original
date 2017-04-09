package Controler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

import Model.Data.Level;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

public class ExitCommand extends FunctionalCommand implements Command {

	public ExitCommand(Level lev) {
		super(lev);
		// TODO Auto-generated constructor stub
	}
	public void execute() throws FileNotFoundException, IOException, ClassNotFoundException, Exception
	{
		FunctionalCommand fc;
		Scanner sc=new Scanner(System.in);
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Exiting Game");
		alert.setHeaderText("Befor exit,do you want to save the level status?");
		alert.setContentText("Choose your option.");

		ButtonType buttonTypeOne = new ButtonType("Yes");
		ButtonType buttonTypeTwo = new ButtonType("No");
		ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);
		Optional<ButtonType> result = alert.showAndWait();
		if(result.get()==buttonTypeOne)
		{
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Save Level Dialog");
			dialog.setHeaderText("Saving...");
			dialog.setContentText("Please enter file name.type:");
			dialog.showAndWait();
			fc=new SaveCommand(this.getLev());
			fc.setStr("save "+dialog.getResult());
			System.out.println("check");

			fc.execute();
			
			System.out.println("Thank you for playing Sokoban,exit now from the game...");
			System.exit(0);
		}
		else if(result.get()==buttonTypeTwo)
		{
			System.out.println("Thank you for playing Sokoban,exit now from the game...");

		}
		else if(result.get()==buttonTypeCancel)
		{
			alert.close();
		}



			/*System.out.println("exitCMD check");
			System.out.println("befor you exit, do you want to save your level? Yes/No?");
			
			String s=sc.next();
			if (s=="yes")
			{
				FC=new SaveCommand(this.getLev());
				System.out.println("enter file in this form : NAME.TYPE");
				s=sc.next();
				FC.setStr("save "+s);
				FC.execute();
				System.out.println("Thank you for playing Sokoban,exit now from the game...");
				//System.exit(0);
			}
			else if (s=="no")
			{
				System.out.println("Thank you for playing Sokoban,exit now from the game...");
				//System.exit(0);
			}
			System.out.println("wrong answer,try again in the main menu...");
			sc.close();*/
		}
		//System.exit(0);
		
		
	
	


}
