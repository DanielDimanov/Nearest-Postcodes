import java.util.Scanner;

public class Runner{
	private static HttpRequestHandle http;
	private static Response r;
	private static String input;
	private static Scanner s;
	private static String url;
	private static String country;
	private static String region;
	private static String nearPCs;

	public static void main(String[] args) throws Exception {
		http = new HttpRequestHandle("Mozilla/5.0");
		s=new Scanner(System.in);
		System.out.println("Please enter a valid UK postcode:");
		input=s.next();
		url = "http://api.postcodes.io/postcodes/";
		url+=input;
		if(validatePostcode(url,r,http)){
			r = http.sendGet(url);
			System.out.println();
			country=r.getCountry();
			region=r.getRegion();
			nearPCs=getNearPCs(url);
			
			System.out.println("The country of the given postcode is: "+country);
			System.out.println("The region of the given postcode is: "+region);
			System.out.println("The nearest postcodes are: " + nearPCs);
		}
		else{
			System.out.println("Please check the format and spelling of your postcode and try again.");
		}
	}
	
	public static String getNearPCs(String url) throws Exception{
		url+="/nearest";
		r=http.sendGet(url);
		return r.getNearPCs();
	}
	public static boolean validatePostcode(String url,Response r,HttpRequestHandle http) throws Exception{
		url+="/validate";
		r= http.sendGet(url);
		int index=r.getResponseText().indexOf("result");
		index+=8;
		String response= r.getResponseText();
		response= response.substring(index, response.length()-2);
		if(response.equals("true")){
			return true;
		}
		else{
			return false;
		}
	}

}