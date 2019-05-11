package tn.biat.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("bot")
public class GoogleSearchController {
	
	
	
	
	@RequestMapping(value="/result",produces=MediaType.APPLICATION_JSON_VALUE)
	public String bot(@RequestParam String qry) throws IOException {
		
		String key="AIzaSyCrJojffHIgNjrtnpo-Db7J5BGL-91kFz4";
		URL url;
		 String googleApi="";
		 qry = qry.replace(" ", "+");
				url = new URL("https://www.googleapis.com/customsearch/v1?key="+key+ "&cx=013036536707430787589:_pqjad5hr1a&q="+ qry + "&alt=json");
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
		    
		    String output;
		    
		    while ((output = br.readLine()) != null) {
		    	googleApi+=output;
		   // System.out.println(output);
		    }
			//System.out.println("Google Api "+googleApi);
			 conn.disconnect(); 
			 return googleApi;
	}
}

