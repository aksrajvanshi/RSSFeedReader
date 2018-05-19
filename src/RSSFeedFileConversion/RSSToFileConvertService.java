package RSSFeedFileConversion;

import com.sun.syndication.feed.synd.SyndFeed;

public interface RSSToFileConvertService {

	public void CreateFile(SyndFeed modifiedFeed, int fileCountIndex);
	
}
