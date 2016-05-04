package channelBrowser;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class TVBrowser {

  public static void main(String[] args) {
    List<String> channels =
        Arrays.asList(new String[] {"BBC", "CBC", "CNN", "ERT"});
    TV aNewTV = new ConcreteTV(channels);
    ChannelIterator i = aNewTV.getIterator();
    String someChannel = i.toString();
    while (i.hasNext()) {
      someChannel = i.next();
      System.out.println(someChannel);
    }
    System.out.println("Let's print them all once more!");

    while (i.hasNext()) {
      someChannel = i.next();
      System.out.println(someChannel);
    }
    System.out.println("Oups.");
    for (Iterator<String> itr = channels.iterator(); itr.hasNext();) {
      String item = itr.next();
      // The rest is the same as the body of the foreach-loop:
      System.out.println(item);
    }
  }
}
