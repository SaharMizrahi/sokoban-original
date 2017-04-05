package Controler;

import java.io.FileNotFoundException;
import java.io.IOException;

import Model.ModelInterface;
import Model.MyModel;
import Model.Data.Level;
import view.MyView;

public class FunctionalCommand implements Command {
	private ModelInterface mM;
	
	private String str;

	public void updateCommand(FunctionalCommand fc)
	{
		
		this.mM.setCurrentLevel(fc.mM.getCurrentLevel());
	}
	public FunctionalCommand() {
		super();
		// TODO Auto-generated constructor stub
		mM=new MyModel();
		str="";
		
	}
	
	public ModelInterface getmM() {
		return mM;
	}
	public void setmM(ModelInterface mM) {
		this.mM = mM;
		this.setLev(mM.getCurrentLevel());
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public Level getLev() {
		return mM.getCurrentLevel();
	}

	public void setLev(Level lev) {
		
		mM.setCurrentLevel(lev);
		
	}

	public FunctionalCommand(Level lev) {
		super();
		// TODO Auto-generated constructor stub
		this.mM=new MyModel();
		
		this.setLev(lev);
		
	}

	public FunctionalCommand(ModelInterface mM, MyView mV, String str) {
		super();
		this.mM = mM;
		
		this.str = str;
	}
	@Override
	public void execute() throws FileNotFoundException, IOException, ClassNotFoundException, Exception {
		// TODO Auto-generated method stub

	}

}
