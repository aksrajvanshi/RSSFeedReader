package model;

import java.util.List;

import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEnclosure;
import com.sun.syndication.feed.synd.SyndEntry;

public class FeedMessage {

    private String title;
    private String description;
    private String link;
    private String guid;
    private String pubDate;
    private String url;
    private static final String STRING_EMPTY = "";


	public SyndEntry setFeed(SyndEntry entry){
		entry = validateEntry(entry);
    	List<SyndEnclosure> encls = entry.getEnclosures();
        this.title = entry.getTitle();
        this.link = entry.getLink();
        this.guid = entry.getUri();
        
        if(entry.getDescription() == null){
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
        
        return entry;

    }
	
	public void printFeed(){
		System.out.println(toString());
	}
	
    
    
    public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
    
    public String getUrl() {
		return url;
	}

    public void setUrl(String url) {
		this.url = url;
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
    
    @Override
    public String toString() {
        return " title=" + title + "\n, description=" + description
                + "\n, link=" + link  + "\n, guid=" + guid +"\n" + ", pubdate=" + pubDate +"\n" + ", Enclosure URL=" + url +"\n";
    }

}