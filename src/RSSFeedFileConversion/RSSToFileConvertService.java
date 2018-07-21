package RSSFeedFileConversion;

import com.sun.syndication.feed.synd.SyndFeed;

public interface RSSToFileConvertService {

	public void CreateRssTextFile(SyndFeed modifiedFeed, int fileCountIndex);

}
