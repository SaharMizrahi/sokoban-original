package SearchLib;



public class State<T> {
	private State<T> cameFrom;
	private T state;
	private double cost;
	private Action action;
	
	
	
	@Override
	public boolean equals(Object o) {
		State<T> s = (State<T>)o;
		return state.equals(s.state);
	}
	@Override
	public int hashCode() {		
		return state.hashCode();
	}
		
	@Override
	public String toString() {		
		return state.toString();
	}
	public State(T state)
	{
		this.state=state;
	}
	public Action getAction() {
		return action;
	}
	public void setAction(Action action) {
		this.action = action;
	}
	public State<T> getCameFrom() {
		return cameFrom;
	}
	public void setCameFrom(State<T> cameFrom) {
		this.cameFrom = cameFrom;
	}
	public T getState() {
		return state;
	}
	public void setState(T state) {
		this.state = state;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	
	

}
