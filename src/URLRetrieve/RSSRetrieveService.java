package URLRetrieve;
import java.util.Arrays;
import java.util.List;

public interface RSSRetrieveService {

	List<String> RSS_LINKS = Arrays.asList("http://tech.uzabase.com/rss");
	
	public List<String> getRSSLink();

}
