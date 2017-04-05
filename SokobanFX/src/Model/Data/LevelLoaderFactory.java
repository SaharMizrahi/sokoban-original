package Model.Data;
import java.util.HashMap;
public class LevelLoaderFactory {
	HashMap<String,LevelLoaderCreator> LLHM;

	public HashMap<String, LevelLoaderCreator> getLLHM() {
		return LLHM;
	}
	public void setLLHM(HashMap<String, LevelLoaderCreator> lLHM) {
		LLHM = lLHM;
	}
	public LevelLoaderFactory() {
		
		LLHM=new HashMap<String,LevelLoaderCreator>();
		LLHM.put("txt",new TextLoaderCreator());
		LLHM.put("xml", new XmlLoaderCreator());
		LLHM.put("obj", new ObjectLoaderCreator());

	}
	public class ObjectLoaderCreator implements LevelLoaderCreator
	{

		
		public LevelLoader create() {
			// TODO Auto-generated method stub
			return new MyObjectLevelLoader();
		}
		
	}
	public class TextLoaderCreator implements LevelLoaderCreator
	{

		
		public LevelLoader create() {
			// TODO Auto-generated method stub
			return new MyTextLevelLoader();
		}
		
	}
	public class XmlLoaderCreator implements LevelLoaderCreator
	{

		
		public LevelLoader create() {
			// TODO Auto-generated method stub
			return new MyXMLLevelLoader();
		}
		
	}
	
}
