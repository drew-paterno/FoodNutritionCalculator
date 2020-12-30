// --== CS400 File Header Information ==--
// Name: <Jiaming Zhang>
// Email: <jzhang2254@wisc.edu>
// Team: <NB>
// TA: <Daniel Finer>
// Lecturer: <Florian Heimerl>
// Notes to Grader: <optional extra notes>
import java.util.LinkedList;
import java.util.NoSuchElementException;
/**
 * A hash table class that implements the MapADT
 * @author Jiaming Zhang
 *
 * @param <KeyType> generic type for key
 * @param <ValueType> generic type for value
 */
public class HashTableMap<KeyType, ValueType> {

	private int size;
	protected LinkedList<Node>[] hashTable;
    /**
     * A helper method to print out the contents of this hash table
     */
	public String display() {
		String result = "";
		for (int i = 0; i < hashTable.length; ++i) {
			if (hashTable[i] != null) {
				for (int j = 0; j < hashTable[i].size(); ++j) {
					result += hashTable[i].get(j).getValue() + "\n";
				}
			}
		}
		return result;
	}
	/**
	 * constructor, set the capacity of array to capacity
	 * @param capacity the capacity of the array
	 */
	public HashTableMap(int capacity) {
		hashTable = new LinkedList[capacity];
		this.size = 0;
	}
	/**
	 * default constructor
	 */
	public HashTableMap() {// with default capacity = 10
		hashTable = new LinkedList[10];
		this.size = 0;
	}
	/**
	 * private helper, called whenever a new node is added. If the load factor
	 * is bigger than .8, resize the array and rehash all the nodes
	 */
	private void resize() {
		if (size / (double) hashTable.length >= 0.8) {
			LinkedList<Node>[] newHashTable = new LinkedList[hashTable.length * 2];//resizing
			
			for (int i = 0; i < hashTable.length; ++i) {
				if (hashTable[i] != null) {
					for (int j = 0; j < hashTable[i].size(); ++j) {
						if (newHashTable[Math
								.abs(hashTable[i].get(j).getKey().hashCode() % newHashTable.length)] == null) {//avoid null pointer exception
							newHashTable[Math.abs(hashTable[i].get(j).getKey().hashCode()
									% newHashTable.length)] = new LinkedList<Node>();
						}
						newHashTable[Math.abs(hashTable[i].get(j).getKey().hashCode() % newHashTable.length)]//rehashing
								.add(hashTable[i].get(j));
					}
				}
			}
			hashTable = newHashTable;
		}
	}
	/**
	 * This method add a node to the hashTable if the key is not already stored
	 * @param key
	 * @param value
	 * @return boolean return true when a node is successfully added, false otherwise
	 * 
	 */
	public boolean put(Object key, Object value) {
		if(key == null) return false;
		
		if (((LinkedList<Node>) getLinkedList(key)) == null) {// check if there's anything stored at this index, if not,
															  // create a new LinkedList, and store key at index 0
			hashTable[Math.abs(key.hashCode() % hashTable.length)] = new LinkedList<Node>();
			Node node = new Node(key, value);
			((LinkedList<Node>) getLinkedList(key)).add(node);
			size++;
			resize();
			return true;
		}

		for (int i = 0; i < ((LinkedList<Node>) getLinkedList(key)).size(); ++i) {// check if the same key is already
																				  // stored
			if (((LinkedList<Node>) getLinkedList(key)).get(i).getKey().equals(key)) {
				return false;
			}
		}

		Node node = new Node(key, value);//The LinkedList is not null, and the key is not in it
		((LinkedList<Node>) getLinkedList(key)).add(node);
		size++;
		resize();
		return true;
	}
	/**
	 * private helper to locate the linked list at the key's index
	 * @param key
	 * @return the reference to the linked list at the key's corresponding index
	 */
	private Object getLinkedList(Object key) {
		return hashTable[Math.abs(key.hashCode() % hashTable.length)];
	}
	/**
	 * return the reference to the value associated with the key
	 * @param key
	 * @return return the reference to the value at the key index
	 */
	public Object get(Object key) {
		if(key == null) throw new NoSuchElementException();
		if (((LinkedList<Node>) getLinkedList(key)) == null)
			return null;

		for (int i = 0; i < ((LinkedList<Node>) getLinkedList(key)).size(); ++i) {
			if (((LinkedList<Node>) getLinkedList(key)).get(i).getKey().equals(key)) {
				return ((LinkedList<Node>) getLinkedList(key)).get(i).getValue();
			}
		}
		return null;
	}
	/**
	 * return the size of the hash table
	 * @return int the size of hash table
	 */
	public int size() {
		return size;
	}
	/**
	 * check if the key is stored in the hash table
	 * @param key 
	 * @return true if the key is stored, false if it isn't or if the key is null
	 */
	public boolean containsKey(Object key) {
		if(key == null) return false;
		if (((LinkedList<Node>) getLinkedList(key)) == null)
			return false;

		for (int i = 0; i < ((LinkedList<Node>) getLinkedList(key)).size(); ++i) {
			if (((LinkedList<Node>) getLinkedList(key)).get(i).getKey().equals(key)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * remove the node with the associated key, and return it's value
	 * @param key
	 * @return the reference to the value removed, null if the key is not stored or if the key is null
	 */
	public Object remove(Object key) {
		if(key == null) return null;
		if (getLinkedList(key) == null)
			return null;

		for (int i = 0; i < ((LinkedList<Node>) getLinkedList(key)).size(); ++i) {// check if the same key is already
																					// stored, remove it and return the
																					// value
			if (((LinkedList<Node>) getLinkedList(key)).get(i).getKey().equals(key)) {
				size--;
				return ((LinkedList<Node>) getLinkedList(key)).remove(i).getValue();
			}
		}
		return null;
	}
	/**
	 * clean the hashTable, remove all the nodes
	 */
	public void clear() {
		for (int i = 0; i < hashTable.length; ++i) {
			if (hashTable[i] != null)
				hashTable[i] = null;
		}
		size = 0;
	}

}
