package StripsLib;

public class SokoPredicate extends Predicate
{

	public SokoPredicate(String type, String id, String value) {
		super(type, id, value);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean isContradict(Predicate p)
	{
		return super.isContradict(p) || (!id.equals(p.id)&& value.equals(p.value)||(value.equals(p.value)&&(!type.equals(p.type))));
		
	}

}
