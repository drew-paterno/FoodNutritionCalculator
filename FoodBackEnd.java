
import java.util.ArrayList;
import java.util.NoSuchElementException;
/**
 * Back end of the Food Nutrition Calculator
 * @author Jiaming Zhang
 *
 */
public class FoodBackEnd extends RedBlackTree<Type>{
	private int size;
	private ArrayList<Food> foods;
	/**
	 * constructor, load in Food objects from the loader
	 */
	public FoodBackEnd() {
		foods = FoodLoader.load("FoodData.csv");
		for(Food f : foods) {
			if(!this.addFood(f.getFoodGroup(), f)) {
				this.addType(f.getFoodGroup());
				this.addFood(f.getFoodGroup(), f);
			}
		}
	}
	/**
	 * calculate the total and average nutrition of the foods in cart
	 * @param cart contains a list of Food objects 
	 * @return a String representation of the nutrition information
	 */
	public String calculate(ArrayList<Food> cart) {
		String result = "Total Calories: " + (Math.round(calculateHelper("calories", cart) * 1000.0)/1000.0) + "\n" + "Total Fat(g): "
				+ (Math.round(calculateHelper("fatG", cart) * 1000.0)/1000.0) + "\n" + "Total Protein(g): " + (Math.round(calculateHelper("proteinG", cart) * 1000.0)/1000.0) + "\n"
				+ "Total Carbohydrate(g): " + (Math.round(calculateHelper("carbohydrateG", cart) * 1000.0)/1000.0) + "\n" + "Total Sugars(g): "
				+ (Math.round(calculateHelper("sugarsG", cart) * 1000.0)/1000.0) + "\n" + "Total Fiber(g): " + (Math.round(calculateHelper("fiberG", cart) * 1000.0)/1000.0) + "\n"
				+ "Total Cholesterol(mg): " + (Math.round(calculateHelper("cholesterolMg", cart) * 1000.0)/1000.0) + "\n"
				+ "Total Saturated Fats(g): " + (Math.round(calculateHelper("satFatsG", cart) * 1000.0)/1000.0) + "\n" + "\nAverage Calories: "
				+ (Math.round((calculateHelper("calories", cart) / cart.size() )* 1000.0)/1000.0) + "\n" + "Average Fat(g): "
				+ (Math.round((calculateHelper("fatG", cart) / cart.size() )* 1000.0)/1000.0) + "\n" + "Average Protein(g): "
				+ (Math.round((calculateHelper("proteinG", cart) / cart.size() )* 1000.0)/1000.0) + "\n" + "Average Carbohydrate(g): "
				+ (Math.round((calculateHelper("carbohydrateG", cart) / cart.size() )* 1000.0)/1000.0) + "\n" + "Average Sugars(g): "
				+ (Math.round((calculateHelper("sugarsG", cart) / cart.size() )* 1000.0)/1000.0) + "\n" + "Average Fiber(g): "
				+ (Math.round((calculateHelper("fiberG", cart) / cart.size() )* 1000.0)/1000.0) + "\n" + "Average Cholesterol(mg): "
				+ (Math.round((calculateHelper("cholesterolMg", cart) / cart.size() )* 1000.0)/1000.0) + "\n" + "Average Saturated Fats(g): "
				+ (Math.round((calculateHelper("satFatsG", cart) / cart.size() )* 1000.0)/1000.0) + "\n";
		return result;
	}
	/**
	 * private helper, help to calculate the total nutrition value
	 * @param s, the String specifies which value to calculate
	 * @param cart, a list of Food objects
	 * @return the total value (double) of the specified nutrition of all Foods in cart
	 */
	private double calculateHelper(String s, ArrayList<Food> cart) {
		double result = 0;
		switch(s) {
			case "calories": 
				for(int i = 0; i < cart.size(); i++) {
					result += cart.get(i).getCalories();
				}
				break;
			case "fatG": 
				for(int i = 0; i < cart.size(); i++) {
					result += cart.get(i).getFatG();
				}
				break;
			case "proteinG": 
				for(int i = 0; i < cart.size(); i++) {
					result += cart.get(i).getProteinG();
				}
				break;
			case "carbohydrateG": 
				for(int i = 0; i < cart.size(); i++) {
					result += cart.get(i).getCarbohydrateG();
				}
				break;
			case "sugarsG": 
				for(int i = 0; i < cart.size(); i++) {
					result += cart.get(i).getSugarsG();
				}
				break;
			case "fiberG": 
				for(int i = 0; i < cart.size(); i++) {
					result += cart.get(i).getFiberG();
				}
				break;
			case "cholesterolMg": 
				for(int i = 0; i < cart.size(); i++) {
					result += cart.get(i).getCholesterolMg();
				}
				break;
			case "satFatsG": 
				for(int i = 0; i < cart.size(); i++) {
					result += cart.get(i).getSatFatsG();
				}
				break;
		}
		return result;
	}
	
