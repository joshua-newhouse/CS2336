import java.io.*;
import java.net.*;
import java.util.*;

public class IRCServer {
	private static final String server = "irc.freenode.net";
	private static final String nick = "chat_bot";
	private static final String login = "chat_bot";
	private static final String channel = "#cs2336";

	private static Socket socket;
	private static BufferedWriter writer;
	private static BufferedReader reader;

	/*Connects to the IRC server.
	 * Returns true if the connection is good; false otherwise.
	*/
	public static boolean connect() {
		try {
			socket = new Socket(server, 6665);	//6665, 6666, 6667, 8000, 8001, 8002
			writer = new BufferedWriter(
				new OutputStreamWriter(socket.getOutputStream()));
			reader = new BufferedReader(
				new InputStreamReader(socket.getInputStream()));

			writer.write("NICK " + nick + "\r\n");
			writer.write("USER " + login +
				" 8 * : Java IRC Chat Bot\r\n");
			writer.flush();

			String line = null;
			while((line = reader.readLine( )) != null) {
				System.err.println(line);
				if(line.contains("004"))
					break;
				else if(line.contains("433")) {
					System.err.println("Nickname is already in use.");
					return false;
				}
			}

			writer.write("JOIN " + channel + "\r\n");
			writer.flush();

		}
		catch(Exception ex) {
			System.err.println(ex.toString());
			return false;
		}

		return true;
	}

	/*Returns a String with the next line read from the BufferedReader
	 * associated with the IRC server
	*/
	public static String readLine() throws Exception {
		return reader.readLine();
	}

	//Responds to a ping from the IRC server with input String l
	public static void respondToPing(String l) throws Exception {
		writer.write("PONG " + l + "\r\n");
		writer.write("PRIVMSG " + channel + "\r\n");
		writer.flush();
		System.err.println("Responded to PING: PONG " + l);
	}

	//Sends its input String msg as a message over the IRC channel.
	public static void respond(String msg) throws Exception {
		System.err.println(msg);
		Scanner scanner = new Scanner(msg);
		while(scanner.hasNext()) {
			writer.write("PRIVMSG " + channel + " : " + scanner.nextLine() + "\r\n");
			writer.flush();
			Thread.sleep(1000);
		}
		
		scanner.close();
	}
}
