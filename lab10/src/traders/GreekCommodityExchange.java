package traders;

public class GreekCommodityExchange {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    Ouzo ouzo = new Ouzo();
    Tzatziki tzatziki = new Tzatziki();
    // These are real Greek names
    BullTrader makis = new BullTrader(" Makis");
    BearTrader mitsos = new BearTrader(" Mitsos");
    ouzo.registerObserver(makis);
    ouzo.registerObserver(mitsos);
    tzatziki.registerObserver(makis);
    tzatziki.registerObserver(mitsos);
    for(int i = 0; i<10; i++){
    ouzo.priceChange();
    tzatziki.priceChange();
    }
    }
}
