package FeedProcessor;

import java.util.List;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;

public interface FeedProcessorService {
	
	public void processFeed(String keywordToBeReplaced,SyndFeed inputFeed,List<SyndEntry> modifiedFeedEntry);

}
