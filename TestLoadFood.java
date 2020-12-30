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
import org.junit.jupiter.api.Test;
// import com.sun.java.util.jar.pack.Package.File;
import java.io.File;

class TestLoadFood {

  /*
   * Test the Food Loader and get, tests if size is right
   */
  @Test
  void test() {
    ArrayList<Food> foodList = new ArrayList<Food>(); 
    
    foodList = FoodLoader.load("FoodData.csv"); 
    
    System.out.println("Food List size: " + foodList.size()); 
    if (foodList.size() != 14164) {
      fail("Incorrect size");
    }
    
    System.out.println("foodList.get(0): " + foodList.get(0)); 
    // if (!foodList.get(0).name.equals("Pillsbury Golden Layer Buttermilk Biscuits Artificial Flavor Refrigerated Dough")) {
    if (!foodList.get(0).getName().equals("Pillsbury Golden Layer Buttermilk Biscuits Artificial Flavor Refrigerated Dough")) {
      fail("Incorrect get");
    }
  } 
  
  /*
   * Tests the FoodLoader with a smaller size of the csv
   */
  @Test
  void test1() {
    ArrayList<Food> foodList = new ArrayList<Food>(); 
    
    foodList = FoodLoader.load("FoodData1.csv"); 
    
    System.out.println("Food List size: " + foodList.size()); 
    if (foodList.size() != 495) {
      fail("Incorrect size");
    }
    
    System.out.println("foodList.get(1): " + foodList.get(1)); 
    if (!foodList.get(1).getName().equals("Pillsbury Cinnamon Rolls With Icing Refrigerated Dough")) {
      fail("Incorrect get");
    }
    assertEquals("Baked Foods", foodList.get(1).getFoodGroup()); 
    assertEquals(330.0, foodList.get(1).getCalories()); 
    
    System.out.println("foodList.get(1).getCalories(): " + foodList.get(1).getCalories());
    
    System.out.println("foodList.get(2): " + foodList.get(2)); 
    if (!foodList.get(2).getName().equals("Kraft Foods Shake N Bake Original Recipe Coating For Pork Dry")) {
      fail("Incorrect get");
    }
    
    System.out.println("foodList.get(3): " + foodList.get(3)); 
    assertEquals("George Weston Bakeries Thomas English Muffins", foodList.get(3).getName()); 
  }
  
  /*
   * Tests for invalid file name/file not found
   */
  @Test
  void test2() {
    try {
      ArrayList<Food> foodList = new ArrayList<Food>(); 
      String fileName = "FoodData2.csv"; 
      
      // foodList = LoadFoodData.loadFood("FoodData2.csv"); 
      File myFile = new File(fileName); 
     
      if (!myFile.exists()) {
        throw new FileNotFoundException("File not found"); 
      } else {
        foodList = FoodLoader.load(fileName); 
      }
      
    } catch (FileNotFoundException e1) {
      System.out.println("File not found");
    }
    
  }
  
  /*
   * Tests Food Loader and a few food object attributes and the attribute values are right
   */
  @Test
  void testFields() {
    ArrayList<Food> foodList = new ArrayList<Food>(); 
    
    foodList = FoodLoader.load("FoodData1.csv"); 
    
    System.out.println("foodList.get(1): " + foodList.get(1)); 
    if (!foodList.get(1).getName().equals("Pillsbury Cinnamon Rolls With Icing Refrigerated Dough")) {
      fail("Incorrect get");
    }
    assertEquals(167513, foodList.get(1).getID());
    assertEquals("Pillsbury Cinnamon Rolls With Icing Refrigerated Dough", foodList.get(1).getName());
    assertEquals("Baked Foods", foodList.get(1).getFoodGroup()); 
    assertEquals(330.0, foodList.get(1).getCalories()); 
    assertEquals(11.27, foodList.get(1).getFatG()); 
    assertEquals(4.34, foodList.get(1).getProteinG()); 
    assertEquals(53.42, foodList.get(1).getCarbohydrateG()); 
    assertEquals(21.34, foodList.get(1).getSugarsG()); 
    assertEquals(1.4, foodList.get(1).getFiberG()); 
    assertEquals(0.0, foodList.get(1).getCholesterolMg()); 
    assertEquals(3.25, foodList.get(1).getSatFatsG()); 
    
   System.out.println("foodList.get(100): " + foodList.get(100)); 
   assertEquals(167612, foodList.get(100).getID()); 
   assertEquals("Oopah (Tunicate) Whole Animal (Alaska Native)", foodList.get(100).getName());
   assertEquals("American Indian", foodList.get(100).getFoodGroup()); 
   assertEquals(67.0, foodList.get(100).getCalories()); 
   assertEquals(2.2, foodList.get(100).getFatG()); 
   assertEquals(11.7, foodList.get(100).getProteinG()); 
   assertEquals(0.0, foodList.get(100).getCarbohydrateG()); 
   assertEquals(0.0, foodList.get(100).getSugarsG()); 
   assertEquals(0.0, foodList.get(100).getFiberG()); 
   assertEquals(0.0, foodList.get(100).getCholesterolMg()); 
   assertEquals(0.0, foodList.get(100).getSatFatsG()); 
    
  }
  
  /*
   * Tests some value, when the source is NULL, it should be converted to 0
   */
  @Test
  void testNull() {
 ArrayList<Food> foodList = new ArrayList<Food>(); 
    
    foodList = FoodLoader.load("FoodData1.csv"); 
    
   
   System.out.println("foodList.get(11): " + foodList.get(11)); 
   assertEquals(0.0, foodList.get(11).getSugarsG()); 
   assertEquals(1.4, foodList.get(11).getFiberG());
   assertEquals(0.0, foodList.get(11).getCholesterolMg());
   
   
  }
  
//  /*
//   * tests the Food object toString() method
//   */
//  @Test
//  void testToString() {
// ArrayList<Food> foodList = new ArrayList<Food>(); 
//    
//    foodList = FoodLoader.load("FoodData1.csv"); 
//    
//   System.out.println("foodList.get(11): " + foodList.get(11)); 
//   
//   assertEquals("| | ID: 167523\n" + 
//       "| | Name: Pie Crust Deep Dish Frozen Unbaked Made With Enriched Flour\n" + 
//       "| | Food Group: Baked Foods\n" + 
//       "| | Calories: 468.0\n" + 
//       "| | Fat(g): 28.74\n" + 
//       "| | Protein(g): 5.52\n" + 
//       "| | Carbohydrate(g): 46.79\n" + 
//       "| | Sugars(g): 0.0\n" + 
//       "| | Fiber(g): 1.4\n" + 
//       "| | Cholesterol(mg): 0.0\n" + 
//       "| | Saturated Fats(g) 8.129", 
//       foodList.get(11).toString()); 
//   
//  }

  
  
}   
   
 
