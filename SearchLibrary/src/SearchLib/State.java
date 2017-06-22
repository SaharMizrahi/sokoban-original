package SearchLib;

public class State<T>
{
	private State<T> cameFrom;
	private double cost;
	private T state;
	private ComplexAction action;
	

	
	@Override
	public int hashCode()
	{
		return state.hashCode();
		
	}
	public ComplexAction getAction()
	{
		
		return action;
	}
	public void setAction(ComplexAction action2)
	{
		this.action = action2;
	}
	public State<T> getCameFrom()
	{
		
		return cameFrom;
	}
	public void setCameFrom(State<T> cameFrom)
	{
		this.cameFrom = cameFrom;
	}
	public double getCost()
	{
		return cost;
	}
	public void setCost(double cost)
	{
		this.cost = cost;
	}
	public T getState()
	{
		return state;
	}
	public void setState(T state)
	{
		this.state = state;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		
		
		return state.equals(((State)obj).state);
		
	}
	public State(State<T> cameFrom, double cost, T state,ComplexAction action) {
		super();
		this.cameFrom = cameFrom;
		this.cost = cost;
		this.state = state;
		this.action = action;
	}
	public State(State<T> otherState) {
		super();
		// TODO Auto-generated constructor stub
		this.cameFrom=otherState.cameFrom;
		this.cost=otherState.cost;
		this.state=otherState.state;
		this.action=otherState.action;
		
	}
	public State() {
		super();
		// TODO Auto-generated constructor stub
		this.cameFrom=null;
		this.cost=100000;
		this.action=null;
		this.state=null;
	}


}
