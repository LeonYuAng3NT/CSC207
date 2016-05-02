package traders;

public class BullTrader implements traders.Observer{
  private String name;

  
  public BullTrader(String name){
    this.name = name;
  }

  @Override
  public void update(int price) {
    // TODO Auto-generated method stub
    if(price > 50){
    System.out.println("This is" +this.name+ " and I want to buy risky stuff!");
    }
  }

}
