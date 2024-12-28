/*
File name: GroceryDatabase.java
Creators: Muda, Kenrich, Brian, Houston
Description: a grocery database that allows the 
user to choose, purchase, modify and store grocery items
Date created: Dec 19, 2023
Date last modified: Jan 09, 2024

Note to testers: Please Ignore Manager.java, the unlock() method,
and the idle() method. please jump straight into initializeMenu()
and mainMenu(). 
*/


import java.util.ArrayList;
import java.util.Scanner;

class Main {
  // cart is a dynamic array storing an unknown number of values, therefor it must
  // be an arraylist
  static ArrayList<item> cart = new ArrayList<item>();
  static item[] itemCatalogue = new item[1];
  static fruitVeg[] fruitVegCatalogue = new fruitVeg[3];
  static meat[] meatCatalogue = new meat[3];
  static dairy[] dairyCatalogue = new dairy[3];
  static food[] foodCatalogue = new food[2];
  static Scanner input = new Scanner(System.in);
  /*
   * initialize all the cart and catalogue arrays. The arrays
   * cannot be 2-d because each different item has to be it's own cartagory
   * and there is no use downcasting and upcasting every time one needs to be
   * called.
   */
  // main method
  public static void main(String[] args) {
    //if power is on, store runs.
    unlock();
    input.close();
  }
  
  public static void unlock(){
    String power = manager.getPower();
    if(power.equalsIgnoreCase("on")){
      initializeMenu();
      mainMenu();
    }
    else{
      //idle, store is off, only can be unlocked when the admin comes
      idle();
    }
  }
  
  public static void idle(){
    //constantly accepts input to see if admin arrives
    System.out.println("Admin arrives, enter name to access. Enter code: ");
    //admin must enter code
    String adminCode = input.nextLine();
    if (adminCode.equals(manager.UnlockPassword)){
      //unlock password is changable in the manager.java file.
      manager.initialize();
      manager.access();
    }
    else{
      idle();
    }
  }

  // initializes specific database, each database has 3 items
  public static void initializeMenu()
  // requires every database to be passed through
  {
    // initialize the items in each catalogue
    initializeItem(itemCatalogue);
    initializeFoodItems(foodCatalogue);
    initializeDairy(dairyCatalogue);
    initializeFruitVeg(fruitVegCatalogue);
    initializeMeat(meatCatalogue);
  }
  // initializing all of the inidividual items in the code
  public static void initializeItem(item[] itemCatalogue) {
    itemCatalogue[0] = new item("Toilet Paper", 10, 2.00);
  }
  
  public static void initializeFruitVeg(fruitVeg[] fruitVegCatalogue) {
    fruitVegCatalogue[0] = new fruitVeg("Apple", 10, 0.35);
    fruitVegCatalogue[1] = new fruitVeg("Banana", 10, 2.50);
    fruitVegCatalogue[2] = new fruitVeg("Broccoli", 10, 5.00);
  }

  public static void initializeMeat(meat[] meatCatalogue) {
    meatCatalogue[0] = new meat("Fish", 10, 9.00);
    meatCatalogue[1] = new meat("Chicken", 10, 5.00);
    meatCatalogue[2] = new meat("Beef", 10, 22.00);
  }

  public static void initializeDairy(dairy[] dairyCatalogue) {
    dairyCatalogue[0] = new dairy("Milk", 10, 10.00);
    dairyCatalogue[1] = new dairy("Eggs", 10, 6.00);
    dairyCatalogue[2] = new dairy("Yogurt", 10, 4.50);
  }

  public static void initializeFoodItems(food[] foodCatalogue) {
    foodCatalogue[0] = new food("Soup", 10, 0.99);
    foodCatalogue[1] = new food("Cereal", 10, 4.50);
  }

  // printing out the menu display
  public static void mainMenu(){
    System.out.println(
      """
      \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n
      Welcome to the ZaZa Zanon Zhop! Which category will you be shopping at today?
      ─────────────────────
      1) Non-Food Items
      2) Other Food Items
      3) Dairy
      4) Meats
      5) Fruit and Vegetables
      6) View Cart
      7) Checkout
      8) Exit
      ─────────────────────\nEnter Option: 
      """
    );
    //to see if input is an integer
    int menuOption = inputErrorInt();
    input.nextLine();
    if(menuOption == 5889){
      //secret manager easter egg
      manager.initialize();
      manager.access();
    }
    //switch between menu options
    switch (menuOption){
      case 1:
        groceryMenu(itemCatalogue);
        break;
      case 2:
        groceryMenu(foodCatalogue);
        break;
      case 3:
        groceryMenu(dairyCatalogue);
        break;
      case 4:
        groceryMenu(meatCatalogue);
        break;
      case 5:
        groceryMenu(fruitVegCatalogue);
        break;
      case 6:
        cartMenu();
        break;
      case 7:
        checkout();
        break;
      case 8:
        //if the user wishes to exit, if not, recurse to mainMenu
        if(exitClause() == false){
          mainMenu();
        }
        break;
      case 404:
        System.out.println("error. Enter in an INTEGER. please try again");
        mainMenu();
        break;
      default:
        System.out.println("Invalid input, please try again.");
        mainMenu();
        break;
    }
  }
  
