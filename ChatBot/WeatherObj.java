import java.util.Date;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;

/*Class representing a weather entry.  Contains weather data for a city and the
 * method for creating an entry from the json response.
 */
public class WeatherObj {
	private Date lastUpdate;
	public String city;
	private final WeatherData data;

	public WeatherObj() {
		this.data = new WeatherData();
	}

	/*Parses the json response to obtain the weather object's field values.
	 * Returns true if the object was successfully created; false otherwise.
	*/
	public boolean create(JsonParser parser) {
		boolean goodData = true;
		boolean inObject = true;
		Event e;

		do {
			e = parser.next();
			if(e == JsonParser.Event.KEY_NAME)
				switch(parser.getString()) {
				case "description":
					parser.next();
					data.description += parser.getString() + "; ";
					break;
				case "temp":
					parser.next();
					data.temp = parser.getInt();
					break;
				case "pressure":
					parser.next();
					data.pres = parser.getInt();
					break;
				case "humidity":
					parser.next();
					data.humid = parser.getInt();
					break;
				case "visibility":
					parser.next();
					data.visibility = (int)((float)parser.getInt() / 1609.34f + 0.5f);
					break;
				case "speed":
					parser.next();
					data.wSpeed = parser.getInt();
					break;
				case "deg":
					parser.next();
					data.wDir = parser.getInt();
					break;
				case "dt":
					parser.next();
					lastUpdate = new Date(parser.getLong() * 1000);
					break;
				case "country":
					parser.next();
					data.country = parser.getString();
					break;
				case "sunrise":
					parser.next();
					data.sunrise = new Date(parser.getLong() * 1000);
					break;
				case "sunset":
					parser.next();
					data.sunset = new Date(parser.getLong() * 1000);
					break;
				case "id":
					parser.next();
					data.id = parser.getInt();
					break;
				case "name":
					parser.next();
					city = parser.getString();
					inObject = false;
					while((e = parser.next()) != JsonParser.Event.END_OBJECT)
						;
					break;
				case "cod":
					parser.next();
					goodData = parser.getInt() == 200;
					break;
				default:
					break;
				}
		} while(inObject);
		return goodData;
	}

	//Returns a String representing the weather object
	@Override
	public String toString() {
		String ret_val = "Weather at " + city + ", " + data.country + " as of " + lastUpdate.toString()
				+ data.toString();
		return ret_val;
	}
}
