package Controler;

public class SolveCommand extends FunctionalCommand implements Command
{
	@Override
	public void execute()
	{
		this.getmM().solveLevel();
	}

}
