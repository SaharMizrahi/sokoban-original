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


	
	protected LinkedList<Action> backTrace(State<T> goalState) 
	{

		LinkedList<Action> list=new LinkedList<>();
		State<T> current=goalState;
		while(current!=null)
		{

			if(current.getAction()!=null)
			{

				if(current.getAction().getAction()!=null)
				{
					list.addFirst(current.getAction().getAction());
					if(current.getAction().getHistory()!=null)
					{
						for(int i=current.getAction().getHistory().size()-1;i>=0;i--)
						{
							list.addFirst(current.getAction().getHistory().get(i));
						}
					}
				}
			}
			

			current=current.getCameFrom();
		}

		return list;
		
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
