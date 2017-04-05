package Model.Data;

import java.util.HashMap;

public class ObjectFactory  {
	private HashMap<String,Creator> HM;

	public ObjectFactory() {
		// TODO Auto-generated constructor stub
		HM=new HashMap<String,Creator>();
		HM.put("A", new CharacterCreator());
		HM.put("a", new CharacterCreator());//when its on desination
		HM.put("@",new WoodBoxCreator());
		HM.put("$", new WoodBoxCreator());
		HM.put("#", new WallCreator());
		HM.put("o", new BoxDestinyCreator());
		HM.put(" ", new FloorCreator());
	}

	
	public HashMap<String, Creator> getHM() {
		return HM;
	}


	public void setHM(HashMap<String, Creator> hM) {
		HM = hM;
	}


	public Item creatLevelObject(String s)
	{
		Creator c=HM.get(s);
		if (c!=null)
			return c.create();
		return null;
	}
	private class FloorCreator implements Creator
	{

		
		public Item create() {
			// TODO Auto-generated method stub
			return new Floor();
		}
		
	}
	private class CharacterCreator implements Creator
	{

		
		public Item create() {
			// TODO Auto-generated method stub
			return new Character();
		}
		
	}

	private class WallCreator implements Creator
	{

		
		public Item create() {
			// TODO Auto-generated method stub
			return new Wall();
		}
		
	}
	private class WoodBoxCreator implements Creator
	{

		
		public Item create() {
			// TODO Auto-generated method stub
			return new Box();
		}
		
	}
	private class BoxDestinyCreator implements Creator
	{

		
		public Item create() {
			// TODO Auto-generated method stub
			return new Destination();
		}
		
	}

}