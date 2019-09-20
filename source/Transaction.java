public class Transaction
{
   private String name;
   private double cost;

   public Transaction(String name, double cost) {
      this.name = name;
      this.cost = cost;
   }

   public double getCost() {
      return cost;
   }

   public void setCost(double cost) {
      this.cost = cost;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

}
