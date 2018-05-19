package ReplaceKeywordRetrieve;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public interface KeywordRetrieveService {

    /**
     *  put replace keyword in this list in the same order as the RSS Links
     */
	public static final List<String> REPLACE_KEY = Arrays.asList("NewsPicks"); 
	HashMap<String, String> FeedKeywordMap = new HashMap<>();
	
	public String getReplaceKeyword(String urlLink);
		
}
