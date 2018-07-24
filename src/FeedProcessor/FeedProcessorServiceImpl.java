package FeedProcessor;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import RSSFeedOutputter.RSSFeedOutputService;
import RSSFeedOutputter.RSSFeedOutputServiceImpl;
import RSSFeedSetterAndPrinter.FeedSetterAndPrinterService;
import RSSFeedSetterAndPrinter.FeedSetterAndPrinterServiceImpl;
import Validator.ValidatorService;
import Validator.ValidatorServiceImpl;

public class FeedProcessorServiceImpl implements FeedProcessorService {

	private RSSFeedOutputService outputService;
	
	private ValidatorService validatorService;
	
	private FeedSetterAndPrinterService feedSetterAndPrinterService;
	
	public FeedProcessorServiceImpl() {
		
		outputService = new RSSFeedOutputServiceImpl();
		validatorService = new ValidatorServiceImpl();
		feedSetterAndPrinterService = new FeedSetterAndPrinterServiceImpl();
			
	}
	
	@Override
	public SyndFeed readFeed(URL url) {
		// TODO Auto-generated method stub
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
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void processFeed(String keywordToBeReplaced, SyndFeed inputFeed, List<SyndEntry> modifiedFeedEntry) {
	
	for (SyndEntry entry : (List<SyndEntry>) inputFeed.getEntries()) {
	        	
				entry = validatorService.validateEntry(entry);
	        	SyndEntry modifiedEntry = outputService.replaceKeyword(entry, keywordToBeReplaced);   	
	        	feedSetterAndPrinterService.feedSetter(modifiedEntry);
	        	feedSetterAndPrinterService.printFeed();
	        	modifiedFeedEntry.add(modifiedEntry);		        	
	        }

	}

	

}
