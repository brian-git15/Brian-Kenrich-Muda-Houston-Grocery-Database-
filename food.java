public class food extends item {

  public double pricePerUnit;

  public food(String name, int quantity, double pricePerUnit) {
    //calling constructor of the item superclass
    super(name, quantity, pricePerUnit);
    this.pricePerUnit = pricePerUnit;
    
  }
  @Override
  public String getFoodType() {
    return "food";
  }
  public double getPricePerUnit() {
    return pricePerUnit;
  }

  public double calculateTotalPrice() {
    return pricePerUnit * quantity;
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