  public static void groceryMenu(item[] database) {
    printCatalogue(database);
    System.out.println(
            "Zanon ZaZa Grocery Database! \nMenu Options:" +
            "\n─────────────────────" +
            "\n1) Choose Grocery" +
            "\n2) View cart" +
            "\n3) Checkout" +
            "\n4) Exit" +
            "\n5) Back to Main Menu" +
            "\n─────────────────────\nEnter Option: ");
    int menuOption = inputErrorInt();
    input.nextLine(); //input from menuOption was an integer, so we need to use input.nextLine() to accept future Strings
    switch (menuOption) {
      case 1:
        System.out.println("Which item index number would you want to add to your cart?");
        System.out.print("Index Number: ");
        int indexNum = input.nextInt();
        selectItem(indexNum,database);
        break;
      case 2:
        cartMenu();
        break;
      case 3:
        checkout();
        break;
      case 4:
        //use exit caluse method to see if user wishes to exit, if no, recurse. If no, ends in the exit clause method
        if(exitClause() == false){
          groceryMenu(database);
        }
        break;
      case 5:
        mainMenu();
        break;
        //case 404 means invalid input, from inputErrorInt()
      case 404:
        System.out.println("Not a valid INTEGER input!!!!");
        groceryMenu(database);
      default:
        System.out.println("Not a valid input from 1-4!!!!");
        groceryMenu(database);
    }
  }
  //if the user wishes to exit the program

  public static void cartMenu() {
    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    System.out.printf("%n%-15s%-15s%-25s%-15s%n","item:","quantity:","price per unit:","Total price:");
    for(int i = 0;i < cart.size(); i++) {
      System.out.printf("%n%-15s%-15s$%-25.2f$%-25.2f%n",cart.get(i).name,cart.get(i).quantity,cart.get(i).pricePerUnit,cart.get(i).calculateTotalPrice());
    } 
    
    System.out.println(
            "\nZanon ZaZa Cart! \nMenu options:" +
            "\n──────────────────────────────" +
            "\n 1) View Grocery Database" +
            "\n 2) Checkout" +
            "\n 3) Edit Cart" +
            "\n 4) Exit" +
            "\n──────────────────────────────\nEnter Option: ");
    int menuOption = input.nextInt();
    input.nextLine();
    switch (menuOption) {
      case 1:
        mainMenu();
        break;
      case 2:
        checkout();
        break;
      case 3:
        modifyCart();
        break;
      case 4:
        //use exit caluse method to see if user wishes to exit, if no, recurse. If no, ends in the exit clause method
        if(exitClause() == false){
          cartMenu();
        }
        break;
      case 404:
        System.out.println("error. Enter in an INTEGER. please try again");
        mainMenu();
        break;
      default:
        cartMenu();
    }
  }

