public class fruitVeg extends food {

  public fruitVeg (String name , int quantity , double pricePerUnit) {
    super(name, quantity, pricePerUnit);
  }

  public String getFoodType() {
    return "fruitVeg";
  } 

  @Override
    public String toString() {
      return "Name: " + getName() 
        + "\nType: fruitVeg" 
        + "\nPrice per unit: $" 
        + String.format("%.2f",getPricePerUnit());
  }
}
