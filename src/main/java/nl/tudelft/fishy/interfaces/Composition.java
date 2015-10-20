package nl.tudelft.fishy.interfaces;

/**
 * Composition is a list of elements which can behave like a single object,
 * according to the Composite design pattern.
 * @author Michiel
 *
 * @param <K> 
 * 						- Generic.
 */
public interface Composition<K> {
	
	/**
	 * Add an element.
	 * @param k 
	 * 					- to be added.
	 */
	void add(K k);
	
	/**
	 * Remove specific element K.
	 * @param k
	 * 					- to be removed.
	 */
	void remove(K k);
	
	/**
	 * Remove the i'th element.
	 */
	void remove(int i);
	/**
	 * Clear all the elements from the list.
	 */
	void clear();
	
}
