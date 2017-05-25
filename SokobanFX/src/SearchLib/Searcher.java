package SearchLib;

import java.util.List;

public interface Searcher<T> {
	
	public Solution search(Searchable<T> s);
	public int getNumberOfNodesEvaluated();

}
