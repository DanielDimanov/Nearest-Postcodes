import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequestHandle {

	private final String USER_AGENT;
	public HttpRequestHandle(String agent) {
		this.USER_AGENT = agent;
	}

	// HTTP GET request
	protected Response sendGet(String url) throws Exception {

		URL obj = new URL(url);
		HttpURLConnection connect = (HttpURLConnection) obj.openConnection();
		connect.setRequestMethod("GET");
		connect.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = connect.getResponseCode();
		System.out.println("\nSending 'GET' request to " + url);
		Response r=new Response(responseCode);
		BufferedReader in = new BufferedReader(
				new InputStreamReader(connect.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine+"\n");
			if(inputLine.contains("country")){
				int start=inputLine.indexOf("country");
				int end= inputLine.indexOf("nhs_ha");
				r.setCountry(inputLine.substring(start+10,end-3));
			}
			if(inputLine.contains("region")){
				int start=inputLine.indexOf("region");
				int end= inputLine.substring(start+9).indexOf(",")-1;
				r.setRegion(inputLine.substring(start+9,start+9+end));
			}
			while(inputLine.contains("postcode")){
				int start=inputLine.indexOf("postcode");
				int end= inputLine.indexOf("quality");
				r.addNearPC(inputLine.substring(start+11, end-3));
				inputLine=inputLine.substring(end+10);
			}
		}
		r.setResponseText(response.toString());
		in.close();
		return r;
	}

	protected Response sendPost(String url)throws Exception{
		return null;
	}

}