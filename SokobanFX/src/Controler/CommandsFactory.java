package Controler;

import java.util.HashMap;

import Model.Data.Level;

public class CommandsFactory {
	private HashMap<String,CommandCreator> CM;
	
	public HashMap<String, CommandCreator> getCM() {
		return CM;
	}
	public void setCM(HashMap<String, CommandCreator> cM) {
		CM = cM;
	}
	public CommandsFactory()
	{
		CM=new HashMap<String,CommandCreator>();
		CM.put("display",  new DisplayLevelCommandCreator());
		CM.put("move", new MoveCommandCreator());
		CM.put("load", new LoadLevelCommandCreator());
		CM.put("save", new SaveLevelCommandCreator());
		CM.put("exit", new ExitCommandCreator());
	}
	public class ExitCommandCreator implements CommandCreator
	{

		@Override
		public FunctionalCommand create() {
			// TODO Auto-generated method stub
			return new ExitCommand(new Level());
		}
		
	}
	public class SaveLevelCommandCreator implements CommandCreator
	{

		@Override
		public FunctionalCommand create() {
			// TODO Auto-generated method stub
			return new SaveCommand(new Level());
		}
		
	}
	public class LoadLevelCommandCreator implements CommandCreator
	{

	
		public FunctionalCommand create() {
			// TODO Auto-generated method stub
			return new LoadLevelCommand(new Level());
		}
		
	}
	public class DisplayLevelCommandCreator implements CommandCreator
	{

		public FunctionalCommand create() {
			// TODO Auto-generated method stub
			return new DisplayLevelCommand(new Level());
		}
		
	}
	public class MoveCommandCreator implements CommandCreator
	{

		
		public FunctionalCommand create() {
			// TODO Auto-generated method stub
			return new MoveCommand(new Level());
		}
	
	}

}
