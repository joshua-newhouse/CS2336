import javax.swing.*;

/*This class contains the main method
	It creates a calculator window and sets its properties */
public class Driver {
	public static Calculator window;

	public static void main(String[] args) {
		int width, height;

		//Set look and feel for Windows OS
		if(System.getProperty("os.name").regionMatches(true, 0, "windows", 0, 7)) {
			width = 490;
			height = 400;
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			}
			catch(Exception e) {
				System.out.println(e.toString());
			}
		}
		else {	//Set look and feel for non-Windows OS(Only tested in Ubuntu)
			width = 515;
			height = 395;
		}

		//Set properties for Calculator window
		window = new Calculator();
		window.setSize(width, height);
		window.setVisible(true);
		window.setTitle("Calculator");
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
