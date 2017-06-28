package Model.Data;
import java.util.HashMap;
/**
 * 
 * @author Sahar Mizrahi and Gal Ezra
 *	this class is our level loader factory
 */
public class LevelLoaderFactory {
	HashMap<String,LevelLoaderCreator> LLHM;

	/**
	 * 
	 * @return the level loaders hash map
	 */
	public HashMap<String, LevelLoaderCreator> getLLHM() {
		return LLHM;
	}
	/**
	 * set new level loaders hash map
	 * @param lLHM-new level loader hash map
	 */
	public void setLLHM(HashMap<String, LevelLoaderCreator> lLHM) {
		LLHM = lLHM;
	}
	/**
	 * default constructor
	 */
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
