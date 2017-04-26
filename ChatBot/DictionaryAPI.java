import java.net.*;
import javax.net.ssl.HttpsURLConnection;
import java.io.*;

public class DictionaryAPI {
	private static final String baseURI =
			"https://od-api.oxforddictionaries.com/api/v1/entries/en/";
	private static final String appID = "4825cc8c";
	private static final String appKey = "8b93c189a56e91db89b440ca1d7e8020";
	
	/*Makes the api call for the given word and returns a BufferedReader
	 * associated with the input stream of the url connection
	*/
	public static BufferedReader callApi(String word)
			throws MalformedURLException, IOException {
		URL url = new URL(baseURI + word);
		HttpsURLConnection urlConnection =
				(HttpsURLConnection) url.openConnection();
		urlConnection.setRequestProperty("Accept", "application/json");
		urlConnection.setRequestProperty("app_id", appID);
		urlConnection.setRequestProperty("app_key", appKey);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(
				urlConnection.getInputStream()));
		urlConnection.connect();
		
		return br;
	}
}
