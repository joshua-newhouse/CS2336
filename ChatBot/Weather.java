import javax.json.stream.JsonParser;
import javax.json.Json;
import java.io.*;
import java.util.*;

public class Weather {
	public static final int HELP = 0;
	public static final int UPDATE = 1;
	public static final int GET_WEATHER = 2;
	public static final int QUIT = 3;
	public static final int NULL = 100;

	//To hold cached weather responses from api
	private static final ArrayList<WeatherObj> list = new ArrayList(20);
	private static Date lastUpdate;

	//Holds weather requests made by users on IRC channel
	private static final ArrayList<String> requestQueue = new ArrayList(10);
	//Holds weather requests made by users that are not in list
	private static final ArrayList<String> newWxEntries = new ArrayList(10);

	//Holds the output message
	private static final StringBuilder output = new StringBuilder("");

	//Constructs the list with the 20 largest cities in US
	public Weather() {
		String biggestUSCities = "5128581,"	//New York*
								+"5368361,"	//Los Angeles*
								+"4887398,"	//Chicago*
								+"4699066,"	//Houston*
								+"4560349,"	//Philadelphia*
								+"5308655,"	//Phoenix*
								+"4726206,"	//San Antonio*
								+"5391811,"	//San Diego*
								+"4684888,"	//Dallas*
								+"5392171,"	//San Jose*
								+"4671654,"	//Austin*
								+"4160021,"	//Jacksonville, FL*
								+"4259418,"	//Indianapolis*
								+"4509177,"	//Columbus, OH*
								+"4460243,"	//Charlotte*
								+"5809844,"	//Seattle*
								+"5419384,"	//Denver*
								+"5520993,"	//El Paso*
								+"4990729,"	//Detroit*
								+"4138106";	//Washington D.C.*

		try {
			BufferedReader br = WeatherAPI.getCurrentWeather(
					biggestUSCities, WeatherAPI.GROUP);
			addWeatherResponses(br);
			br.close();
		}
		catch(Exception ex) {
			System.err.println(ex.toString());
		}
	}

	/*Parses the json response from the api server and adds the weather entries
	 * to list
	*/ 
	private void addWeatherResponses(Reader br) {
		JsonParser parser = Json.createParser(br);
		while(parser.hasNext()) {
			System.out.println("Creating next obj");
			WeatherObj w = new WeatherObj();
			if(w.create(parser)) {
				list.add(w);
				System.out.println("Obj added");
			}
			else {
				w = null;
				System.out.println("No obj created; bad response");
			}
		}
		parser.close();
	}

	/*Loops through the list and puts the weather associated with a matching entry in
	 * requestQueue into output and deletes the entry from requestQueue.
	 * If there is no match in list then the request is moved to the
	 * newWxEntries list to be updated from the api server.
	 * Returns true if there is another weather request in requestQueue; false otherwise
	*/
	public boolean hasResponse() {
		output.delete(0, output.length());
		if(requestQueue.isEmpty())
			return false;
		else {
			int len = list.size();
			for(int i = 0; i < len; i++)
				if(list.get(i).city.compareToIgnoreCase(requestQueue.get(0)) == 0) {
					output.append(list.get(i).toString()).append("\nBREAK");
					requestQueue.remove(0);
					return true;
				}
			output.append(requestQueue.get(0)).append(" not available.  " +
					"Issue WEATHER UPDATE command.\n");
			newWxEntries.add(requestQueue.get(0));
			requestQueue.remove(0);
			return true;
		}
	}
	
	/*Parses String s to determine the type of command: HELP, UPDATE, QUIT, or
	 * GET_WEATHER
	 * If help is requested it queues the help message; if it is a GET_WEATHER
	 * request if parses out the cities and adds them to requestQueue
	 * Returns HELP, UPDATE, QUIT, or GET_WEATHER
	*/
	public static int parse(String s) {
		System.err.println(s);
		Scanner scanner = new Scanner(s);
		scanner.next();

		//Parse string into command options
		while(scanner.hasNext()) {
			String temp = scanner.next();
			System.err.println(temp);
			if(temp.equals("HELP")) {
				queueHelpMsg();
				return HELP;
			}
			else if(temp.equals("UPDATE"))
				return UPDATE;
			else if(temp.equals("QUIT"))
				return QUIT;
			else {
				temp = temp.replace('&', ' ');
				if(!requestQueue.contains(temp))
					requestQueue.add(temp);
			}
		}		
		scanner.close();

		return GET_WEATHER;
	}
	
	//Puts help message into the output variable
	private static void queueHelpMsg() {
		output.delete(0, output.length());
		output.append("Function is to provide weather of input city.\n" +
			"To get weather in a given city enter WEATHER followed by " +
			"a space separated list of cities. For best results type: city&name,state\n" +
			"ex: WEATHER Dallas,TX New&York District&of&Columbia\n" + 
			"The command WEATHER UPDATE forces the bot to update its " +
			"cached weather.  Otherwise weather is updated hourly\n");
	}

	/*Loops through the cities in newWxEntries and calls the weather api.  Adds
	 * the new weather responses to the cache (list)
	*/
	public void update() {
		output.delete(0, output.length());
		BufferedReader br;
		while(!newWxEntries.isEmpty()) {
			try {
				br = WeatherAPI.getCurrentWeather(
					newWxEntries.get(0), WeatherAPI.CITY);
				addWeatherResponses(br);
				list.get(list.size() - 1).city = newWxEntries.get(0);
				newWxEntries.remove(0);
				
				br.close();
				Thread.sleep(1000);
			}
			catch(Exception ex) {
				System.err.println(ex.toString());
				output.append(newWxEntries.get(0))
					.append(" not a valid city name.  ");
				newWxEntries.remove(0);
			}
		}
		output.append("Update complete.\n");
	}

	//Returns the output message as a string
	public static String getOutputMsg() {
		return output.toString();
	}
}
