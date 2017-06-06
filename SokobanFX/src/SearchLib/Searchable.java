package SearchLib;

import java.util.HashMap;

public interface Searchable<T>
{
	public State<T> getInitialState();
	public State<T> getGoalState();
	public HashMap<ComplexAction,State<T>> getAllPossibleStates(State<T> s);

}
