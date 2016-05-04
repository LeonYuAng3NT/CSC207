package channelBrowser;
import java.util.Iterator;

import java.util.TreeMap;

//Concrete Iterator 
public class ConcreteChannelIterator implements ChannelIterator {

	private TreeMap<Float,String> channels;
    private Iterator<Float> keys;
	private int currentPos = 0;

	public ConcreteChannelIterator(TreeMap<Float, String> channels) {
	  this.channels = channels;
	  keys = channels.navigableKeySet().iterator();
	}

	public boolean hasNext() {
	 return keys.hasNext();
	}

	public String next() {
           Float k = keys.next();
           return k + channels.get(k);
	}

}
