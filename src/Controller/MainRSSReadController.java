package Controller;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;

import FeedProcessor.FeedProcessorService;
import FeedProcessor.FeedProcessorServiceImpl;
import RSSFeedFileConversion.RSSToFileConvertService;
import RSSFeedFileConversion.RSSToFileConvertServiceImpl;
import ReplaceKeywordRetrieve.KeywordRetrieveService;
import ReplaceKeywordRetrieve.KeywordRetrieveServiceImpl;
import URLRetrieve.RSSRetrieveService;
import URLRetrieve.RSSRetrieveServiceImpl;
import Validator.ValidatorService;
import Validator.ValidatorServiceImpl;

public class MainRSSReadController {

	 private URL url;
	
	 private RSSRetrieveService rssService;
	
	 private KeywordRetrieveService keywordService;
	
	 private RSSToFileConvertService fileCreateService;
	
	 private ValidatorService validatorService;
	
	 private FeedProcessorService feedProcessorService;
	
	 private int fileCountIndex;
	/**
	 *  initializing of all the objects and static variables.
	 */
	public MainRSSReadController() {
		rssService = new RSSRetrieveServiceImpl();
		keywordService = new KeywordRetrieveServiceImpl();
		fileCreateService = new RSSToFileConvertServiceImpl();
		validatorService = new ValidatorServiceImpl();
		feedProcessorService = new FeedProcessorServiceImpl();
		fileCountIndex = 1;
	}
	
	/**
	 * 
	 * @param url the input RSS Feed URL
	 * 
	 * The master method which processes the RSS Feed, retrieves the keyword, replaces the keyword and creates a file for that RSS Feed.
	 */
	public void RSSMainApplication(URL url, String keywordToBeReplaced){
     
        SyndFeed inputFeed = validatorService.linkValidator(url);
		
        List<SyndEntry> modifiedFeedEntry = new ArrayList<>();
        SyndFeed modifiedFeed = new SyndFeedImpl();
        
        validatorService.validateInputFeed(inputFeed);
        feedProcessorService.processFeed(keywordToBeReplaced, inputFeed, modifiedFeedEntry);
        
        modifiedFeed.setEntries(modifiedFeedEntry);
        fileCreateService.CreateRssTextFile(modifiedFeed, fileCountIndex++);
        	
	}
	
	public static void main(String[] args) {
		MainRSSReadController rssReadController = new MainRSSReadController();
		rssReadController.rssReaderImpl();
    
	}

	private void rssReaderImpl() {
		
		String replaceKeyword = null;
		for(String link : rssService.getRSSLink()){
			try {
					url = new URL(link);		
					replaceKeyword = keywordService.getReplaceKeyword(link);
			} catch (MalformedURLException e) {
				System.out.println("Invalid RSS Feed. Please enter a valid URL. The application will now exit.");
				System.exit(0);
			}

			RSSMainApplication(url, replaceKeyword);

		}
	}


}
