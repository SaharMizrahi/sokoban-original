package StripsLib;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Strips implements Planner
{
	private Plannable myPlannable;

	@Override
	public List<PlanAction> plan(Plannable plannable)
	{
		// TODO Auto-generated method stub
		Stack<Predicate> predicatesStack = new Stack<>();
		LinkedList<PlanAction> plan=new LinkedList<>();
		this.myPlannable = plannable;
		predicatesStack.push(plannable.getGoal());
		Predicate topPredicate = null;
		while (!predicatesStack.isEmpty()) {
			topPredicate = predicatesStack.peek();
			System.out.println("top is: "+topPredicate);
			if (!(topPredicate instanceof PlanAction))
			{
				if (!plannable.getKnowledgebase().isSatisfied(topPredicate))//unsatisfied
				{
					if(topPredicate instanceof Clause)//multiple and unsatisfied
					{
						Clause c=(Clause) topPredicate;
						for(Predicate p : c.getPredicatesSet() )
							predicatesStack.push(p);
					}
					else//single and unsatisfied
					{
						predicatesStack.pop();
						Set<PlanAction> actions=plannable.getSatisfyingActions(topPredicate);
						if(actions!=null)
						{
							for (PlanAction a : actions)
							{
								predicatesStack.push(a);
								predicatesStack.push(a.getPreConditions());
							}
						}
						else
						{
							if(predicatesStack.size()>1)//there is another predicate
							{
								System.out.println("trying another way");
								predicatesStack.pop();
								Predicate p=predicatesStack.pop();
								predicatesStack.push(topPredicate);
							}
							else
							{
								System.out.println("*****block*****");
								return null;
							}
						}
					}
				} 
				else
				{
					predicatesStack.pop();
				}
			}
			else//top is action
			{
				predicatesStack.pop();
				PlanAction action=(PlanAction)topPredicate;
				plannable.getKnowledgebase().update(action.getEffects());
				System.out.println("new kb: "+plannable.getKnowledgebase());
				plan.addLast(action);
			}
		}

		return plan;
	}

	public Strips() {
		super();
		// TODO Auto-generated constructor stub
	}

}
