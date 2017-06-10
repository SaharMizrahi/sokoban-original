package StripsLib;

import java.util.LinkedList;

import SearchLib.Action;

public class PlanAction extends Predicate
{

	private LinkedList<Action> subActions;
	private Clause preConditions,effects;
	private Action action;

	
	public LinkedList<Action> getSubActions()
	{
		return subActions;
	}


	public void setSubActions(LinkedList<Action> subActions)
	{
		this.subActions = subActions;
	}


	public Action getAction()
	{
		return action;
	}


	public void setAction(Action action)
	{
		this.action = action;
	}


	public Clause getPreConditions()
	{
		return preConditions;
	}


	public void setPreConditions(Clause preConditions)
	{
		this.preConditions = preConditions;
	}


	public Clause getEffects()
	{
		return effects;
	}


	public void setEffects(Clause effects)
	{
		this.effects = effects;
	}


	public PlanAction(String type, String id, String value) {
		super(type, id, value);
		// TODO Auto-generated constructor stub
	}
}
