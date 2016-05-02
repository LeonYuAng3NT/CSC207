package newTraders;

import java.util.Observable;
import java.util.Random;

public class Ouzo extends Observable{
  private int price;
  
  public Ouzo(){
    this.price = 0;
  } 
  public int getPrice() {
    // TODO Auto-generated method stub
    return price;
  }
  public void priceChange(){
    Random i = new Random();
    int result = i.nextInt(100);
    price = result;
    System.out.println("Ouzo price changed!");
    this.setChanged();
    this.notifyObservers(price);
    this.clearChanged();
  }
}
