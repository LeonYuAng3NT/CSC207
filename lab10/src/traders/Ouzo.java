package traders;

import java.util.Random;

public class Ouzo extends Observable{
  private int price;

  @Override
  public int getPrice() {
    // TODO Auto-generated method stub
   return price;
  }
  public void priceChange(){
    Random i = new Random();
    price = i.nextInt(100);
    System.out.println("Ouzo price changed!");
    this.notifyObservers();
  }
}
