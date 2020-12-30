
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

/**
 * 
 * This is the front end of team NB's Food Nutrition Calculator project, constructed using JavaFX
 * 11.
 * 
 * @author Drew Paterno
 *
 */
public class FrontEnd extends Application {

  private FoodBackEnd backEnd = new FoodBackEnd(); // Reference to the project's back-end code.
  private ArrayList<Food> cart = new ArrayList<Food>(); // List containing the current contents of
                                                        // the cart.

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Food Nutrition Calculator");
    BorderPane main = new BorderPane();

    Label mainHeader = new Label("Nutrition Calculator");
    mainHeader.setStyle("-fx-font-size: 20px;-fx-font-weight: bold;");
    main.setTop(mainHeader);
    main.setAlignment(mainHeader, Pos.CENTER);

    // This segment of code initializes the fields in the bottom pane. This includes the exit
    // button.

    HBox bottomContent = new HBox(5);
    Button exitButton = new Button("Exit");
    exitButton.setOnAction((Event) -> {
      primaryStage.close();
    });
    Button directionsButton = new Button("Help");
    directionsButton.setOnAction((Event) -> {
      showDirections();
    });
    bottomContent.getChildren().add(exitButton);
    bottomContent.getChildren().add(directionsButton);
    main.setBottom(bottomContent);

    // This segment of code initializes the fields in the right pane. This includes the cart and
    // total nutrition facts.

    ListView<String> scrollFoodInfo = new ListView<String>(); // Food information panel is declared
                                                              // here so information about the food
                                                              // in the cart can be viewed.

    Label rightHeader = new Label("Cart");
    rightHeader.setUnderline(true);
    rightHeader.setStyle("-fx-font-size: 15px;");
    ListView<HBox> cartContents = new ListView<HBox>();
    cartContents.setOnMouseClicked((Event) -> {
      try {
        scrollFoodInfo.setItems(FXCollections.observableArrayList(
            ((Food) cartContents.getSelectionModel().getSelectedItem().getUserData()).toString()
                .split("\n")));
      } catch (Exception e) {
        // Sometimes a NullPointerException is thrown when you click through the list too fast...
      }
    }); // Adds the information of the selected item in the cart to the information panel.
    VBox allRightContents = new VBox(5);
    allRightContents.setAlignment(Pos.CENTER);
    ListView<String> nutritionFacts = new ListView<String>();
    Button calcNutrition = getCalculateButton(nutritionFacts);
    Button clearCart = new Button("Clear Cart");
    clearCart.setOnAction((Event) -> {
      cartContents.getItems().clear();
      cart.clear();
    }); // Removes all items from the cart.
    Label nutritionLabel = new Label("Total Nutrition Facts");
    nutritionLabel.setUnderline(true);
    allRightContents.getChildren().add(rightHeader);
    allRightContents.getChildren().add(cartContents);
    allRightContents.getChildren().add(clearCart);
    allRightContents.getChildren().add(nutritionLabel);
    allRightContents.getChildren().add(nutritionFacts);
    allRightContents.getChildren().add(calcNutrition);
    main.setRight(allRightContents);

    // This segment of code initializes the fields in the center pane. This includes the search
    // window.

    BorderPane center = new BorderPane();
    Label centerHeader = new Label("Search Results");
    centerHeader.setUnderline(true);
    centerHeader.setStyle("-fx-font-size: 15px;");
    ListView<HBox> centerPane = new ListView<HBox>();
    centerPane.setOnMouseClicked((Event) -> {
      try {
        scrollFoodInfo.setItems(FXCollections.observableArrayList(
            ((Food) centerPane.getSelectionModel().getSelectedItem().getUserData()).toString()
                .split("\n")));
      } catch (Exception e) {
        // Sometimes a NullPointerException is thrown when you click through the list too fast...
      }
    }); // Adds the information of the selected item in the search pane to the information panel.
    center.setCenter(centerPane);
    center.setTop(centerHeader);
    center.setAlignment(centerHeader, Pos.CENTER);
    center.setMargin(centerHeader, new Insets(5, 0, 5, 0));
    main.setCenter(center);

    // This segment of code initializes the fields in the left pane. This includes the search panel,
    // list of types, and food information panel.

