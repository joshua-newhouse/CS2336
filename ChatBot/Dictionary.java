import java.util.*;
import javax.json.stream.JsonParser;
import javax.json.Json;
import javax.json.stream.JsonParser.Event;

public class Dictionary {
	public static final int HELP = 0;
	public static final int GET_DEFINITION = 1;
	
	//To hold output message
	private static final StringBuilder output = new StringBuilder();
	
	//Word to lookup definitions of
	private static String word;
	
	//Returns the output message as a string
	public static String getOutputMsg() {
		return output.toString();
	}
	
	//Puts help message into the output variable
	private static void queueHelpMsg() {
		output.delete(0, output.length());
		output.append("Function is to provide definition of input word.\n" +
			"To get definition of a word enter DEFINITION followed by the word or phrase.\n" +
			"ex word: DEFINITION house\n" + "ex phrase: DEFINITION house and home\n");
	}
	
	/*Parses String s to determine the type of command: HELP or get definition
	 * of a word.
	 * If help is requested it queues the help message; if a definition is
	 * requested it calls the getDefinitions method.
	 * Returns HELP or GET_DEFINITION
	*/
	public static int parse(String s){
		System.err.println(s);
		Scanner scanner = new Scanner(s);
		scanner.next();

		if(scanner.hasNext()) {
			String temp = s.substring(s.indexOf(scanner.next()));
			System.err.println(temp);
			if(temp.equals("HELP")) {
				queueHelpMsg();
				return HELP;
			}
			else {
				temp = temp.replace(' ', '_').toLowerCase();
				word = temp;
			}
		}		
		scanner.close();
		System.err.println("getting definition of " + word);
		getDefinitions();

		return GET_DEFINITION;
	}
	
	/*Calls the DictionaryAPI for word and puts the returned definitions into
	 * output.
	*/	
	private static void getDefinitions() {
		output.delete(0, output.length());
		try {
			JsonParser parser = Json.createParser(DictionaryAPI.callApi(word));
			Event e;
			int i = 1;
			while(parser.hasNext()) {
				e = parser.next();
				if(e == JsonParser.Event.KEY_NAME)
					if(parser.getString().equals("definitions")) {
						parser.next();
						parser.next();
						output.append("Definition ").append(i++).append(": ")
								.append(parser.getString()).append("\n");
					}
			}
			parser.close();
		}
		catch(Exception ex) {
			System.err.println(ex.getStackTrace());
			output.append(word).append(" is not in the dictionary.\n");
		}
	}
}
