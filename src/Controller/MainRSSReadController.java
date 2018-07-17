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
import model.Feed;

public class MainRSSReadController {

	static URL url;
	
	static RSSRetrieveService rssService;
	
	static KeywordRetrieveService keywordService;
	
	static RSSToFileConvertService fileCreateService;
	
	static ValidatorService urlValidatorService;
	
	static FeedProcessorService feedProcessorService;
	
	Feed feed = null;
	
	static int fileCountIndex;
	/**
	 *  initializing of all the objects and static variables.
	 */
	public MainRSSReadController() {
		rssService = new RSSRetrieveServiceImpl();
		keywordService = new KeywordRetrieveServiceImpl();
		fileCreateService = new RSSToFileConvertServiceImpl();
		urlValidatorService = new ValidatorServiceImpl();
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
     
        SyndFeed inputFeed = urlValidatorService.linkValidator(url);
		
        List<SyndEntry> modifiedFeedEntry = new ArrayList<>();
        SyndFeed modifiedFeed = new SyndFeedImpl();
        
        urlValidatorService.validateInputFeed(inputFeed);
        feedProcessorService.processFeed(keywordToBeReplaced, inputFeed, modifiedFeedEntry);
        
        modifiedFeed.setEntries(modifiedFeedEntry);
        fileCreateService.CreateFile(modifiedFeed, fileCountIndex++);
        	
	}
	
	public static void main(String[] args) {
		
		MainRSSReadController rssReadController = new MainRSSReadController();
		String replaceKeyword = null;
		for(String link : rssService.getRSSLink()){
			try {
					url = new URL(link);		
					replaceKeyword = keywordService.getReplaceKeyword(link);
			} catch (MalformedURLException e) {
				System.out.println("Invalid RSS Feed. Please enter a valid URL. The application will now exit.");
				System.exit(0);
			}

			rssReadController.RSSMainApplication(url, replaceKeyword);

		}
    
	}


}
