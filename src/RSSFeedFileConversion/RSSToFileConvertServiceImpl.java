package RSSFeedFileConversion;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.sun.syndication.feed.synd.SyndEnclosure;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;

public class RSSToFileConvertServiceImpl implements RSSToFileConvertService {


	/**
	 *  creates file from the modified Feed.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void CreateRssTextFile(SyndFeed modifiedFeed, int fileCountIndex) {
		// TODO Auto-generated method stub
		String filename = "RSSProgramFeedFile" + fileCountIndex + ".txt";
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			File file = new File(filename);


			if (!file.exists()) {

			}
			file.createNewFile();

			fw = new FileWriter(file.getAbsoluteFile(), false);
			bw = new BufferedWriter(fw);

			for (SyndEntry entry : (List<SyndEntry>) modifiedFeed.getEntries()) {

				bw.append("title: " + entry.getTitle());
				bw.append("link: " + entry.getLink()+ "\n");
				bw.append("guid: " + entry.getUri() + "\n");
				bw.append("description: " + entry.getDescription().getValue().toString() + "\n");
				if(entry.getPublishedDate()!=null){
					bw.append("pubDate: " + entry.getPublishedDate().toString() +  "\n");
				}

				List<SyndEnclosure> encls = entry.getEnclosures();
				if(encls.size()!=0){
					bw.append("Enclosure URL: " + encls.get(0).getUrl() + "\n" + "\n");
				}
				else{
					bw.append("Enclosure URL: null"  + "\n" + "\n");
				}

			}

		} catch (IOException e) {
			System.err.println("Unable to create file");
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();

			} catch (IOException ex) {
				System.err.println("Unable to properly close file");
			}
		}
	}

}
