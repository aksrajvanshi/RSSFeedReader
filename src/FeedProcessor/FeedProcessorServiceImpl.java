package FeedProcessor;

import java.util.List;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;

import RSSFeedProcessor.RSSFeedOutputService;
import RSSFeedProcessor.RSSFeedOutputServiceImpl;
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
