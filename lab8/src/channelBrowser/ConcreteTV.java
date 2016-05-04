package channelBrowser;

import java.util.List;
import java.util.Random;
import java.util.TreeMap;

//Concrete Aggregate
public class ConcreteTV implements TV {
	private TreeMap<Float, String> channels;

	public ConcreteTV(List<String> c) {
	  channels = new TreeMap<Float, String>();
      Random r = new Random();
      for(String s: c){
        channels.put(r.nextFloat(), s);
      }
	}

	public ChannelIterator getIterator() {
		return new ConcreteChannelIterator(channels);
	}

  @Override
  public boolean hasNext() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public String next() {
    // TODO Auto-generated method stub
    return null;
  }

}