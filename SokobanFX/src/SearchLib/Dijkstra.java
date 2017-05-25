package SearchLib;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Dijkstra<T> extends CommonSearcher<T> {

	@Override
	public Solution search(Searchable<T> s) {
		Queue<State<T>> minHeap = new PriorityQueue<State<T>>(new Comparator<State<T>>() {

			@Override
			public int compare(State<T> o1, State<T> o2) {
				return (int) (o1.getCost() - o2.getCost());
			}
		});
		HashSet<State<T>> closed = new HashSet<>();
		HashSet<State<T>> init = new HashSet<>();
		// initialize
		State<T> source = s.getInitialState();
		source.setCost(0);
		init.add(source);
		minHeap.add(source);




		/*
		 * Map<Action,State<T>> map=s.getAllPossibleMoves(source); for (Action a
		 * : map.keySet()) { State<T> state=map.get(a);
		 * if(!init.contains(state)) { state.setCost(Double.MAX_VALUE);
		 * init.add(state); minHeap.add(state); } }
		 */

		while (!minHeap.isEmpty()) {
			State<T> u = minHeap.poll();
			while (!closed.contains(u)) {
				Map<Action, State<T>> nehigbors = s.getAllPossibleMoves(u);
				for (Action a : nehigbors.keySet()) {
					State<T> state = nehigbors.get(a);
					/*
					 * if(!init.contains(state)) {
					 * state.setCost(Double.MAX_VALUE); init.add(state);
					 * minHeap.add(state); }
					 */

				}

				for (Action a : nehigbors.keySet()) {
					State<T> n = nehigbors.get(a);
					double alt = u.getCost() + 1;
					if (alt < n.getCost()) {
						minHeap.remove(n);
						n.setCost(alt);
						n.setCameFrom(u);
						minHeap.add(n);

					}

				}

				closed.add(u);
			}
		}
		State<T> goal = null;
		if (minHeap.contains(s.getGoalState())) {
			for (State<T> state1 : minHeap) {
				if (state1.equals(s.getGoalState())) {
					goal = state1;
				}
			}
		}

		if (goal != null) {
			return backTrace(goal);
		}

		else {
			System.out.println("no way");
			return null;
		}
	}

	@Override
	public int getNumberOfNodesEvaluated() {
		// TODO Auto-generated method stub
		return 0;
	}

}
