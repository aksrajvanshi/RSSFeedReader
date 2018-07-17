package FeedProcessor;

import java.util.List;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;

import RSSFeedPrinter.RSSFeedOutputService;
import RSSFeedPrinter.RSSFeedOutputServiceImpl;
import model.Feed;
import model.FeedMessage;

public class FeedProcessorServiceImpl implements FeedProcessorService {

	private Feed feed;

	@Override
	public void processFeed(String keywordToBeReplaced, SyndFeed inputFeed, List<SyndEntry> modifiedFeedEntry) {
	
	RSSFeedOutputService outputService = new RSSFeedOutputServiceImpl();
		
	for (SyndEntry entry : (List<SyndEntry>) inputFeed.getEntries()) {
	        	
	        	FeedMessage feedMsg = new FeedMessage();
	        	entry = feedMsg.setFeed(entry);
	        	SyndEntry modifiedEntry = outputService.replaceKeyword(entry, keywordToBeReplaced);
	        	this.feed = new Feed(modifiedEntry);       	
	        	modifiedEntry = feedMsg.setFeed(modifiedEntry);
	        	feedMsg.printFeed();
	        	modifiedFeedEntry.add(modifiedEntry);	
	        	
	        }

	}

}
