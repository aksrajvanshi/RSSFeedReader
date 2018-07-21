package RSSFeedProcessor;

import com.sun.syndication.feed.synd.SyndEntry;

public interface RSSFeedOutputService {

	public SyndEntry replaceKeyword(SyndEntry entry, String replaceString);
		
}
