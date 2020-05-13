package view;

/**
 * Provides methods for adding or removing a GameCallback from a collection.
 * 
 * @author Ross Nye
 *
 */
public interface GameCallbackCollection
{
	/**
	 * Registers a {@link GameCallback}. The callback is placed in a collection.
	 * @param callback the {@link GameCallback} to be added to the collection.
	 * 
	 * @see view.GameCallback
	 */
	public void registerCallback(GameCallback callback);
	
	
	/**
	 * Removes supplied {@link GameCallback} from the collection of registered callbacks.
	 * @param callback the {@link GameCallback} to be removed.
	 * 
	 * @see view.GameCallback
	 */
	public void removeCallback(GameCallback callback);	
}
