package Controller;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import RSSFeedFileConversion.RSSToFileConvertService;
import RSSFeedFileConversion.RSSToFileConvertServiceImpl;
import RSSFeedPrinter.RSSFeedOutputService;
import RSSFeedPrinter.RSSFeedOutputServiceImpl;
import ReplaceKeywordRetrieve.KeywordRetrieveService;
import ReplaceKeywordRetrieve.KeywordRetrieveServiceImpl;
import URLRetrieve.RSSRetrieveService;
import URLRetrieve.RSSRetrieveServiceImpl;
import model.Feed;
import model.FeedMessage;

public class MainRSSReadController {

	static URL url;
	
	static RSSRetrieveService rssService;
	
	static KeywordRetrieveService keywordService;
	
	static RSSFeedOutputService outputService;
	
	static RSSToFileConvertService fileCreateService;
	
	Feed feed = null;
	
	static int fileCountIndex;
	/**
	 *  initializing of all the objects and static variables.
	 */
	public MainRSSReadController() {
		rssService = new RSSRetrieveServiceImpl();
		keywordService = new KeywordRetrieveServiceImpl();
		outputService = new RSSFeedOutputServiceImpl();
		fileCreateService = new RSSToFileConvertServiceImpl();
		fileCountIndex = 1;
	}
	
	/**
	 * 
	 * @param url the input RSS Feed URL
	 * 
	 * The master method which processes the RSS Feed, retrieves the keyword, replaces the keyword and creates a file for that RSS Feed.
	 */
	@SuppressWarnings("unchecked")
	public void RSSMainApplication(URL url, String keywordToBeReplaced){
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed inputFeed = null;
		try {
			 inputFeed = input.build(new XmlReader(url));
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid URL Entered. Please check the URL Entered.");
			return;
		} catch (FeedException e) {
			System.out.println("Unable to read Feed!");
			return;
		} catch (IOException e) {			
			System.out.println("IO Problem!");
			return;
		} 
        List<SyndEntry> modifiedFeedEntry = new ArrayList<>();
        SyndFeed modifiedFeed = new SyndFeedImpl();
        
        if(inputFeed == null){
        	System.out.println("The Feed contained no items! The application will now exit. Please try another Feed URL");
        	System.exit(0);
        }

        for (SyndEntry entry : (List<SyndEntry>) inputFeed.getEntries()) {
        	
        	FeedMessage feedMsg = new FeedMessage();
        	entry = feedMsg.setFeed(entry);
        	SyndEntry modifiedEntry = outputService.replaceKeyword(entry, keywordToBeReplaced);
        	this.feed = new Feed(modifiedEntry);       	
        	modifiedEntry = feedMsg.setFeed(modifiedEntry);
        	feedMsg.printFeed();
        	modifiedFeedEntry.add(modifiedEntry);	
        	
        }
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
