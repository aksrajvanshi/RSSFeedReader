package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;

import RSSFeedPrinter.RSSFeedOutputService;
import RSSFeedPrinter.RSSFeedOutputServiceImpl;
import ReplaceKeywordRetrieve.KeywordRetrieveService;
import ReplaceKeywordRetrieve.KeywordRetrieveServiceImpl;
import model.FeedMessage;

public class testRSSFeedApplication {

	SyndEntry entry = new SyndEntryImpl();
	RSSFeedOutputService convertFeed = new RSSFeedOutputServiceImpl();
	KeywordRetrieveService keywordService = new KeywordRetrieveServiceImpl();
	private static final String STRING_EMPTY = "";
	
	@Test
	public void test() {
		entry.setTitle("newPIcks NEWSPICKS NEwSPiCks");
		entry.setAuthor("newPIcks NEWSPICKS NEwSPiCks");
		entry.setLink("newPIcks NEWSPICKS NEwSPiCks");
		entry.setUri("newPIcks NEWSPICKS NEwSPiCks");
		SyndContentImpl modifieddescription = new SyndContentImpl();
        modifieddescription.setType("text/html");
        modifieddescription.setValue("newPIcks NEWSPICKS NEwSPiCks");
        entry.setDescription(modifieddescription);
        FeedMessage feedMsg = new FeedMessage();
        entry = feedMsg.setFeed(entry);
		SyndEntry modifiedEntry = convertFeed.replaceKeyword(entry, "NewsPicks");
		assertTrue("true", modifiedEntry.getTitle().equals(STRING_EMPTY));
		assertTrue("true", modifiedEntry.getAuthor().equals(STRING_EMPTY));
		assertTrue("true", modifiedEntry.getLink().equals(STRING_EMPTY));
		assertTrue("true", modifiedEntry.getUri().equals(STRING_EMPTY));
		assertTrue("true", modifiedEntry.getDescription().getValue().equals(STRING_EMPTY));
		
	}

}
