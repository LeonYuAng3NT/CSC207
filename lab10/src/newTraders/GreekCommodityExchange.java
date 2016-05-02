package newTraders;

public class GreekCommodityExchange {
  // this Exchange uses the Observer and Observable from JDK
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    Ouzo ouzo = new Ouzo();
    Tzatziki tzatziki = new Tzatziki();
    // These are real Greek names
    BullTrader makis = new BullTrader(" Makis");
    BearTrader mitsos = new BearTrader(" Mitsos");
    ouzo.addObserver(makis);
    ouzo.addObserver(mitsos);
    tzatziki.addObserver(makis);
    tzatziki.addObserver(mitsos);
    for(int i = 0; i<10; i++){
    ouzo.priceChange();
    tzatziki.priceChange();
    }
    }
}
