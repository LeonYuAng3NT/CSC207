package traders;

import java.util.Random;

public class Tzatziki extends traders.Observable {

  private int price;

  @Override
  public int getPrice() {
    // TODO Auto-generated method stub
    return price;
  }

  public void priceChange() {
    Random i = new Random();
    price = i.nextInt(60);
    System.out.println("Tzatziki price changed!");
    this.notifyObservers();
  }
}

