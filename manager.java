import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
//idea credits to houston (and the goat CookieSir) :)
//all programming credit goes to brian lolz
public class manager {
  //declare variables power, either on or off, and admin names, 4 members.
  public static String power;
  public static ArrayList<String> adminNames = new ArrayList<>();
  //to see if the code has worked in idle
  public static String UnlockPassword = "uwu"; //mudas idea
  
  public static void initialize() {
    try {
      //initialize all authorized names
      adminNames.add("Brian");
      adminNames.add("Muda");
      adminNames.add("Houston");
      adminNames.add("Kenrich");
      adminNames.add("Mathew");
      //create file names power containting one word - on or off to shut down system.
      File powerFile = new File("power.txt");
      if(powerFile.createNewFile()) {
        System.out.println("File sucsessfully created");
      }
      else{
        //start manager program
        System.out.println("System file exists, booting...");
      }
      //the first line of power.txt is either on or off, defines this variables.
      Scanner reader = new Scanner(powerFile);
      power = reader.nextLine();
    }
    catch(Exception e) {
      //catches initialization error 
      System.out.println("error in initialization occured");
    }
  }
  public static void access() {
    //access codes, usernames and passwords 
    Scanner input = new Scanner(System.in);
    System.out.print("Username: ");
    String username = input.nextLine();
    String password = ""; //initialize outside of loop
    for(int i = 0; i < adminNames.size(); i++){
      if (username.equalsIgnoreCase(adminNames.get(i))){
        //check for correct user/password
        System.out.print("Hello " + username + "\nPassword: ");
        password = input.nextLine();
        if(password.equals("uwu")){
          System.out.println("Access granted");
          //toggle power. 
          System.out.print("Welcome to the admin panel. Would you like to toggle power? (yes/no): ");
          String powerToggleOption = input.nextLine();
          if(powerToggleOption.equals("yes")){
            togglePower();
          } else {
            access();
          }
        } 
        else if (!password.equalsIgnoreCase("uwu")) {
          System.out.println("Access Denied.");
        }
      } 
    }
  }
  public static void togglePower(){
    try{
      Scanner input = new Scanner(System.in);
      Scanner reader = new Scanner("power.txt");
      if(power.equals("on")){
        System.out.println("would you like to turn off the power? (yes/no)");
        String powerOffOption = input.nextLine();
        if(powerOffOption.equals("yes")){
          FileWriter writer = new FileWriter("power.txt");
          writer.write("off");
          writer.close();
          power = reader.nextLine();
          Main.mainMenu();
        }
        else{
          togglePower();
        }
      } 
      else if(power.equals("off")){
        System.out.println("would you like to turn on the power? (yes/no)");
        String powerOffOption = input.nextLine();
        if(powerOffOption.equals("yes")){
          FileWriter writer = new FileWriter("power.txt");
          writer.write("on");
          writer.close();
          power = reader.nextLine();
          Main.mainMenu();
        }
        else{
          togglePower();
        }
      }
    }
    catch(Exception e){
      System.out.println("error occured");
    }
  }
  public static String getPower(){
    //reads power for the initial status. Nothing is managed here. 
    try{
      File powerFile = new File("power.txt");
      Scanner reader = new Scanner(powerFile);
      power = reader.nextLine();
    }
    catch(Exception e){
      
    }
    return power;
  }
}
