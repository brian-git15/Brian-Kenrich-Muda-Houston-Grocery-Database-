public class dairy extends food {

  public dairy(String name, int quantity, double pricePerUnit) {
    
    super(name, quantity, pricePerUnit);
    
  }

  public String getFoodType() {
    return "dairy";
  }

  @Override
  public String toString() {
    return "Name: " + getName()  
      + "\nType: dairy"   
      + "\nPrice per unit: $" 
      + String.format("%.2f",getPricePerUnit());
    //price per unit is rounded to 2 decimal places
  }
}
