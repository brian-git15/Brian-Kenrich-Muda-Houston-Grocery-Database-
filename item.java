public class item{
  public String name;
  public int quantity;
  public double pricePerUnit; 
  public int stockQuantity;

  //initializing with constructor
  public item(String name, int quantity, double pricePerUnit) {
    this.name = name;
    this.quantity = quantity;
    this.pricePerUnit = pricePerUnit;
    this.stockQuantity = 10;
  }
  public String getFoodType(){
    return "item";
  }
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
  //accessing methods from private fields
  public String getName() {
    return name;
  }

  public int getQuantity() {
    return quantity;
  }

  public double getPricePerUnit() {
    return pricePerUnit;
  }

  //calculating total price of items
  public double calculateTotalPrice() {
    return quantity * pricePerUnit;
  }

  @Override
  public String toString() {
    //price per unit is rounded to 2 decimal places
    return "Name: " + getName()  
      + "\nPrice per unit: " 
      + String.format("%.2f",getPricePerUnit());
  }

  
}
