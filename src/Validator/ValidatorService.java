package Validator;

import java.net.URL;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;

public interface ValidatorService {
	
	public static final String STRING_EMPTY = "";
	
	public SyndFeed linkValidator(URL URL);
	
	public void validateInputFeed(SyndFeed inputFeed);
	
	public SyndEntry validateEntry(SyndEntry entry);

}
