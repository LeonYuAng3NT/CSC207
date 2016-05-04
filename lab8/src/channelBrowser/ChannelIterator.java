package channelBrowser;

import java.util.Iterator;

//Iterator interface 
public interface ChannelIterator extends Iterator<String>{

	public boolean hasNext();

	public String next();

}