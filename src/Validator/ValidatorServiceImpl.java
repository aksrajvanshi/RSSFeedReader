package Validator;

import java.io.IOException;
import java.net.URL;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class ValidatorServiceImpl implements ValidatorService {

	@Override
	public void linkValidator(URL url) {
		
		SyndFeedInput input = new SyndFeedInput();
		
		try {
			 input.build(new XmlReader(url));
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid URL Entered. Please check the URL Entered.");
		} catch (FeedException e) {
			System.out.println("Unable to read Feed!");
		} catch (IOException e) {			
			System.out.println("IO Problem!");
		} 
		
		
	}
	
	public void validateInputFeed(SyndFeed inputFeed) {
		if(inputFeed == null){
        	System.out.println("The Feed contained no items! The application will now exit. Please try another Feed URL");
        	System.exit(0);
        }
	}
	
	
	public SyndEntry validateEntry(SyndEntry entry){
	    	
	    	if(entry.getTitle() == null){
	    		entry.setTitle(STRING_EMPTY);
	    	}
	    	if(entry.getLink() == null){
	    		entry.setLink(STRING_EMPTY);
	    	}
	    	if(entry.getUri() == null){
	    		entry.setUri(STRING_EMPTY);
	    	}
	    	
	    	return entry;
	    	
	    }

}
