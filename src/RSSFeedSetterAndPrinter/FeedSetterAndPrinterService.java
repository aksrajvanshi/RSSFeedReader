package RSSFeedSetterAndPrinter;

import com.sun.syndication.feed.synd.SyndEntry;

public interface FeedSetterAndPrinterService {
	
	static final String STRING_EMPTY = "";

	public void printFeed();
	
	public void feedSetter(SyndEntry entry);
	
}
