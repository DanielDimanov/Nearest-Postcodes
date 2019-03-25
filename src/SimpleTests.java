import static org.junit.Assert.*;


import org.junit.Test;

public class SimpleTests {

	private static HttpRequestHandle http;
	private static Response r;
	private static String url;
	@Test
	public void testNearest() throws Exception {
		http= new HttpRequestHandle("Mozila/5.0");
		url="http://api.postcodes.io/postcodes/CB30FA/Nearest";
		r=http.sendGet(url);
		assertEquals("[CB3 0FA, CB3 0GT, CB3 0FT]",r.getNearPCs());
	}
	
	@Test
	public void testValidate() throws Exception {
		http= new HttpRequestHandle("Mozila/5.0");
		url="http://api.postcodes.io/postcodes/CB30FA";
		assertEquals(true,Runner.validatePostcode(url,r,http));
	}
	

}