  public static void modifyCart() {  //modify cart
    System.out.print("Enter the index of the item you want to modify the quantity of: ");
    int indexToModify = input.nextInt(); 

    if (indexToModify >= 1 && indexToModify <= cart.size()) {
      item modifiedItem = cart.get(indexToModify - 1);

      System.out.println("Selected Item: " + modifiedItem.getName() + "\nCurrent Quantity: " + modifiedItem.getQuantity());
      //modifying logic to more user friendly
      System.out.print("Enter modified quantity: ");
      int newQuantity = input.nextInt();
      //to check if the quantity is valid
      while(newQuantity>10 || newQuantity<0){
        System.out.print("invalid input. modified quantity: ");
        newQuantity = input.nextInt();
      }
      //remove item if 0
      if(newQuantity == 0){
        cart.remove(indexToModify - 1);
        System.out.println("Item removed from cart.");
        cartMenu();
      }
      int difference = newQuantity - modifiedItem.getQuantity();

      if (difference > 0) {
        modifiedItem.quantity += difference; //if item is modified to be lowered, add back to stock
            
      } else if (difference < 0) {
          //decrease stock if item is modified to be higher
        if (modifiedItem.quantity >= -difference) {
          modifiedItem.quantity += difference;
        } else {
          System.out.println("Invalid modification. Returning to the menu.");
        }
      }
      //set the new quantity of the mofied item 
      modifiedItem.setQuantity(newQuantity);
      System.out.println("Adjusted quantity of " + modifiedItem.getName() + " to " + newQuantity + ". \n");
      //to find the database item corsponding to the cart item
      //down case can be used in this situation, but it is not needed
      String type = modifiedItem.getFoodType();
      if(type.equals("item")){
        modifyStock(itemCatalogue,newQuantity,indexToModify);
      }
      else if(type.equals("food")){
        modifyStock(foodCatalogue,newQuantity,indexToModify);
      }
      else if(type.equals("dairy")){
        modifyStock(dairyCatalogue,newQuantity,indexToModify);
      }
      else if(type.equals("meat")){
        modifyStock(meatCatalogue,newQuantity,indexToModify);
      }
      else if(type.equals("fruitVeg")){
        modifyStock(fruitVegCatalogue,newQuantity,indexToModify);
      }
    } else {
      System.out.println("Invalid input. Please enter a valid input. \n");
    }
    //Recurse
    cartMenu();
  }
  //the modified stock method takes in the database the item is in, the new quantity of item, and the array number of the item
  public static void modifyStock(item[] database, int newQuantity, int arrayNum){
    //to find the correct item in the database for modified item, cycle through the database
    for(item modifiedItem : database){
      //item is found by matching names
      if(modifiedItem.getName().equals(database[arrayNum-1].getName())){
        //the stock is always ten, so the stock is 10-the quantity of item bought.
        modifiedItem.setQuantity(10-newQuantity);
      }
    }
  }
  public static void checkout() {
    System.out.println(
            "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nZanon ZaZa Shop! \nMenu options:" +
            "\n──────────────────────────────" +
            "\n 1) Pay" +
            "\n 2) View Grocery Database" +
            "\n 3) View Cart Menu" +
            "\n 4) Exit" +
            "\n──────────────────────────────\nEnter Option: ");
    int menuOption = input.nextInt();
    input.nextLine();
    switch (menuOption) {
      case 1:
        paymentScreen();
        break;
      case 2:
        mainMenu();
        break;
      case 3:
        cartMenu();
        break;
      case 4:
        //use exit caluse method to see if user wishes to exit, if no, recurse. If no, ends in the exit clause method
        //if the value is true, the program will send a false value to the main method, ending the program there. else; the program will recurse accordingly 
        if(exitClause() == false){
          checkout();
        }
        break;
      default:
        checkout();
    }
  }

  public static void printCatalogue(item[] database) {
    //converges array index number to new index number displayed.
    // printing out all the grocery items with index numbers
    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    for (int i = 0; i < database.length; i++) {
      // first value of i is 0, so we add 1 to the index value
      System.out.println("Index Number: " + (i + 1));
      System.out.println(database[i].toString() + "\n");
      System.out.println("Stock amount: " + database[i].getQuantity() + "\n");
    }
  }

  public static void selectItem(int indexNum, item[] database) {
    //array num is different from indexNum
    int arrayNum = indexNum-1;
    while (arrayNum < 0 || arrayNum >= database.length) {
      System.out.println("Invalid input. Please enter a valid input. \n");
      arrayNum = input.nextInt();
      //arrayNum needs to start at 0
      arrayNum--;
    }
    addItem(arrayNum, database);
    //Check and handle stock quantity
    if (arrayNum >= 0 && arrayNum < database.length) {
      if (database[arrayNum].quantity > 0) {
        database[arrayNum].quantity--; //decrement stock
      } else {
        System.out.println("Sorry, " + database[arrayNum].name + " is out of stock.");
        cart.remove(cart.size() - 1); //remove item from cart
      } 
    }

    printCatalogue(database);

    //after adding to cart, go back to menu.
    mainMenu();
  }

