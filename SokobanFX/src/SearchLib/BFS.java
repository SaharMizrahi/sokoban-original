package SearchLib;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class BFS<T> extends CommonSearcher<T>
{
	@Override
	public Solution search(Searchable<T> searchable)
	{
		openList=new PriorityQueue<>(new Comparator<State<T>>()
		{

			@Override
			public int compare(State<T> arg0, State<T> arg1)
			{
				// TODO Auto-generated method stub
				return (int)(arg0.getCost()-arg1.getCost());
			}
		});	
		HashSet<State<T>> closed=new HashSet<>();
		closed.clear();
		State<T> state=searchable.getInitialState();
		//state.setCameFrom(getFinalState());
		this.openList.add(state);
		State<T> currentState=null;
		while(!this.openList.isEmpty())
		{
			currentState=this.openList.poll();
			this.evaluatedNodes++;
			closed.add(currentState);
			if(currentState.equals(searchable.getGoalState()))
					{
			
						Solution solution=new Solution();
						solution.setActionList(backTrace(currentState));
						setFinalState(currentState);
						return solution;
					}
			HashMap<ComplexAction,State<T>> map=searchable.getAllPossibleStates(currentState);

				for(ComplexAction action : map.keySet())
				{
					State<T> s=map.get(action);
					if(!closed.contains(s))
					{
						if(!openList.contains(s))
							openList.add(s);
						else
						{
							for(State<T> os : openList)
							{
								if(os.equals(s))
								{
									if(s.getCost()<os.getCost())
									{
										openList.remove(os);
										os.setCost(s.getCost());
										openList.add(os);
									}
								}
							}
						}
					}

				}

			
		}
		return null;
	
		
	}

	public BFS() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
