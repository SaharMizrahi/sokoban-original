package StripsLib;

import java.util.Set;

public interface Plannable
{
	Clause getGoal();
	Clause getKnowledgebase();
	Set<PlanAction> getSatisfyingActions(Predicate topPredicate);

}
