// --== CS400 File Header Information ==--
// Name: Ivan Man
// Email: igman@wisc.edu
// Team: NB
// TA: Daniel
// Lecturer: Florian Heimerl
// Notes to Grader: NONE
import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
// import com.sun.java.util.jar.pack.Package.File;
import java.io.File;

class FoodBackEndTest {
  ArrayList<Food> foodList = new ArrayList<Food>(); 
  private FoodBackEnd myFoodBackEnd = new FoodBackEnd(); 
  
  /*
  @BeforeEach
  void before() {
    // ArrayList<Food> foodList = new ArrayList<Food>(); 
    FoodBackEnd myFoodBackEnd = new FoodBackEnd(); 
    // foodList = FoodLoader.load("FoodData.csv"); 
  }; 
  */
  
  // test food loader and the get method
  @Test
  void test() {
    // ArrayList<Food> foodList = new ArrayList<Food>(); 
    
    // foodList = LoadFoodData.loadFood("FoodData.csv"); 
    foodList = FoodLoader.load("FoodData.csv"); 
    
    System.out.println("Food List size: " + foodList.size()); 
    if (foodList.size() != 14164) {
      fail("Incorrect size");
    }
    
    System.out.println("foodList.get(0): " + foodList.get(0)); 
    if (!foodList.get(0).getName().equals("Pillsbury Golden Layer Buttermilk Biscuits Artificial Flavor Refrigerated Dough")) {
      fail("Incorrect get");
    }
  } 
  
  // addType test
  @Test
  void test1() {
    // FoodBackEnd myFoodBackEnd = new FoodBackEnd(); 
    // Type myType = new Type("Sweets"); 
    boolean addStatus = myFoodBackEnd.addType("Ice Cream"); 
    
    System.out.println("addStatus: " + addStatus); 
    if (!addStatus) {
      fail ("Incorrect status"); 
    }
    
    System.out.println("type size: " + myFoodBackEnd.getSize()); 
    assertEquals(23, myFoodBackEnd.getSize());
    // if (!foodList.get(1).name.equals("Pillsbury Cinnamon Rolls With Icing Refrigerated Dough")) {
     //  fail("Incorrect get");
    // }
  }
  
  // tests the calculate average stuff method and cart size
  @Test
  void backEndTest1() {
    // FoodBackEnd myFoodBackEnd = new FoodBackEnd(); 
    ArrayList<Food> cart = new ArrayList<>(); 
    Food strawberry = new Food(3794, "Strawberry", "Fruits", 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 
        100.0, 100.0); 
    Food apple = new Food(2187, "Apple", "Fruits", 200.0, 200.0, 200.0, 200.0, 200.0, 200.0, 200.0, 
        200.0); 
   // myFoodBackEnd.addFood("Fruits", strawberry); 
   // myFoodBackEnd.addFood("Fruits", apple); 
    
    cart.add(strawberry); 
    cart.add(apple); 
    System.out.println("get cart size: " + cart.size());
    assertEquals(2, cart.size());
    
    System.out.println("Calculate: " + myFoodBackEnd.calculate(cart));  
    assertEquals("Total Calories: 300.0\n" + 
        "Total Fat(g): 300.0\n" + 
        "Total Protein(g): 300.0\n" + 
        "Total Carbohydrate(g): 300.0\n" + 
        "Total Sugars(g): 300.0\n" + 
        "Total Fiber(g): 300.0\n" + 
        "Total Cholesterol(mg): 300.0\n" + 
        "Total Saturated Fats(g): 300.0\n" + 
        "\n" + 
        "Average Calories: 150.0\n" + 
        "Average Fat(g): 150.0\n" + 
        "Average Protein(g): 150.0\n" + 
        "Average Carbohydrate(g): 150.0\n" + 
        "Average Sugars(g): 150.0\n" + 
        "Average Fiber(g): 150.0\n" + 
        "Average Cholesterol(mg): 150.0\n" + 
        "Average Saturated Fats(g): 150.0\n", myFoodBackEnd.calculate(cart).toString());
    
    
  }
  
  // tests the getFoodOfType
  @Test
  void backEndTest2() {
    // FoodBackEnd myFoodBackEnd = new FoodBackEnd(); 
    ArrayList<Food> foodList = new ArrayList<>(); 
    
    System.out.println("number of sweet food: " + myFoodBackEnd.getFoodOfType("Sweets").size());   
    assertEquals(503, myFoodBackEnd.getFoodOfType("Sweets").size()); 
    
    System.out.println("1st sweet food: " + myFoodBackEnd.getFoodOfType("Sweets").get(0)); 
    assertEquals("Fruit Butters Apple", myFoodBackEnd.getFoodOfType("Sweets").get(0).getName());
    assertEquals(173.0, myFoodBackEnd.getFoodOfType("Sweets").get(0).getCalories());
    
    System.out.println("400th food: " + myFoodBackEnd.getFoodOfType("Sweets").get(399));
    assertEquals("Ice Creams Vanilla Light", myFoodBackEnd.getFoodOfType("Sweets").get(399).getName());
    assertEquals(180.0, myFoodBackEnd.getFoodOfType("Sweets").get(399).getCalories());
    
    System.out.println("number of sweet food: " + myFoodBackEnd.getFoodOfType("Fruits").size()); 
    assertEquals(456, myFoodBackEnd.getFoodOfType("Fruits").size()); 
  }
  