    Label leftHeader = new Label("Search");
    leftHeader.setUnderline(true);
    leftHeader.setStyle("-fx-font-size: 15px;");
    TextField searchFoodInput = new TextField();
    TextField searchTypeInput = new TextField();
    searchFoodInput.promptTextProperty().set("Enter Name of Food");
    searchTypeInput.promptTextProperty().set("Enter Type of Food");
    HBox searchFields = new HBox();
    VBox allLeftFields = new VBox(5);
    HBox searchOptions = new HBox();
    Button searchFood = new Button("Search Food");
    Button searchType = new Button("Search Type");
    Text errorMessage = new Text("");
    ListView<String> typeList = new ListView<String>();
    Label typeLabel = new Label("Types of Food");
    typeLabel.setUnderline(true);
    Label infoLabel = new Label("Nutrition Facts");
    infoLabel.setUnderline(true);
    typeList.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent click) {
        if (click.getClickCount() == 1) {
          searchTypeInput.setText(typeList.getSelectionModel().getSelectedItem());
        } else {
          searchTypeInput.setAccessibleText(typeList.getSelectionModel().getSelectedItem());
          searchType.fire();
        }
      }
    }); // If an item in the list of food types is double clicked, the program will perform a "type
        // search" on the selected type of food. If an item is clicked once, the name of the food
        // type selected will be inserted into the type search field.
    typeList.setItems(FXCollections.observableArrayList(backEnd.allTypes().split("\n")));
    searchFood.setOnAction((Event) -> {
      try {
        Food newFood = backEnd.getFood(searchTypeInput.getText(), searchFoodInput.getText());
        if (newFood == null) { // If invalid input is given for a food search, the program will
                               // inform the user.
          errorMessage.setText("Invalid Input. Search is Case Sensitive.");
          errorMessage.setFill(Color.RED);
        } else { // Delivers the successfully searched food to the search results pane.
          centerPane.getItems().clear();
          HBox newFoodDisplay = new HBox();
          newFoodDisplay.setUserData(newFood);
          newFoodDisplay.setSpacing(5);
          Text nameOfFood = new Text(newFood.getName());
          Button addToCart = getAddButton(newFood, cartContents);
          newFoodDisplay.getChildren().add(addToCart);
          newFoodDisplay.getChildren().add(nameOfFood);
          centerPane.getItems().add(newFoodDisplay);
          errorMessage.setText("");
        }
      } catch (Exception e) { // If invalid input is given for a food search, the program will
                              // inform the user.
        errorMessage.setText("Invalid Input. Search is Case Sensitive.");
        errorMessage.setFill(Color.RED);
      }
    });
    searchType.setOnAction((Event) -> {
      ArrayList<Food> foodsOfType = backEnd.getFoodOfType(searchTypeInput.getText());
      if (foodsOfType == null) { // If invalid input is given for a type search, the program will
                                 // inform the user.
        errorMessage.setText("Invalid Input. Search is Case Sensitive.");
        errorMessage.setFill(Color.RED);
      } else { // Delivers the successfully searched food items corresponding with the inputed type
               // value to the search results pane.
        centerPane.getItems().clear();
        for (Food newFood : foodsOfType) {
          HBox newFoodDisplay = new HBox();
          newFoodDisplay.setUserData(newFood);
          newFoodDisplay.setSpacing(5);
          Text nameOfFood = new Text(newFood.getName());
          Button addToCart = getAddButton(newFood, cartContents);
          newFoodDisplay.getChildren().add(addToCart);
          newFoodDisplay.getChildren().add(nameOfFood);
          centerPane.getItems().add(newFoodDisplay);
        }
        errorMessage.setText("");
      }
    });
    searchFields.getChildren().add(searchTypeInput);
    searchFields.getChildren().add(searchFoodInput);
    searchOptions.getChildren().add(searchType);
    searchOptions.getChildren().add(searchFood);
    allLeftFields.getChildren().add(leftHeader);
    allLeftFields.getChildren().add(searchFields);
    allLeftFields.getChildren().add(errorMessage);
    allLeftFields.getChildren().add(searchOptions);
    allLeftFields.getChildren().add(typeLabel);
    allLeftFields.getChildren().add(typeList);
    allLeftFields.getChildren().add(infoLabel);
    allLeftFields.getChildren().add(scrollFoodInfo);
    searchOptions.setAlignment(Pos.CENTER);
    allLeftFields.setAlignment(Pos.CENTER);
    main.setLeft(allLeftFields);

    // Sets the desired margins for the main scene.

    main.setMargin(mainHeader, new Insets(5, 0, 0, 0));
    main.setMargin(center, new Insets(0, 2.5, 0, 2.5));
    main.setMargin(allLeftFields, new Insets(0, 2.5, 0, 5));
    main.setMargin(allRightContents, new Insets(0, 5, 0, 2.5));
    main.setMargin(bottomContent, new Insets(5, 5, 5, 5));

    // Launches the GUI.

    Scene mainScene = new Scene(main, 1100, 700);
    primaryStage.setScene(mainScene);
    primaryStage.show();
    showDirections();
  }

  /**
   * Private helper method which displays the "Help" window upon the launch of the program, or
   * whenever the user presses the help button in the bottom left of the main window.
   */
  private void showDirections() {
    Stage directionsStage = new Stage();
    directionsStage.setTitle("Directions");
    BorderPane content = new BorderPane();
    VBox centerContent = new VBox(5);

    Label title = new Label("How to Use the Nutrition Calculator");
    title.setStyle("-fx-font-size: 20px;-fx-font-weight: bold;");
    content.setTop(title);
    content.setAlignment(title, Pos.TOP_CENTER);

    Text help = new Text(
        "You can return to this window at any time by pressing the \"Help\" button in the bottom left of your window."
            + "                                                      ");

    Label searchTitle = new Label("Searching");
    searchTitle.setStyle("-fx-font-size: 15px;");
    searchTitle.setUnderline(true);
    Text search = new Text(
        "To search a type of food in the catalogue, you can either search for the desired type, or click an item from the list of types."
            + "\nYou can also double click a type from the list to search for all the food items of that type."
            + "\n"
            + "\nTo search for a specific food, first enter the type of food associated with the food you are searching for (process described above), and           "
            + "\nthen type in the name of the food you would like to search for." + "\n"
            + "\nTo view the nutritional information of any item in the \"Search Results\" pane or the \"Cart,\" simply click on the item you would like to view"
            + "\nmore information of." + "\n"
            + "\nAll search results will be shown in the center pane labeled \"Search Results\""
            + "\nPlease also note searches ARE case sensitive.");
    Label managingTitle = new Label("Managing Items in the Cart");
    managingTitle.setStyle("-fx-font-size: 15px;");
    managingTitle.setUnderline(true);
    Text managingText = new Text(
        "After searching for items following the instructions above, you can then either add an item to the cart by clicking on the + button next to the"
            + "\nfood you would like to add, or you can remove items from the cart by pressing the - button on food items in the cart that you would like to"
            + "\nbe removed." + "\n"
            + "\nTo remove all items currently in the cart, you can press the button labeled \"Clear Cart.\""
            + "\n"
            + "\nTo calculate the nutritional information of all items in the cart, you can press the button labeled \"Calculate Nutrition Facts.\"");
    centerContent.getChildren().add(help);
    centerContent.getChildren().add(searchTitle);
    centerContent.getChildren().add(search);
    centerContent.getChildren().add(managingTitle);
    centerContent.getChildren().add(managingText);
    centerContent.setAlignment(Pos.TOP_CENTER);
    content.setCenter(centerContent);

    Button exitButton = new Button("Exit");
    exitButton.setOnAction((Event) -> {
      directionsStage.close();
    });
    content.setBottom(exitButton);

    content.setMargin(exitButton, new Insets(5, 5, 5, 5));
    content.setMargin(centerContent, new Insets(5, 5, 5, 5));
    content.setMargin(title, new Insets(5, 0, 0, 0));

    Scene directionsScene = new Scene(content);
    directionsStage.setScene(directionsScene);
    directionsStage.show();
  }

  /**
   * Creates the calculate nutrition facts button.
   * 
   * @param nutritionFacts A reference to the panel which shows the results of pressing the
   *                       calculate nutrition facts button.
   * @return Returns the calculate nutrition facts button.
   */
  private Button getCalculateButton(ListView<String> nutritionFacts) {
    Button calcNutrition = new Button("Calculate Nutrition Facts");
    calcNutrition.setOnAction((Event) -> {
      nutritionFacts
          .setItems(FXCollections.observableArrayList(backEnd.calculate(cart).split("\n")));
    }); // Uses the back-end to fill the nutritionFacts field with the total calculated nutrition
        // facts from the items in the cart.
    return calcNutrition;
  }

  /**
   * Creates a button which adds a search result to the cart.
   * 
   * @param food         Reference to the food object to be added to the cart.
   * @param cartContents Reference to the current displayed cart contents.
   * @return A reference to a button which adds a given search result to the cart.
   */
  private Button getAddButton(Food food, ListView<HBox> cartContents) {
    Button addToCart = new Button("+");
    addToCart.setOnAction((Event) -> {
      cart.add(food);
      HBox newFoodDisplay = new HBox();
      newFoodDisplay.setUserData(food);
      newFoodDisplay.setSpacing(5);
      Text nameOfFood = new Text(food.getName());
      Button removeFromCart = getRemoveButton(cartContents, newFoodDisplay);
      newFoodDisplay.getChildren().add(removeFromCart);
      newFoodDisplay.getChildren().add(nameOfFood);
      cartContents.getItems().add(newFoodDisplay);
    });
    return addToCart;
  }

  /**
   * Creates a button which removes a given food item from the cart.
   * 
   * @param cartContents Reference to the current displayed cart contents.
   * @param food         Reference to the HBox containing the food item to be removed.
   * @return A button which removes a given food item from the cart.
   */
  private Button getRemoveButton(ListView<HBox> cartContents, HBox food) {
    Button removeFromCart = new Button("-");
    removeFromCart.setOnAction((Event) -> {
      cart.remove((Food) food.getUserData());
      cartContents.getItems().remove(food);
    });
    return removeFromCart;
  }

}
