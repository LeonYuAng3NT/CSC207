package traders;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {
  private List<Observer> observerCollection;
  
  public Observable(){
    this.observerCollection = new ArrayList<Observer>();
  }
  abstract public int getPrice();
  
  public void registerObserver(Observer o){
    observerCollection.add(o);
  };
  
  public  void notifyObservers(){
    for (Observer x: observerCollection){
      int newPrice = this.getPrice();
      x.update(newPrice);
  }
  }
}
  
