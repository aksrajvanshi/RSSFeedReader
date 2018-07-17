package Validator;

import java.net.URL;

import com.sun.syndication.feed.synd.SyndFeed;

public interface ValidatorService {
	
	public SyndFeed linkValidator(URL URL);
	
	public void validateInputFeed(SyndFeed inputFeed);

}
