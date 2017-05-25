package SearchLib;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class BFS<T> extends CommonSearcher<T> {

	public BFS() {
		super();
	}
		@Override
		public Solution search(Searchable<T> s) {

			Queue<State<T>> open=new PriorityQueue<>(new Comparator<State<T>>() {

				@Override
				public int compare(State<T> o1, State<T> o2) {
					return (int)(o1.getCost()-o2.getCost());
				}
			});//staes to be evaluated
			HashSet<State<T>> closed = new HashSet<>(); //states already evaluated
			State<T> state=s.getInitialState();
		//	state.setCost(0);//////////////
			open.add(state);
			while(!open.isEmpty()){
				State<T> currstate=open.poll();//remove the best node

				if(currstate.equals(s.getGoalState()))
				{
					//System.out.println("!!!!!!!!!");
					return backTrace(currstate);
				}
				if(!closed.contains(currstate)){
				HashMap<Action, State<T>> map=s.getAllPossibleMoves(currstate);
				for (Action a : map.keySet()) {
					State<T> n=map.get(a);
					//System.out.println(n.toString());
					if((!closed.contains(n)&&(!open.contains(n)))){
						n.setCameFrom(currstate);
						n.setAction(a);
					//	n.setCost(n.getCost()+n.getCameFrom().getCost());///
						n.setCost(currstate.getCost()+1);
						//n.setCost(n.getCost()+1);
						evaluatedNodes++;
						open.add(n);

					//	n.setCost(n.getCost()+n.getCameFrom().getCost());

					}
					else{//if or open or close
						if(!open.contains(n)){
							if(n.getCost()>currstate.getCost()+1)
								n.setCost(currstate.getCost()+1);
						}
						else{//if isnt in close
							open.remove(n);
							n.setCost(currstate.getCost()+1);
							open.add(n);////////////
							//System.out.println(n.getCost());
							//n.setAction(a);

						}

					}
				}

			}
				closed.add(currstate);
			}
			System.out.println("no way");
			return null;

		}
	

}
