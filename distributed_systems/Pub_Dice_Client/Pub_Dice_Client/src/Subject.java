/**
 * Observer pattern being applied.
 * This is the Subject interface.
 * The interface to be observed.
 * 
 * @author igMoreira
 *
 */
public interface Subject {
	
	/**
	 * Allows observers subscribe to this
	 * subject so they can start listening to changes
	 * 
	 * @param o: An observer who wants to receive content
	 * from the subject.
	 */
	public void subscribe(Observer o);
	
	/**
	 * As soon as the subject changes it notifies
	 * all the observers so everybody is updated.
	 */
	public void notifyObservers();
}
