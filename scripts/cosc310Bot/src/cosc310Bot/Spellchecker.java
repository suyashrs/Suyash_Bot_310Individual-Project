package cosc310Bot;

//import packages from gson.jar
import java.io.*;
import java.net.*;
import java.util.ArrayList;

import com.google.gson.*;
import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Spellchecker {
	static String host = "https://api.bing.microsoft.com/";
	static String path = "/v7.0/spellcheck";
	
	static String key = "81cbfb962852437180e90fb421863d36";
	static String mkt = "en-GB";
	static String mode = "proof";
	static String text = "...";
	
	
	public static void main(String []args) {
		try {
			check();
		}
		catch (Exception e) {
			System.out.println (e);
		}
	
	}
	
	public static void check() throws Exception {
	    String params = "?mkt=" + mkt + "&mode=" + mode;
	   // add the rest of the code snippets here (except prettify() and main())...
	    
	    URL url = new URL(host + path + params);
	    HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
	    
	    connection.setRequestMethod("POST");
	    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	    connection.setRequestProperty("Ocp-Apim-Subscription-Key", key);
	    connection.setDoOutput(true);
	    
	    DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
	    wr.writeBytes("text=" + text);
	    wr.flush();
	    wr.close();
	    
	    BufferedReader in = new BufferedReader(
	    		new InputStreamReader(connection.getInputStream()));
	    		String line;
	    		while ((line = in.readLine()) != null) {
	    			//System.out.println(prettify(line));
	    			parse(line);

	    		}
	    		in.close();
	}
	
	
	/*public static String prettify(String json_text) {
	    JsonParser parser = new JsonParser();
	    JsonElement json = parser.parse(json_text);
	    Gson gson = new GsonBuilder().setPrettyPrinting().create();
	    return gson.toJson(json);
	    }*/
	
	public static String parse(String json_text) {
		
		String rec = null;
		String [] arr = json_text.split("\"");
		
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals("suggestion")) {
		System.out.println(arr[i+2]); 
			rec = arr[i+2];
			break;
			}
			
			
				//System.out.println(arr[i]); 
					
			
			
			}
		
		
		
		return rec;
		
	}
}
