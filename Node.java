// --== CS400 File Header Information ==--
// Name: <Jiaming Zhang>
// Email: <jzhang2254@wisc.edu>
// Team: <NB>
// TA: <Daniel Finer>
// Lecturer: <Florian Heimerl>
// Notes to Grader: <optional extra notes>
/**
 * a node class to bind key and value into a pair
 * @author Jiaming Zhang
 *
 * @param <KeyType> 
 * @param <ValueType>
 */
public class Node<KeyType, ValueType> {
	private Object key;
	private Object value;
		
	/**
	 * constructor, assign values to key and value
	 * @param key
	 * @param value
	 */
	public Node(KeyType key, ValueType value) {
		this.key = key;
		this.value = value;
	}
	/**
	 * getter for key
	 * @return the reference to key
	 */
	public Object getKey() {
		return key;
	}
	/**
	 * getter for value
	 * @return the reference to value
	 */
	public Object getValue() {
		return value;
	}

}
