package domain;

/**
 * @author MehmetUstek
 *
 */
public class SaveLoadAdapter implements ISaveLoad{
	/**
	 * OVERVIEW: This class is saver and loader class adapter class which uses the specified ISaveload instance's save and load method.
	 */
	
	ISaveLoad saveload;
	public SaveLoadAdapter(ISaveLoad saveload) {
		this.saveload= saveload;
	}
	@Override
	public void saveGame() {
		/**
		 * @modifies the saved file or database.
		 * @effects save the game using specified ISaveLoad instance's saveGame method.
		 * The saved instances can be any file or database.
		 */
		saveload.saveGame();
	}
	@Override
	public void loadGame() {
		/**
		 * @requires already saved game file or database.
		 * @effects brings the game from file or database and updates controller by invoked methods and game objects.
		 */
		saveload.loadGame();
	}

}
