// --== CS400 File Header Information ==--
// Name: Andy Lin
// Email: <alin47@wisc.edu>
// Team: <NB>
// TA: <Daniel Finer>
// Lecturer: <Gary Dahl>
// Notes to Grader: <optional extra notes>
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class that loads all the data from a food data file into an ArrayList of food objects.
 * 
 * @author Andy Lin
 *
 */
public class FoodLoader {
  public static ArrayList<Food> load(String foodData) {
    File file = new File(foodData);
    ArrayList<Food> allFoods = new ArrayList<>();
    Scanner sc;
    String currFood[];
    String line;
    Food food;

    try {
      sc = new Scanner(file);
      sc.nextLine();    // skips the first four lines to proceed to the first food item.
      sc.nextLine();
      sc.nextLine();
      sc.nextLine();
      while (sc.hasNextLine()) { 
        line = sc.nextLine();
        currFood = line.split(","); // stores each nutritional value information into a different 
        for (int i = 0; i < 11; i++) { //  index of the string array.
          if (currFood[i].equals("NULL")) { 
            if (i != 1 && i != 2) { // ensures when parsing a null value to a double value, it is
              currFood[i] = "0";    // instead interpreted as 0.
            }
          }
        }
        
        // stores each index of the array as a variable for the food's nutritional value.
        Integer ID = Integer.parseInt(currFood[0]); 
        String name = currFood[1];
        String foodGroup = currFood[2].trim();
        Double calories = Double.parseDouble(currFood[3]);
        Double fatG = Double.parseDouble(currFood[4]);
        Double proteinG = Double.parseDouble(currFood[5]);
        Double carbohydrateG = Double.parseDouble(currFood[6]);
        Double sugarsG = Double.parseDouble(currFood[7]);
        Double fiberG = Double.parseDouble(currFood[8]);
        Double cholesterolMg = Double.parseDouble(currFood[9]);
        Double satFatsG = Double.parseDouble(currFood[10]);

        food = new Food(ID, name, foodGroup, calories, fatG, proteinG, carbohydrateG, sugarsG,
            fiberG, cholesterolMg, satFatsG);  // using these food variables, create a new food object.
        allFoods.add(food); 
      }
      sc.close();
    } catch (FileNotFoundException e) {
      System.out.println("The given file was not found.");
    }
    return allFoods;
  }
}
