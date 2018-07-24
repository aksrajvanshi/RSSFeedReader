package FeedProcessor;

import java.net.URL;
import java.util.List;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;

public interface FeedProcessorService {
	
	public SyndFeed readFeed(URL url);
	
	public void processFeed(String keywordToBeReplaced,SyndFeed inputFeed,List<SyndEntry> modifiedFeedEntry);

}
