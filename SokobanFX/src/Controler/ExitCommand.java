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
/**
 * 
 * @author Sahar Mizrahi and Gal Ezra
 *	This commands exit the program, safe exit
 */
public class ExitCommand extends FunctionalCommand implements Command
{

	/**
	 * Constructor with level parameter to functional command  supper class
	 * @param lev
	 */
	public ExitCommand(Level lev) {
		super(lev);
		// TODO Auto-generated constructor stub
	}

	/**
	 * implemented method
	 */
	public void execute() throws FileNotFoundException, IOException, ClassNotFoundException, Exception
	{
		FunctionalCommand fc;
		Scanner sc = new Scanner(System.in);

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Exiting Game");
		alert.setHeaderText("Befor exit,do you want to save the level status?");
		alert.setContentText("Choose your option.");

		ButtonType buttonTypeOne = new ButtonType("Yes");
		ButtonType buttonTypeTwo = new ButtonType("No");
		ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeOne) {
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Save Level Dialog");
			dialog.setHeaderText("Saving...");
			dialog.setContentText("Please enter file name.type:");
			dialog.showAndWait();
			fc = new SaveCommand(this.getLev());
			fc.setStr("save " + dialog.getResult());

			fc.execute();

			System.out.println("Thank you for playing Sokoban,exit now from the game...");
		} else if (result.get() == buttonTypeTwo) {
			System.out.println("Thank you for playing Sokoban,exit now from the game...");

		} else if (result.get() == buttonTypeCancel) {
			alert.close();
		}
	}

}
