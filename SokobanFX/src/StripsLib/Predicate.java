package StripsLib;

public class Predicate
{
	protected String type,id,value;

	public boolean equals(Predicate p)
	{
		return type.equals(p.type)&&id.equals(p.id)&&value.equals(p.value);
	}
	@Override
	public int hashCode()
	{
		return (type+id+value).hashCode();
	}
	@Override
	public String toString()
	{
		return type+"_"+id+"_"+value;
	}
	public boolean isContradictClause(Clause c)
	{
		for(Predicate p : c.getPredicatesSet())
		{
			if(isContradict(p))
				return true;
		}
		return false;
		
	}
	public boolean isSatisfiedClause(Clause c)
	
	{
		for(Predicate p : c.getPredicatesSet())
		{
			if(isSatisfied(p))
				return true;
		}
		return false;
	}
	public boolean isContradict(Predicate p)
	{
		if(p instanceof Clause)
			return isContradictClause((Clause) p);
		else
			return type.equals(p.type)&&id.equals(p.id)&&!value.equals(p.value);
		
	}

	public boolean isSatisfied(Predicate p)
	{
		if(p instanceof Clause)
			return isSatisfiedClause((Clause) p);
		else
			return (type.equals(p.type)&&(id.equals(p.id)||p.id.equals("?"))&&value.equals(p.value));
	}
	public Predicate(String type, String id, String value) {
		super();
		this.type = type;
		this.id = id;
		this.value = value;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	

}
