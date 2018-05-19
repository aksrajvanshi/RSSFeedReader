package ReplaceKeywordRetrieve;

import java.util.List;

import URLRetrieve.RSSRetrieveService;
import URLRetrieve.RSSRetrieveServiceImpl;

public class KeywordRetrieveServiceImpl extends RSSRetrieveServiceImpl implements KeywordRetrieveService{

    /**
     *  puts the URL links and Replace Keywords in a Map
     */
    public KeywordRetrieveServiceImpl() {
      RSSRetrieveService linkService = new RSSRetrieveServiceImpl();
      List<String> urlLinks = linkService.getRSSLink();
      for(int i=0; i < urlLinks.size(); i++){
        
        try{
          FeedKeywordMap.put(urlLinks.get(i), REPLACE_KEY.get(i));        
        }catch(IndexOutOfBoundsException e){
          System.out.println("Please input the replace keyword in Replace Key list for the link: " + urlLinks.get(i));
        }
        
      }
      
    }
  
	/**
	 * returns the keyword to be replaced from the map.
	 */
	@Override
	public String getReplaceKeyword(String urlLink) {
		// TODO Auto-generated method stub
	  return FeedKeywordMap.get(urlLink);
	}

}
