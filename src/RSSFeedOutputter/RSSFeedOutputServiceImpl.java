package RSSFeedOutputter;

import java.util.List;

import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEnclosure;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;

public class RSSFeedOutputServiceImpl implements RSSFeedOutputService{

	/**
	 *  replaces the keyword with an empty string.
	 */
	@Override
	public SyndEntry replaceKeyword(SyndEntry entry, String keywordToBeReplaced) {
		// TODO Auto-generated method stub
	    if(keywordToBeReplaced.equals("")){
	      return entry;
	    }
		List<SyndEnclosure> encls = entry.getEnclosures();
        SyndEntry modifiedEntry = new SyndEntryImpl();
        modifiedEntry.setTitle(entry.getTitle().replaceAll("(?i).*"+keywordToBeReplaced+"*", ""));
        modifiedEntry.setLink(entry.getLink().replaceAll("(?i).*"+keywordToBeReplaced+"*", ""));
        modifiedEntry.setUri(entry.getUri().replaceAll("(?i).*"+keywordToBeReplaced+"*", ""));
        SyndContentImpl modifieddescription = new SyndContentImpl();
        modifieddescription.setType("text/html");
        modifieddescription.setValue(entry.getDescription().getValue().replaceAll("(?i).*"+keywordToBeReplaced+"*", ""));
        modifiedEntry.setDescription(modifieddescription);  
        if(entry.getPublishedDate() !=null){
        	modifiedEntry.setPublishedDate(entry.getPublishedDate());
        }
        if(encls.size()!=0){
        	encls.get(0).setUrl(encls.get(0).getUrl().replaceAll("(?i).*"+keywordToBeReplaced+"*", ""));
        	modifiedEntry.setEnclosures(encls);	
        }
        
        return modifiedEntry;
		
	}





}
