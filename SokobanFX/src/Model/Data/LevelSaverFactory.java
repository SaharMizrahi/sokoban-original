package Model.Data;

import java.util.HashMap;

public class LevelSaverFactory {
	private HashMap<String,LevelSaverCreator> LSHM;
	public HashMap<String, LevelSaverCreator> getLSHM() {
		return LSHM;
	}
	public void setLSHM(HashMap<String, LevelSaverCreator> lSHM) {
		LSHM = lSHM;
	}
	public LevelSaverFactory() {
		// TODO Auto-generated constructor stub
		LSHM=new HashMap<String,LevelSaverCreator>();
		LSHM.put("txt", new TextLevelSaverCreator());
		LSHM.put("xml",  new XmlLevelSaverCreator());
		LSHM.put("obj", new ObjLevelSaverCreator());
		
	}
	
	public class TextLevelSaverCreator implements LevelSaverCreator
	{

		@Override
		public LevelSaver creat() {
			// TODO Auto-generated method stub
			return new MyTextLevelSaver();
		}
		
	}
	public class XmlLevelSaverCreator implements LevelSaverCreator
	{

		@Override
		public LevelSaver creat() {
			// TODO Auto-generated method stub
			return new MyXMLLevelSaver();
		}
		
	}
	public class ObjLevelSaverCreator implements LevelSaverCreator
	{

		@Override
		public LevelSaver creat() {
			// TODO Auto-generated method stub
			return new MyObjectLevelSaver();
		}
		
	}
}
