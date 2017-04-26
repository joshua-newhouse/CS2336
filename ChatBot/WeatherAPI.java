import java.net.*;
import java.io.*;

public class WeatherAPI {
	private static final String baseUriGroup = "http://api.openweathermap.org/data/2.5/group?id=";
	private static final String baseUriCity = "http://api.openweathermap.org/data/2.5/weather?q=";
	private static final String appID = "&APPID=81cbb03074e66f3295099eec727276fe";
	private static final String units = "&units=imperial";

	public static final int GROUP = 0;
	public static final int CITY = 1;
	
	/*Makes the api call for the input arguments and returns a BufferedReader
	 * associated with the input stream of the url connection
	*/
	public static BufferedReader getCurrentWeather(String args, int type) throws
	IOException, SocketTimeoutException, UnknownServiceException, FileNotFoundException {
		URL apiCall = type == GROUP ? new URL(baseUriGroup + args + units + appID) :
				new URL(baseUriCity + args + units + appID);
		System.out.println(apiCall.toString());
		URLConnection connection = apiCall.openConnection();

		BufferedReader br = new BufferedReader(
			new InputStreamReader(connection.getInputStream()));
		connection.connect();

		return br;
	}
}
