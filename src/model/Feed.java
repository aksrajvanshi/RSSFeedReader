package model;

import java.util.ArrayList;
import java.util.List;

import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEnclosure;
import com.sun.syndication.feed.synd.SyndEntry;

public class Feed {

    private String title;
    private String link;
    private String description;
    private String guid;
	private String pubDate;
	private String url;
	private static final String STRING_EMPTY = "";

	List<FeedMessage> entries = null;

    public Feed(SyndEntry entry) {
    	entry = validateEntry(entry);
    	List<SyndEnclosure> encls = entry.getEnclosures();
        this.title = entry.getTitle();
        this.link = entry.getLink();
        this.guid = entry.getUri();
        if(entry.getDescription() ==null){
        	this.description = STRING_EMPTY;
        	SyndContentImpl modifieddescription = new SyndContentImpl();
        	modifieddescription.setType("text/html");
        	modifieddescription.setValue(STRING_EMPTY);
        	entry.setDescription(modifieddescription);
        }
        else{      	
        	this.description = entry.getDescription().getValue().toString();
        }
        
        
        if(entry.getPublishedDate()==null){
        	this.pubDate = STRING_EMPTY;
        }
        else{      	
        	this.pubDate = entry.getPublishedDate().toString();
        }
        
        if(encls.size()!=0){
        	this.url = encls.get(0).getUrl();
        }
        else{
        	this.url = STRING_EMPTY;
        	
        }
        entries = new ArrayList<FeedMessage>();
    }

    public List<FeedMessage> getMessages() {
        return entries;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public String getPubDate() {
        return pubDate;
    }
    
    public String getGuid() {
		return guid;
	}
    
    public String getUrl() {
		return url;
	}
    
    private SyndEntry validateEntry(SyndEntry entry){
    	
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