  public static void addItem(int arrayNum,item[] database) {
    //take in a item[] or item subclass array to add towards. 
    //method referenced in the selectItem method

    boolean existingItem = false;
    int addQuantity = 0;

    //Check if item is arleady in cart
    for (item cartItem : cart) {
      if (cartItem.getName().equals(database[arrayNum].getName()) && database[arrayNum].getQuantity() > 0) {
        // Prompt user for how many they would like
        System.out.println("How many more would you like??");
        addQuantity = input.nextInt();
        System.out.println(database[arrayNum].getQuantity());
        // Check if the quantity is valid, within range of stock, and is greater than 0
        while (addQuantity <= 0 || database[arrayNum].getQuantity() < addQuantity) {
          System.out.println("Invalid input or out of stock. Please enter again:\n");
          addQuantity = input.nextInt();
        }
        //set the database's quantity (stock quantity), to the beginning stock - added amount
        database[arrayNum].setQuantity(database[arrayNum].getQuantity() - addQuantity +1);
        cartItem.setQuantity(cartItem.getQuantity() + addQuantity);
        //do not make a new item into cart, there is already one in the cart
        existingItem = true;
        //break from the loop of (for item cartItem : cart), to make 100% sure that the other method does not run
        break;
      }
      else if(database[arrayNum].getQuantity() <= 0){
        System.out.println("Sorry! no more stock!");
        groceryMenu(database);
      }
    }
    //If item is not in the cart, add it to the cart 
    if (!existingItem && database[arrayNum].getQuantity() > 0) {
      System.out.println("How many would you like?");
      addQuantity = input.nextInt();

      while (addQuantity <= 0 || database[arrayNum].getQuantity() < addQuantity) {
        System.out.println("Invalid input or out of stock. Please enter again:\n");
        addQuantity = input.nextInt();
      }
      item newItem = new item(database[arrayNum].getName(), addQuantity, database[arrayNum].getPricePerUnit());
      cart.add(newItem); // Add item to cart
      database[arrayNum].setQuantity(database[arrayNum].getQuantity() - addQuantity+1);
      existingItem = true;
      System.out.println("Added " + database[arrayNum].getName() + " to the cart!\n");
    } else if (!existingItem) {
      System.out.println("Sorry, " + database[arrayNum].getName() + " is out of stock.\n");
    }
  }

  public static void paymentScreen() {

    //display grand total
    double subtotal = calculateSubTotal();
    double tax = subtotal * 0.13; //13% hst
    double grandTotal = subtotal + tax; 

    System.out.printf("Subtotal: $%.2f%n", subtotal);
    System.out.printf("Tax: $%.2f%n", tax);
    System.out.printf("Grand Total: $%.2f%n" , grandTotal); 

    System.out.print("Are you sure you want to pay? This action is final. (yes/no): ");
    String menuOption = input.nextLine();
    
    if (menuOption.equalsIgnoreCase("yes")) {
      double paymentAmount = getPaymentAmount();
      if (paymentAmount < grandTotal) {
        System.out.println("Payment amount insufficient. Please enter a sufficient amount.");
        paymentScreen(); //reprompt for payment
      } else {
        double change = paymentAmount - grandTotal;
        System.out.printf("Thank you for shopping at Zanon ZaZa Shop!! Your change: $%.2f%n", change);
      }
    } else {
      System.out.println("Returning to checkout...");
      checkout(); //sends user back to checkout menu
    }
  }

  public static double getPaymentAmount() {
    System.out.print("Enter payment amount: $");
    return input.nextDouble();
  }

  public static void payment() {

    double grandTotal = calculateGrandTotal();
    System.out.printf("Grand Total: $%.2f%n", grandTotal);

    System.out.print("Please enter the amount you are paying: $");
    double paymentAmount = input.nextDouble();

    if (paymentAmount < grandTotal) {
      System.out.println("Insufficient payment. Please pay the full amount. ");
      
    } else {
      double change = paymentAmount - grandTotal;
      System.out.printf("Payment successful! Your change is: $%.2f%n" , change);
      cart.clear(); //clears cart after sucessful payment
    }
  }

  public static double calculateSubTotal() {
    double subtotal = 0;
    for (item cartItem : cart) {
      subtotal += cartItem.calculateTotalPrice();
    }
    return subtotal;
  }
  public static double calculateGrandTotal() {
    double grandTotal = 0;
    for (item cartItem : cart) {
      grandTotal += cartItem.calculateTotalPrice();
    }
    return grandTotal;
  }
  
  //whenever the user wishes to exit, exit clause will pop up asking if they wish to do so
  public static Boolean exitClause(){
    //Since we cannot include methods inside the variable, we have to use a boolean to return a true/falce value
    Boolean exit = false;
    System.out.println("Are you sure you want to exit? you will lose all your cart items.(yes/no)");
    String exitOption = input.nextLine();
    if (exitOption.equalsIgnoreCase("yes")) {
      System.out.println("thank you for shopping and ZAZAZANNON!");
      exit = true;
    } else {
      System.out.println("Oh no... Wrong input.");
      exit = false;
    }
    return exit;
  }
  
  public static int inputErrorInt() {
    //for menus involving integers, if the input is not an integer, return a message
    int menuOption;
    try{
      menuOption = input.nextInt();
    }
    catch(Exception e){
      //menu option of 5 means the input is not an integer
      menuOption = 404;
    }
    return menuOption;
  }
}
