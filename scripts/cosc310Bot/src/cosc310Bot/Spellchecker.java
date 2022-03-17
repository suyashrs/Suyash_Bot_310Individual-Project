package cosc310Bot;

//import packages from gson.jar
import java.io.*;
import java.net.*;
import java.util.ArrayList;

import com.google.gson.*;
import javax.net.ssl.HttpsURLConnection;



public class Spellchecker {
	static String host = "https://api.bing.microsoft.com/";
	static String path = "/v7.0/spellcheck";
	
	static String key = "81cbfb962852437180e90fb421863d36";
	static String mkt = "en-GB";
	static String mode = "proof";
	public static String text = "creamm";
	public static String result = "";
	
	
	public static void main(String []args) {
		try {
			check(text);
			System.out.print(result);
			
		}
		catch (Exception e) {
			System.out.println (e);
		}
	
	}
	
	public static void check(String text) throws Exception {
		//new addition
		String txt = text;
		
	    String params = "?mkt=" + mkt + "&mode=" + mode;
	   // add the rest of the code snippets here (except prettify() and main())...
	    
	    URL url = new URL(host + path + params);
	    HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
	    
	    connection.setRequestMethod("POST");
	    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	    connection.setRequestProperty("Ocp-Apim-Subscription-Key", key);
	    connection.setDoOutput(true);
	    
	    DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
	    //OG
	    // wr.writeBytes("text=" + text);
	    //new
	    wr.writeBytes("text=" + txt);
	    wr.flush();
	    wr.close();
	    
	    BufferedReader in = new BufferedReader(
	    		new InputStreamReader(connection.getInputStream()));
	    		String line;
	    		while ((line = in.readLine()) != null) {
	    			
	    			 result = parse(line);

	    		}
	    		in.close();
	}
	
	
	public static String parse(String json_text) {
		
		String rec = null;
		String [] arr = json_text.split("\"");
		
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals("suggestion")) {
		//System.out.println(arr[i+2]); 
			rec = arr[i+2];
			break;
			}	
		}
		
		return rec;
		
	}
}
