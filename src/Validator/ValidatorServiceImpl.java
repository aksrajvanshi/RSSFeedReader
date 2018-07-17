package Validator;

import java.io.IOException;
import java.net.URL;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class ValidatorServiceImpl implements ValidatorService {

	@Override
	public SyndFeed linkValidator(URL url) {
		
		SyndFeed inputFeed;
		SyndFeedInput input = new SyndFeedInput();
		
		try {
			 inputFeed = input.build(new XmlReader(url));
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid URL Entered. Please check the URL Entered.");
			return null;
		} catch (FeedException e) {
			System.out.println("Unable to read Feed!");
			return null;
		} catch (IOException e) {			
			System.out.println("IO Problem!");
			return null;
		} 
		
		return inputFeed;
		
	}
	
	public void validateInputFeed(SyndFeed inputFeed) {
		if(inputFeed == null){
        	System.out.println("The Feed contained no items! The application will now exit. Please try another Feed URL");
        	System.exit(0);
        }
	}

}
