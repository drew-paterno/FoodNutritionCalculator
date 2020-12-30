run: compile test runFrontEnd
	
compile:
	javac HashTableMap.java
	javac RedBlackTree.java
	javac Food.java
	javac Type.java
	javac FoodLoader.java
	javac FoodBackEnd.java

test:
	javac -cp .:junit5.jar TestLoadFood.java
	javac -cp .:junit5.jar FoodBackEndTest.java
	java -jar junit5.jar --class-path . --scan-classpath --details tree

runFrontEnd: FrontEnd.class
	java --module-path JavaFXForLinux/ --add-modules javafx.controls FrontEnd
	make clean

FrontEnd.class: FrontEnd.java
	javac --module-path JavaFXForLinux/ --add-modules javafx.controls FrontEnd.java
	
clean:
	$(RM) *.class


