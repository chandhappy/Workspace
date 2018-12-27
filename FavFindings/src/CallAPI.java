import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;
 
public class CallAPI {
 
	private final String USER_AGENT = "Mozilla/5.0";
 
	public static void main(String[] args) throws Exception {
 
		CallAPI http = new CallAPI();
 
		System.out.println("GET Request Using HttpURLConnection");
		http.sendGet();
                System.out.println();
		System.out.println("POST Request Using HttpURLConnection");
		http.sendPost();
 
	}
 
	// HTTP GET request
	private void sendGet() throws Exception {
	    
	    String username="hitenpratap";
        StringBuilder stringBuilder = new StringBuilder("https://twitter.com/search");
        stringBuilder.append("?q=");
        stringBuilder.append(URLEncoder.encode(username, "UTF-8"));
        
        URL obj = new URL(stringBuilder.toString());
 
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Charset", "UTF-8");
 
		System.out.println("\nSending request to URL : " + obj);
		System.out.println("Response Code : " + con.getResponseCode());
		System.out.println("Response Message : " + con.getResponseMessage());
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String line;
		StringBuffer response = new StringBuffer();
 
		while ((line = in.readLine()) != null) {
			response.append(line);
		}
		in.close();
 
		System.out.println(response.toString());
 
	}
 
	private void sendPost() throws Exception {
	    
	/*    StringBuilder tokenUri=new StringBuilder("param1=");
        tokenUri.append(URLEncoder.encode("params1","UTF-8"));
        tokenUri.append("&param2=");
        tokenUri.append(URLEncoder.encode("param2","UTF-8"));
        tokenUri.append("&param3=");
        tokenUri.append(URLEncoder.encode("param3","UTF-8"));*/
 
		/*String url = "https://example.com";
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
 
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "UTF-8");
 
		con.setDoOutput(true);
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(con.getOutputStream());
        outputStreamWriter.write(params.toString());
        outputStreamWriter.flush();
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();*/
        
        
        URL url = new URL("https://ai.web.com/nov/");
    	Map<String,Object> params = new LinkedHashMap<>();
    /*	params.put("Name", Name[j]);
        params.put("CreatedBy",CreatedBy[i]);
        params.put("CreatedAt",CreatedAt);
        params.put("UpdatedAt",UpdatedAt);
        params.put("ClinicalFindings",ClinicalFindings[j])
        params.put("ObservationAndComments",ObservationAndComments[j]);
        params.put("Recommendation",Recommendation);
        params.put("RecommendationDetails",RecommendationDetails[j]);
        params.put("RegionsFindings",RegionsFindings[j]);
        params.put("FindingsDetails",FindingsDetails);
        params.put("ProductId",ProductId); */
    	
                StringBuilder postData = new StringBuilder();
    	for (Map.Entry<String,Object> param : params.entrySet()) {
    		if (postData.length() != 0) postData.append('&');
    		postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
    		postData.append('=');
    		postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
    	}
    	byte[] postDataBytes = postData.toString().getBytes("UTF-8");
    	HttpURLConnection conn = (HttpURLConnection)url.openConnection();
    	conn.setRequestMethod("POST");
    	conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    	conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
    	conn.setDoOutput(true);
    	conn.getOutputStream().write(postDataBytes);
    	Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
    	StringBuilder data = new StringBuilder();
    	for (int c; (c = in.read()) >= 0;) {
    		data.append((char)c);
    	}
    	String intentData = data.toString();
    	System.out.println(intentData);	
    	in.close();
	}
 
}

