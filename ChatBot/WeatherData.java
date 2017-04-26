import java.util.Date;

//Class that contains data for the WeatherObj class
public class WeatherData {
	public int id;
	public String country;

	public Date	sunrise;
	public Date sunset;

	public int wSpeed;
	public int wDir;
	public int visibility;
	public int temp;
	public int pres;
	public int humid;

	public String description = "";

	//Returns a String representation of the weather data
	@Override
	public String toString() {
		String ret_val = "\nWeather: " + description
					   + "\nTemperature (F): " + temp
					   + "\nPressure (mbar): " + pres
					   + "\nHumidity (%): " + humid
					   + "\nVisibility (mi): " + visibility
					   + "\nWind: " + wSpeed + " mph @ " + wDir + " deg"
					   + "\nSunrise: " + sunrise.toString()
					   + "\nSunset: " + sunset.toString();
		
		return ret_val;
	}	
}