	/**
	 * return a list of Food objects of the same type
	 * @param type String that specifies the food type
	 * @return a list of Food of the specified type
	 */
	public ArrayList<Food> getFoodOfType(String type) {
		if(this.getNode(type) == null) return null;
		ArrayList<Food> foodList = new ArrayList<>();
		Type target = this.getNode(type).data;
		for (int i = 0; i < target.foodHashTable.hashTable.length; ++i) {
			if (target.foodHashTable.hashTable[i] != null) {
				for (int j = 0; j < target.foodHashTable.hashTable[i].size(); ++j) {
					foodList.add((Food)target.foodHashTable.hashTable[i].get(j).getValue());
				}
			}
		}
		return foodList;
	}
	/**
	 * add a type to the red black tree
	 * @param type String that specifies the food type
	 * @return true if successfully added, false otherwise
	 */
	public boolean addType(String type) {
		try {
			this.insert(new Type(type));
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		size++;
		return true;
	}
	/**
	 * return a String contains all the names of all foods
	 * @return String contains all the names of all foods
	 */
	public String allFood() {
		return allFoodHelper(this.root);
	}
	/**
	 * private helper to allFood(), use recursion to traverse the tree
	 * @param root the root of RBT
	 * @return the String representation of all names of all foods
	 */
	private String allFoodHelper(Node<Type> root){
		String result = "";
		if(root != null) {
			result = allFoodHelper(root.leftChild) + root.data.foodHashTable.display() + allFoodHelper(root.rightChild);
		}
		return result;
	}
	/**
	 * return a String representation of all types within RBT
	 * @return String representation of all types within RBT
	 */
	public String allTypes() {
		return allTypeHelper(this.root);
	}
	/**
	 * private helper to allType()
	 * @param root, the root node of RBT
	 * @return String representation of all types within RBT
	 */
	private String allTypeHelper(Node<Type> root) {
		String result = "";
		if(root != null) {
			result += allTypeHelper(root.leftChild);
			result += root.data.toString() + "\n";
			result += allTypeHelper(root.rightChild);
		}
		return result;
	}
	/**
	 * Add a food to the database
	 * @param type the Type of food
	 * @param food the Food object itself
	 * @return true if successfully added, false otherwise
	 */
	public boolean addFood(String type, Food food) {
		if(this.getNode(type) == null) return false;
		Type target = this.getNode(type).data;
		return target.foodHashTable.put(food.getName(), food);
	}
	/**
	 * get a food specified by type and food name
	 * @param type the Type of food
	 * @param name name of the food
	 * @return the specified Food object 
	 */
	public Food getFood(String type, String name) {
		if(this.getNode(type) == null || this.getNode(type).data.foodHashTable.get(name) == null) return null;
		Type target = this.getNode(type).data;
		Food food = (Food) target.foodHashTable.get(name);
		return food;
	}
	/**
	 * remove a food from the database
	 * @param type the Type of food
	 * @param name name of the food
	 * @return  the specified Food object, null if the food is not in database
	 */
	public Food removeFood(String type, String name) {
		Type target = this.getNode(type).data;
		Food food = (Food) target.foodHashTable.remove(name);
		return food;
	}
	/**
	 * return the total number of Food objects in the database
	 * @return total number of Food objects in the database
	 */
	public int numOfFood() {
		return numOfFoodHelper(this.root);
	}
	/**
	 * private helper to numOfFood()
	 * @param root the root node
	 * @return the total number of Food object sin the database
	 */
	private int numOfFoodHelper(Node<Type> root) {
		int result = 0;
		if(root != null) {
			result += numOfFoodHelper(root.leftChild);
			result += root.data.foodHashTable.size();
			result += numOfFoodHelper(root.rightChild);
		}
		return result;
	}
	/**
	 * The size of the RBT (the number of nodes in the tree)
	 * @return the number of nodes in the tree
	 */
	public int getSize() {
		return size;
	}
	
	public int clearType(String type) {
		if(this.getNode(type) == null) throw new NoSuchElementException("Type is not in RBT");
		Type target = this.getNode(type).data;
		int cleared = target.foodHashTable.size();
		target.foodHashTable.clear();
		return cleared;
	}

  

  /**
   * return the Node that's specifies by the name 
   * @param gameName
   * @return the node specified by gameName
   */
  private Node<Type> getNode(String gameName) {
      if(gameName == null) throw new NullPointerException("This RedBlackTree cannot store null references.");
      return getHelper(gameName, root);
  }
  
  /**
   * private helper for get
   * @param name
   * @param currentNode
   * @return the node specified by name, null if the node is not in RBT
   */
  private Node<Type> getHelper(String name, Node<Type> currentNode){
      if (currentNode == null) return null;
      if (name.compareTo(currentNode.data.getName()) == 0) // name
          return currentNode;
      if (name.compareTo(currentNode.data.getName()) < 0) // name
          return getHelper(name, currentNode.leftChild);
      if (name.compareTo(currentNode.data.getName()) > 0) // name
          return getHelper(name, currentNode.rightChild);
      return null;
  }
  
  /**
   * private helper, helps to find the replacement for the deleted node
   * @param delete
   * @return the replacement, null if the deleted node has no children
   */
  private Node<Type> findReplace(Node<Type> delete) {
      Node<Type> replace = null;
      if (delete.leftChild == null && delete.rightChild != null) {
          replace = delete.rightChild;
      }

      if (delete.leftChild != null && delete.rightChild == null) {
          replace = delete.leftChild;
      }

      if (delete.leftChild != null && delete.rightChild != null) {// find in order predecessor
          replace = delete.leftChild;
          while (replace.rightChild != null) {
              replace = replace.rightChild;
          }
          delete.data = replace.data; // copy the data in in-order predecessor into the deleted node, then delete the
                                      // in-order predecessor
          delete = replace;
          replace = findReplace(delete);
      }
      return replace;
  }
  
  /**
   * remove the specified node from the tree
   * @param data the game name
   * @return the deleted Game object
   */
  public Type removeType(String data) {
      if(data == null || getNode(data) == null) return null;
      Node<Type> delete = this.getNode(data);
      
      Type temp = new Type(""); //deep copy the Game object
      
      Node<Type> replace = findReplace(delete);//this will also replace the content of delete with the content of in order predecessor if applicable
      
      if (delete.leftChild != null && delete.rightChild != null) {// if the content of delete is replaced by that of in order predecessor, delete the IOP
          delete = delete.leftChild;
          while (delete.rightChild != null) {
              delete = delete.rightChild;
          }
      }
      
      if(delete.equals(this.root) && delete.leftChild == null && delete.rightChild == null) {//if delete is the root, and is the only element
          root = null;
          size--;
          return temp;
      }
      
      removeHelper(delete, replace);
      
         //the following part actually delete the node and replace it
              if (replace == null) {                                                     // replace is null
                  if (delete.isLeftChild()) {
                      delete.parent.leftChild = null;
                  } else {
                      delete.parent.rightChild = null;
                  }
              }else if (delete.equals(root)){                                           // delete is root
                  replace.parent = null;
                  root = replace;
              }else if (delete.isLeftChild()) {                                         // delete is left child
                  replace.parent = delete.parent;
                  delete.parent.leftChild = replace;
              } else {                                                                   // delete is right child
                  replace.parent = delete.parent;
                  delete.parent.rightChild = replace;
              }
      size --;
      return temp;
  }
  
  /**
   * private helper, helps to do the rotation and recoloring, and cascading fix
   * @param the node to delete
   * @param the replacement node
   */
  private void removeHelper(Node<Type> delete, Node<Type> replace) {
      if(delete.equals(root)) return;
      if(delete.isBlack && (replace == null || replace.isBlack)) {       // case 2, both delete and replace are black
          if (delete.isLeftChild() && (delete.parent.rightChild == null || delete.parent.rightChild.isBlack)) { // case 2.1, sibling is black and has at least one red child
              if (delete.parent.rightChild.leftChild != null && !delete.parent.rightChild.leftChild.isBlack) {
                  rotate(delete.parent.rightChild.leftChild, delete.parent.rightChild);
                  rotate(delete.parent.rightChild, delete.parent);
                  if(delete.parent.isBlack) {                              //case 2.1, parent is black
                      delete.parent.parent.isBlack = true;
                  }else {                                                  //2.1, parent is red
                  delete.parent.isBlack = false;                          
                  delete.parent.parent.isBlack = true;
                  delete.parent.parent.rightChild.isBlack = false;
                  if (delete.parent.parent.rightChild.rightChild != null && !delete.parent.parent.rightChild.rightChild.isBlack) {//sibling has two red children
                      super.enforceRBTreePropertiesAfterInsert(delete.parent.parent.rightChild.rightChild);
                  }
                  }
              } else if (delete.parent.rightChild.rightChild != null && !delete.parent.rightChild.rightChild.isBlack) {
                  rotate(delete.parent.rightChild, delete.parent);
                  if(delete.parent.isBlack) delete.parent.parent.rightChild.isBlack = true;                        
              } else {                                                    // 2.2, sibling is black and has two black children
                  if (!delete.parent.isBlack) {
                      delete.parent.isBlack = true;
                      delete.parent.rightChild.isBlack = false;
                  } else {
                      removeHelper(delete.parent, delete.parent.rightChild);
                      delete.parent.rightChild.isBlack = false;
                  }

              }

          } else if (!delete.isLeftChild() && (delete.parent.leftChild == null || delete.parent.leftChild.isBlack)) { // case 2.1, when delete is right
              if (delete.parent.leftChild.rightChild != null && !delete.parent.leftChild.rightChild.isBlack) {
                  rotate(delete.parent.leftChild.rightChild, delete.parent.leftChild);
                  rotate(delete.parent.leftChild, delete.parent);
                  if(delete.parent.isBlack) {                              //case 2.1, parent is black
                      delete.parent.parent.isBlack = true;
                  }else{
                  delete.parent.isBlack = false;                               
                  delete.parent.parent.isBlack = true;
                  delete.parent.parent.leftChild.isBlack = false;
                  if (delete.parent.parent.leftChild.leftChild != null && !delete.parent.parent.leftChild.leftChild.isBlack) {//sibling has two red children
                      super.enforceRBTreePropertiesAfterInsert(delete.parent.parent.leftChild.leftChild);
                  }
                  }
              } else if (delete.parent.leftChild.leftChild != null && !delete.parent.leftChild.leftChild.isBlack) {
                  rotate(delete.parent.leftChild, delete.parent);
                  if(delete.parent.isBlack) delete.parent.parent.leftChild.isBlack = true;                              
              } else {                                                           // 2.2, sibling is black and has two black children
                  if (!delete.parent.isBlack) {
                      delete.parent.isBlack = true;
                      delete.parent.leftChild.isBlack = false;
                  } else {
                      removeHelper(delete.parent, delete.parent.leftChild);
                      delete.parent.leftChild.isBlack = false;
                  }

              }
          } else if (delete.isLeftChild() && !delete.parent.rightChild.isBlack) {// case 2.3
              rotate(delete.parent.rightChild, delete.parent);
              delete.parent.isBlack = false;
              delete.parent.parent.isBlack = true;
              removeHelper(delete, delete.parent.rightChild);
          } else if (!delete.isLeftChild() && !delete.parent.leftChild.isBlack) {// case 2.3
              rotate(delete.parent.leftChild, delete.parent);
              delete.parent.isBlack = false;
              delete.parent.parent.isBlack = true;
              removeHelper(delete, delete.parent.leftChild);//the replacement argument is wrong here, but delete's sibling is for sure black, so passed it in is the same as pass delete's replacement in
          }

      } else {                                                                   // case 1
          if (replace != null)
              replace.isBlack = true;
      }
  }

}
