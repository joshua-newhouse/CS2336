public class Driver {
	public static void main(String[] args) throws Exception {
		Weather weather = new Weather();
		
		//Connect to IRC server
		boolean connected = IRCServer.connect();
		if(!connected) {
			System.err.println("Error connecting to server");
			return;
		}
		
		//Introduce the chat bot on the IRC channel
		IRCServer.respond(
				"I am a chat bot who can provide weather and definitions; " +
				"type WEATHER HELP or DEFINITION HELP for commands.\n");

		//Loop indefinitely while reading text traffic in chat
		String line;
		while((line = IRCServer.readLine()) != null) {
			System.err.println(line);
			
			//Respond to ping requests from server
			if(line.startsWith("PING ")) {
				IRCServer.respondToPing(line.substring(5));
				continue;
			}

			//Capture and parse commands issued over chat to the bot
			int i = Weather.NULL;
			int index;
			if((index = line.indexOf(":WEATHER ")) >= 0)
				i = Weather.parse(line.substring(index + 1));
			else if((index = line.indexOf(":DEFINITION ")) >= 0)
				i = Dictionary.parse(line.substring(index + 1)) + 10;

			//Perform action dictated by the weather command
			switch(i) {
			case Weather.HELP:			//Respond with Wx help msg
				IRCServer.respond(Weather.getOutputMsg());
				break;
			case Weather.GET_WEATHER:	//Respond with weather msg
				while(weather.hasResponse()) {
					IRCServer.respond(Weather.getOutputMsg());
					Thread.sleep(5000);
				}
				break;
			case Weather.UPDATE:		//Update the cached weather with new requests
				weather.update();
				IRCServer.respond(Weather.getOutputMsg());
				break;
			case Weather.QUIT:			//Terminate the bot
				return;
			case Dictionary.HELP + 10:	//Respond with dictionary help msg
				IRCServer.respond(Dictionary.getOutputMsg());
				break;
			case Dictionary.GET_DEFINITION + 10:	//Respond with definitions
				IRCServer.respond(Dictionary.getOutputMsg());
				break;
			default:
				break;
			}
		}
	}
}