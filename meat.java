public class meat extends food {
  public String name;
  public int quantity;
  public double pricePerKG;
  public meat(String name, int quantity, double pricePerKG) {
    //override
    super(name,quantity,pricePerKG);
    this.pricePerKG = pricePerKG;
  }

  public String getFoodType() {
    return "meat";
  }

  @Override
  public String toString() {
    //price per unit is rounded to 2 decimal places
    return "Name: " + getName() 
      + "\nType: meat" 
      + "\nPrice per unit: $" 
      + String.format("%.2f per kg",getPricePerUnit());
  }
}
