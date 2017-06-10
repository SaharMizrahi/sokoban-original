package StripsLib;

import java.util.HashSet;

public class Clause extends Predicate
{

	private HashSet<Predicate> predicatesSet;

	public Clause(Predicate... predicates) {
		super("And", "", "");
		// TODO Auto-generated constructor stub
		predicatesSet = new HashSet<>();
		if (predicates != null) {
			for (Predicate p : predicates) {
				this.predicatesSet.add(p);
			}
			updateDescription();
		}

	}

	public HashSet<Predicate> getPredicatesSet()
	{
		return predicatesSet;
	}

	public void setPredicatesSet(HashSet<Predicate> predicatesSet)
	{
		this.predicatesSet = predicatesSet;
	}

	@Override
	public boolean isContradictClause(Clause c)
	{
		for (Predicate p : c.getPredicatesSet()) {
			if (!isContradict(p))
				return false;
		}
		return true;

	}

	@Override
	public boolean isSatisfiedClause(Clause c)
	{
		for (Predicate p : c.getPredicatesSet()) {
			if (!isSatisfied(p))
				return false;
		}
		return true;
	}

	@Override
	public boolean isContradict(Predicate p)
	{
		if (p instanceof Clause)
			return isContradictClause((Clause) p);
		else {
			for (Predicate predicate : predicatesSet) {
				if (p.isContradict(p))
					return true;
			}
			return false;
		}

	}

	@Override
	public boolean isSatisfied(Predicate p)
	{
		if (p instanceof Clause)
			return isSatisfiedClause((Clause) p);
		else {
			for (Predicate predicate : predicatesSet) {
				if (predicate.isSatisfied(p))
					return true;
			}
			return false;
		}
	}

	public void update(Predicate effect)
	{
		HashSet<Predicate> removeablePredicates = new HashSet<>();
		if (predicatesSet.size() > 0) {
			for (Predicate p : predicatesSet) {
				if (p != null) {
					if (effect instanceof Clause) {
						if (p.isContradictClause( (Clause) effect))

							removeablePredicates.add(p);
					} else {

						if (p.isContradict(effect))

							removeablePredicates.add(p);
					}
				}
			}
			predicatesSet.removeAll(removeablePredicates);
			if(effect instanceof Clause)
				updateClause((Clause)effect);
			else
				this.predicatesSet.add(effect);
			updateDescription();
		} else {
			predicatesSet.add(effect);

			updateDescription();
		}

	}
	private void updateClause(Clause c)
	{
		for(Predicate p : c.getPredicatesSet())
		{
			if(p instanceof Clause)
			{
				updateClause((Clause)p);
			}
			else
				this.predicatesSet.add(p);
		}
	}
	private void updateDescription()
	{
		value = "{";
		for (Predicate p : this.predicatesSet) {
			if (p != null) {
				this.predicatesSet.add(p);
				value += p.toString() + " & ";
			}
		}
		value += "}";
	}
}
