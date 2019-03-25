import java.util.ArrayList;

public class Response {
	private int responseCode;
	private String response;
	private String country;
	private String region;
	private ArrayList<String> nearPostcodes;
	
	protected Response(int responseCode,String response){
		this.responseCode=responseCode;
		this.response=response;
		this.nearPostcodes=new ArrayList<String>();
	}
	
	protected Response(int responseCode){
		this.responseCode=responseCode;
		this.nearPostcodes=new ArrayList<String>();
	}
	
	protected String getResponseText(){
		return response;
	}
	
	protected int getResponseCode(){
		return responseCode;
	}
	
	protected String getNearPCs(){
		return this.nearPostcodes.toString();
	}
	
	protected String getCountry(){
		return this.country;
	}
	
	protected String getRegion(){
		return this.region;
	}
	
	protected void setResponseText(String text){
		this.response=text;
	}
	
	protected void setResponseCode(int code){
		this.responseCode=code;
	}
	protected void setCountry(String country){
		this.country=country;
	}
	
	protected void setRegion(String region){
		this.region=region;
	}
	
	protected void addNearPC(String postcode){
		this.nearPostcodes.add(postcode);
	}
	
}
