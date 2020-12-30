
/**
 * A class of object Food that is used to contain all the information about a certain
 * type of food.
 * @author Andy Lin
 *
 */
public class Food {
  private Integer ID;
  private String name;
  private String foodGroup;
  private Double calories;
  private Double fatG;
  private Double proteinG;
  private Double carbohydrateG;
  private Double sugarsG;
  private Double fiberG;
  private Double cholesterolMg;
  private Double satFatsG;

  /**
   * Initializes a food object's nutritional information given the nutritional information.
   * @param ID - The ID of the food.
   * @param name - The name of the food.
   * @param foodGroup - The food group the food is in.
   * @param calories - The amount of calories in the food.
   * @param fatG - The amount of grams of fat that is present in the food.
   * @param proteinG - The amount of grams of protein that is present in the food.
   * @param carbohydrateG - The amount of grams of carbohydrate that is present in the food.
   * @param sugarsG - The amount of grams of sugar that is present in the food.
   * @param fiberG - The amount of grams of fiber that is present in the food.
   * @param cholesterolMg - The amount of milligrams of cholesterol that is present in the food.
   * @param satFatsG - The amount of grams of saturated fats that is present in the food.
   */
  public Food(Integer ID, String name, String foodGroup, Double calories, Double fatG,
      Double proteinG, Double carbohydrateG, Double sugarsG, Double fiberG, Double cholesterolMg,
      Double satFatsG) {
    this.ID = ID;
    this.name = name;
    this.foodGroup = foodGroup;
    this.calories = calories;
    this.fatG = fatG;
    this.proteinG = proteinG;
    this.carbohydrateG = carbohydrateG;
    this.sugarsG = sugarsG;
    this.fiberG = fiberG;
    this.cholesterolMg = cholesterolMg;
    this.satFatsG = satFatsG;
  }
  
  /**
   * Returns the ID of the food.
   * @return the variable ID.
   */
  public Integer getID() {
    return this.ID;
  }
  
  /**
   * Returns the name of the food.
   * @return the variable name
   */
  public String getName() {
    return this.name;
  }
  
  /**
   * Returns the food group of the food.
   * @return the variables foodGroup
   */
  public String getFoodGroup() {
    return this.foodGroup;
  }
  
  /**
   * Returns the calories of the food.
   * @return  the variables calories
   */
  public Double getCalories() {
    return this.calories;
  }
  
  /**
   * Returns the grams of fat in the food.
   * @return the variable fatG
   */
  public Double getFatG() {
    return this.fatG;
  }
  
  /**
   * Returns the grams of protein in the food.
   * @return the variable proteinG
   */
  public Double getProteinG() {
    return this.proteinG;
  }
  
  /**
   * Returns the grams of carbohydrates in the food.
   * @return the variable carbohydrateG
   */
  public Double getCarbohydrateG() {
    return this.carbohydrateG;
  }
  
  /**
   * Returns the grams of sugars in the food.
   * @return the variable sugarsG
   */
  public Double getSugarsG() {
    return this.sugarsG;
  }
  
  /**
   * Returns the grams of fiber in the food.
   * @return the variable fiberG
   */
  public Double getFiberG() {
    return this.fiberG;
  }
  
  /**
   * Returns the Milligrams of cholesterol in the food.
   * @return the variable cholesterolMg
   */
  public Double getCholesterolMg() {
    return this.cholesterolMg;
  }
  
  /**
   * Returns the grams of saturated fats in the food.
   * @return the variable satFatsG
   */
  public Double getSatFatsG() {
    return this.satFatsG;
  }
  
  /**
   * Sets the new ID value of the food.
   * @param ID - the new ID
   */
  public void SetID(Integer ID) {
    this.ID = ID;
  }
  
  /**
   * Sets the new name value of the food.
   * @param name - the new name 
   */
  public void setName(String name) {
    this.name = name;
  }
  
  /**
   * Sets the new food group of the food.
   * @param foodGroup - the new food group
   */
  public void setFoodGroup(String foodGroup) {
    this.foodGroup = foodGroup;
  }
  
  /**
   * Sets the new calories of the food. 
   * @param calories - the new calories
   */
  public void setCalories(Double calories) {
    this.calories = calories;
  }
  
  /**
   * Sets the new grams of fat of the food.
   * @param fatG - the new grams of fat.
   */
  public void setFatG(Double fatG) {
    this.fatG = fatG;
  }
  
  /**
   * Sets the new grams of protein of the food.
   * @param proteinG - the new grams of protein.
   */
  public void setProteinG(Double proteinG) {
    this.proteinG = proteinG;
  }
  
  /**
   * Sets the new grams of carbohydrate of the food.
   * @param carbohydrateG - the new grams of carbohydrate.
   */
  public void setCarbohydrateG(Double carbohydrateG) {
    this.carbohydrateG = carbohydrateG;
  }
  
  /**
   * Sets the new grams of sugar of the food.
   * @param sugarsG - the new grams of sugar.
   */
  public void setSugarsG(Double sugarsG) {
    this.sugarsG = sugarsG;
  }
  
  /**
   * Sets the new grams of fiber of the food.
   * @param fiberG - the new grams of fiber.
   */
  public void setFiberG(Double fiberG) {
    this.fiberG = fiberG;
  }
  
  /**
   * Sets the new milligrams of cholesterol of the food.
   * @param cholesterolMg - the new milligrams of cholesterol.
   */
  public void setCholesterolMg(Double cholesterolMg) {
    this.cholesterolMg = cholesterolMg;
  }
  
  /**
   * Sets the new grams of saturated fats of the food.
   * @param satFatsG - the new grams of saturated fat.
   */
  public void setSatFatsG(Double satFatsG) {
    this.satFatsG = satFatsG;
  }
  
  /**
   * returns the name of the food.
   * @param name - the String array storing the name of the food.
   * @return A string representation of the name of the food.
   */
  private String getToStringName(String[] name) {
    String result = "";
    for(int i = 1; i < name.length+1; i++) {
      if(i%6 == 0 && i != name.length)
        result += name[i-1] + "\n\t";
      else
        result += name[i-1] + " ";
    }
    return result;
  }
  
  /**
   * Overrides the toString method and displays all nutritional information about the food.
   * @return - A string containing all nutritional information about the food.
   */
  @Override
  public String toString() {    
    return "ID: " + this.ID + "\nName: " + this.getToStringName(this.name.split(" ")) + "\nFood Group: " + this.foodGroup
        + "\nCalories: " + this.calories + "\nFat(g): " + this.fatG + "\nProtein(g): " + this.proteinG
        + "\nCarbohydrate(g): " + this.carbohydrateG + "\nSugars(g): " + this.sugarsG + "\nFiber(g): "
        + this.fiberG + "\nCholesterol(mg): " + this.cholesterolMg + "\nSaturated Fats(g) "
        + this.satFatsG;
  }
  



}
