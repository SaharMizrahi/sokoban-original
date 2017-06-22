package SearchLib;

import java.util.LinkedList;

public class ComplexAction
{
	private Action action;
	private LinkedList<Action> history;
	public LinkedList<Action> getPath()
	{
		
		LinkedList<Action> list=new LinkedList<>();
		
		if(history!=null)
		{
			for(int i=0;i<history.size();i++)
				list.addLast((Action) history.toArray()[i]);
		}
		list.addLast(action);

		return list;
	}
	public Action getAction()
	{
		return action;
	}
	public void setAction(Action action)
	{
		this.action = action;
	}
	public LinkedList<Action> getHistory()
	{
		return history;
	}
	public void setHistory(LinkedList<Action> history)
	{
		this.history = history;
	}
	public ComplexAction(Action action, LinkedList<Action> history) {
		super();
		this.action = action;
		this.history = history;
	}
	@Override
	public String toString()
	{
		String str="";
		if (this.history!=null)
		{
			for(Action a: this.history)
				str+=a.toString()+",";
		}
		if(this.action!=null)
			str+=this.action+",";
		return str;
		
	}
	

}
