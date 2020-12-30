# Food Nutrition Calculator

Introduction
 - This program was developed in the Fall of 2020 in collaboration with other students in class at the University of Wisconsin-Madison.
 - The purpose of this program is to simulate a high level version of a modern calorie tracker, such as MyFitnessPal.
 - This project was developed almost entirely using Java based programming (JUnit testing and JavaFX for the front end) with the exception of some miscellaneous components (specifically, Make for construction and CSS for styling within JavaFX).
 
How it Works
 - The program uses a simple loader file to harvest data from CSV file (which can be found within this repo) containing the nutritional facts of over 14000 individual food items.
 - From the loader, food types are sorted using a Red-Black tree, and individual foods are organized within their respective type via a simple hashing algorithm.
 - The front-end interface was constructed using JavaFX. Within the interface, the user has options such as searching the catalog of food, adding items to their "cart" and calculating the average and total nutritional information of all the items in their "cart."

How to Use
 - The Food Nutrition Calculator is fully executable within Linux via Make. Simply type "make" into the command line (while being within the project directory) to run the program.
 - As long as you are operating within the project directory using a Linux machine, the front-end interface should launch shortly after the Java files are compiled, and JUnit    tests are run.
 - Please note that these instructions assume that the user already has Make installed.
 - To only run the front end (without the tests), the user can use the make target runFrontEnd (type "make runFrontEnd" in the command line).
 - Directions on how to use the interface are available upon launch.

 
