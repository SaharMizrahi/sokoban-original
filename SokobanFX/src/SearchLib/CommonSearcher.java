package SearchLib;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public abstract class CommonSearcher<T> implements Searcher<T>
{
	private State<T> finalState;
	protected int evaluatedNodes;
	protected PriorityQueue<State<T>> openList;
	@Override
	public abstract Solution search(Searchable<T> searchable);


	
	protected LinkedList<Action> backTrace(State<T> goalState) {
		LinkedList<Action> actions = new LinkedList<>();
		State<T> currState = goalState;
		State<T> iterator=currState.getCameFrom();
		while (currState.getCameFrom() != null)
		{
			actions.addFirst(currState.getAction().getAction());
			if(currState.getAction().getHistory()!=null)
				for(int i=currState.getAction().getHistory().size()-1;i>=0;i--)
				{
					actions.addFirst((Action) currState.getAction().getHistory().toArray()[i]);
				}
			currState = iterator;
			iterator=currState.getCameFrom();

		}

		return actions;
	}
	@Override
	public int getNumberOfNodesEvaluated()
	{
		// TODO Auto-generated method stub
		return evaluatedNodes;
	}
	public CommonSearcher()
	{
		finalState=null;
		openList=new PriorityQueue<>(new Comparator<State<T>>()
		{

			@Override
			public int compare(State<T> arg0, State<T> arg1)
			{
				// TODO Auto-generated method stub
				return (int)(arg0.getCost()-arg1.getCost());
			}
		});
		evaluatedNodes=0;
	}
	
	protected State<T> popOpenList()
	{
		evaluatedNodes++;
		return openList.poll();
	}


	public CommonSearcher(int evaluatedNodes, PriorityQueue<State<T>> openList) {
		super();
		this.evaluatedNodes = evaluatedNodes;
		this.openList = openList;
	}



	public State<T> getFinalState()
	{
		return finalState;
	}



	public void setFinalState(State<T> finalState)
	{
		this.finalState = finalState;
	}



	public int getEvaluatedNodes()
	{
		return evaluatedNodes;
	}



	public void setEvaluatedNodes(int evaluatedNodes)
	{
		this.evaluatedNodes = evaluatedNodes;
	}



	public PriorityQueue<State<T>> getOpenList()
	{
		return openList;
	}



	public void setOpenList(PriorityQueue<State<T>> openList)
	{
		this.openList = openList;
	}


	

}
