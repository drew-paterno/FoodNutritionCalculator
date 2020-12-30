import java.util.Hashtable;

public class Type implements Comparable<Type>{
	private String name;
	public HashTableMap<String, Food> foodHashTable = new HashTableMap<>();
	
	public Type(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	@Override
	public int compareTo(Type otherName) {
		return name.compareTo(otherName.getName());
	}

}
