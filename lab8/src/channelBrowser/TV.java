package channelBrowser;

import java.util.Iterator;

//Aggregate interface
public interface TV extends Iterator<String>{
	
	public ChannelIterator getIterator();

	// other TV methods
	
}
