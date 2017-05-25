package SearchLib;

import java.util.ArrayList;
import java.util.HashMap;

public interface Searchable<T> {
	State<T> getInitialState();
	State<T> getGoalState();
	//List<State<T>> getAllPossibleStates(State<T> state);
	HashMap<Action, State<T>> getAllPossibleMoves(State<T> state);
	

}