  // test allFood
  
  @Test
  void backEndTest3() {
    
    // System.out.println("allFood: " + myFoodBackEnd.allFood()); 
    
    // pick first, middle, and last food
    
    // System.out.println("allFood: " + myFoodBackEnd.allFood().length()); 
    String allFood = myFoodBackEnd.allFood(); 
    
    if (!allFood.contains("Fruit Butters Apple") || 
        !allFood.contains("Pillsbury Golden Layer Buttermilk Biscuits") ||
           // + "Artificial Flavor Refrigerated Dough") || 
        !allFood.contains("Industrial Oil As Ingredient In Food")) {
      fail("Incomplete contains of allFood"); 
    }
    
    
  }
  
  
  // test allTypes
  @Test
  void backEndTest4() {
    System.out.println("allTypes size: " + myFoodBackEnd.allTypes().length()); 
    System.out.println("allTypes: " + myFoodBackEnd.allTypes());
    
    if (!myFoodBackEnd.allTypes().contains("American Indian") || 
        !myFoodBackEnd.allTypes().contains("Grains and Pasta") || 
        !myFoodBackEnd.allTypes().contains("Vegetables")) {
      fail("Incorrect allTypes stuff"); 
    }
    
    }
    // test getFood
    @Test
    void backEndTest5() {
      
      Food myFood = myFoodBackEnd.getFood("Baked Foods", 
          "Pillsbury Golden Layer Buttermilk Biscuits Artificial Flavor Refrigerated Dough"); 
      
      assertEquals(307.0, myFood.getCalories());
      assertEquals(13.24, myFood.getFatG()); 
      assertEquals(5.88, myFood.getProteinG()); 
      assertEquals(5.88, myFood.getSugarsG()); 
    }
    
    
    // test removeFood and size of one food type
    
    @Test
    void backEndTest6() {
      ArrayList<Food> foodList = new ArrayList<>(); 
      
      System.out.println("test 6 number of sweets: " + myFoodBackEnd.getFoodOfType("Sweets").size());   

       Food myFood1 = myFoodBackEnd.getFood("Sweets", "Ice Creams Vanilla Light"); 
       System.out.println("myFood1: " + myFood1); 
       Food myFood = myFoodBackEnd.removeFood("Sweets", 
          "Ice Creams Vanilla Light"); 
       System.out.println("test 6 after remove number of sweets: " + myFoodBackEnd.getFoodOfType("Sweets").size());
       // System.out.println("Baked Foods- size after remove: " +  myType.foodHashTable.hashTable.length); 
       // assertEquals(9, myType.foodHashTable.hashTable.length); 
      System.out.println("remove result: " + myFoodBackEnd.getFood("Sweets", 
         "Ice Creams Vanilla Light")); 
      if (myFoodBackEnd.getFood("Sweets", 
         "Ice Creams Vanilla Light")
          != null) {
        fail("Remove failed"); 
      }
    }
    
    
    // test number of food
    @Test
    void backEndTest7() {
      System.out.println("number of food: " + myFoodBackEnd.numOfFood()); 
      assertEquals(14164, myFoodBackEnd.numOfFood());
      
      Food strawberry = new Food(3794, "Strawberry", "Fruits", 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 
          100.0, 100.0); 
      myFoodBackEnd.addFood("Fruits", strawberry); 
      
      assertEquals(14165, myFoodBackEnd.numOfFood());
    }
    
    // test remove type and size
    @Test
    void backEndTest8() {
      System.out.println("get size: " + myFoodBackEnd.getSize());
      assertEquals(22, myFoodBackEnd.getSize()); 
      myFoodBackEnd.removeType("Sweets"); 
      assertEquals(21, myFoodBackEnd.getSize());
    }
    
    // tests the clearType method
    @Test
    void backEndTest9() { 
      ArrayList<Food> foodList = new ArrayList<>(); 
      
      System.out.println("number of baked food: " + myFoodBackEnd.getFoodOfType("Baked Foods").size());   
      assertEquals(939, myFoodBackEnd.getFoodOfType("Baked Foods").size()); 
      // Type myType = new Type("Baked Foods"); 
      // int foodSize = myType.foodHashTable.hashTable.length; 
      // System.out.println("get num of food of type baked foods: " + foodSize);
      
      // System.out.println("size clear: " + myFoodBackEnd.clearType("Baked Foods")); 
      assertEquals(939, myFoodBackEnd.clearType("Baked Foods")); 
      
      System.out.println("number of baked food: " + myFoodBackEnd.getFoodOfType("Baked Foods").size());   
      assertEquals(0, myFoodBackEnd.getFoodOfType("Baked Foods").size());
    }
    
}   
   
 

