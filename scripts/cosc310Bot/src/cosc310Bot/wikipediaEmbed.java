package cosc310Bot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.stream.Collectors;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class wikipediaEmbed {

	public static String wiki(String def) {
			String text ="";
			def = def.replace(" ", "_");
			try {
				
				String searchText = def + " wikipedia";
				Document google = Jsoup.connect("https://www.google.com/search?q=" + URLEncoder.encode(searchText, "UTF-8")).userAgent("Mozilla/5.0").get();
				
				//String wikipediaURL = google.getElementsByTag("cite").get(0).text();
				String URL = "https://en.wikipedia.org/wiki/"+def;
				String wikipediaApiJSON = "https://www.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro=&explaintext=&titles="+ URLEncoder.encode(URL.substring(URL.lastIndexOf("/") + 1, URL.length()), "UTF-8");
				
				HttpURLConnection httpconnection = (HttpURLConnection) new URL(wikipediaApiJSON).openConnection();
				httpconnection.addRequestProperty("User-Agent", "Mozilla/5.0");
				BufferedReader buff = new BufferedReader(new InputStreamReader(httpconnection.getInputStream()));
				
				String responses = buff.lines().collect(Collectors.joining());
				buff.close();
				
				String result = responses.split("extract\":\"")[1];
				
				text = result.length() > 400 ? result.substring(0, 400) : result;
				
			} catch (Exception e) {
				e.printStackTrace();
				 
			}
			return text+"...";
		}
		
	}
