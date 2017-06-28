package Model.Data;
/**
 * 
 * @author Sahar Mizrahi and Gal Ezra
 *this interface define a method that create level loader
 */
public interface LevelLoaderCreator {
	/**
	 * 
	 * @return a new level loader
	 */
	public LevelLoader create();

}
