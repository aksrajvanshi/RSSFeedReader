package RSSFeedSetterAndPrinter;

import java.util.List;

import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEnclosure;
import com.sun.syndication.feed.synd.SyndEntry;

import model.FeedMessage;

public class FeedSetterAndPrinterServiceImpl implements FeedSetterAndPrinterService {

	private FeedMessage feedObject;
	
	public FeedSetterAndPrinterServiceImpl() {
		feedObject = new FeedMessage();
	}
	
	@Override
    public String toString() {
        return " title=" + feedObject.getTitle() + "\n, description=" + feedObject.getDescription()
                + "\n, link=" + feedObject.getLink()  + "\n, guid=" + feedObject.getGuid() +"\n" + ", pubdate=" + feedObject.getPubDate() +"\n" + ", Enclosure URL=" + feedObject.getUrl() +"\n";
    }


	@Override
	public void printFeed() {	
		System.out.println(this.toString());
	}


	@Override
	public void feedSetter(SyndEntry entry) {
		// TODO Auto-generated method stub
		List<SyndEnclosure> encls = entry.getEnclosures();
		this.feedObject.setTitle(entry.getTitle());
		this.feedObject.setLink(entry.getLink());
		this.feedObject.setGuid(entry.getUri());
        
        if(entry.getDescription() == null){
        	this.feedObject.setDescription(STRING_EMPTY);
        	SyndContentImpl modifieddescription = new SyndContentImpl();
        	modifieddescription.setType("text/html");
        	modifieddescription.setValue(STRING_EMPTY);
        	entry.setDescription(modifieddescription);
        }
        else{      	
        	this.feedObject.setDescription(entry.getDescription().getValue().toString());
        }
        
        
        if(entry.getPublishedDate()==null){
        	this.feedObject.setPubDate(STRING_EMPTY);
        }
        else{      	
        	this.feedObject.setPubDate(entry.getPublishedDate().toString());
        }
        
        if(encls.size()!=0){
        	this.feedObject.setUrl(encls.get(0).getUrl());
        }
        else{
        	this.feedObject.setUrl(STRING_EMPTY);
        }
        
	}

}
