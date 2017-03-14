import javax.swing.*;
import static javax.swing.GroupLayout.Alignment.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

/*Number Panel class is a child of JPanel.  It is responsible for the layout and
  update of the number display text field and the bit field
	It contains the following 2 UI elements:
		numDisplay - JTextField
		bitPanel - made up of JLabels
*/
public class NumberPanel
extends JPanel {
	private final JTextField numDisplay;	//Displays user input and calc output
	private final JPanel bitPanel;		//Displays binary rep of number in numDisplay

	private final JLabel[] nibble = new JLabel[16];	//Individual bit elements that make up bitPanel
	private final StringBuilder[] nibStr = new StringBuilder[16];	//Used to construct the text for the nibble JLabels

	private final JLabel[] bitRef = new JLabel[6];	//Labels for the reference bits of the bitPanel

	public NumberPanel() {
		//Set width based on OS
		int fieldWidth;
		fieldWidth = System.getProperty("os.name").regionMatches(true, 0, "windows", 0, 7) ?
			18 : 15;

		//Create numDisplay and set properties
		numDisplay = new JTextField("0", fieldWidth);
		numDisplay.setHorizontalAlignment(JTextField.RIGHT);
		Font f = new Font("Default", Font.PLAIN, 29);
		numDisplay.setFont(f);
		numDisplay.setEditable(false);

		//Create bitPanel and set properties
		createNibbles();
		bitPanel = new JPanel();
		setBitRef();
		setBitPanel();
		bitPanel.setBorder(BorderFactory.createLineBorder(Color.black));

		//Layout the numDisplay and bitPanel on the NumberPanel
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(false);

		layout.setHorizontalGroup(layout.createParallelGroup(LEADING, false)
			.addComponent(numDisplay)
			.addComponent(bitPanel)
		);

		layout.setVerticalGroup(layout.createSequentialGroup()
			.addComponent(numDisplay)
			.addComponent(bitPanel)
		);
	}

	//Getter for numDisplay
	public JTextField getNumDisplay() {
		return numDisplay;
	}

	//Initializes each nibble(JLabel) and sets its properties
	private void createNibbles() {
		Font f = new Font(Font.MONOSPACED, Font.PLAIN, 16);
		for(int i = 0; i < nibble.length; i++) {
			nibStr[i] = new StringBuilder(4);
			nibStr[i].append("0000");
			nibble[i] = new JLabel(nibStr[i].toString());
			nibble[i].setFont(f);
			nibble[i].setForeground(Color.DARK_GRAY);
		}
	}

	/*Input:	target StringBuilder t, source String s				*
	 *Function:	Sets each nibble StringBuilder t to the input String s	*/
	private void setNibStr(StringBuilder t, String s) {
		t.setCharAt(0, s.charAt(0));
		t.setCharAt(1, s.charAt(1));
		t.setCharAt(2, s.charAt(2));
		t.setCharAt(3, s.charAt(3));
	}

	//Updates each nibble JLabel based on the number displayed in the numDisplay
	public void updateNibbles() {
		//Parses the number string in the display to a long
		long l = 0L;
		try {
			l = Long.parseLong(numDisplay.getText(), CalcEngine.getRadix());
		}
		catch(Exception ex) {
			System.out.println(ex.toString());
		}
		//Parses the long to a binary string
		StringBuilder temp = new StringBuilder(Long.toBinaryString(l));

		//Pads the binary string with leading 0's to meet the 64 bit width
		for(int len = temp.length(); len < 64; len++)
			temp.insert(0, '0');

		//Updates each nibble JLabel with its respective 4 bits
		for(int i = 0, j = 60; i < nibStr.length; i++, j -= 4) {
			setNibStr(nibStr[i], temp.substring(j, j + 4));
			nibble[i].setText(nibStr[i].toString());
		}
	}

	//Initializes the bit reference labels and sets their properties
	private void setBitRef() {
		bitRef[0] = new JLabel("0");
		bitRef[1] = new JLabel("15");
		bitRef[2] = new JLabel("31");
		bitRef[3] = new JLabel("32");
		bitRef[4] = new JLabel("47");
		bitRef[5] = new JLabel("63");

		Font f = new Font(Font.MONOSPACED, Font.PLAIN, 12);
		for(int i = 0; i < bitRef.length; i++) {
			bitRef[i].setFont(f);
			bitRef[i].setForeground(Color.GRAY);
		}
	}

	//Lays out the bit panel and sets its properties
	private void setBitPanel() {
		GroupLayout layout = new GroupLayout(bitPanel);
		bitPanel.setLayout(layout);
		layout.setAutoCreateGaps(false);
		layout.setAutoCreateContainerGaps(false);

		//Addjust gap size between each nibble based on OS
		int gapSize = System.getProperty("os.name").regionMatches(true, 0, "windows", 0, 7) ?
			20 : 24;

		//Add each nibble and bit reference JLabel to the BitPanel
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup(LEADING, false)
				.addComponent(nibble[15])
				.addComponent(bitRef[5])
				.addComponent(nibble[7])
				.addComponent(bitRef[2]))
			.addContainerGap(gapSize, gapSize)
			.addGroup(layout.createParallelGroup(LEADING, false)
				.addComponent(nibble[14])
				.addComponent(nibble[6]))
			.addContainerGap(gapSize, gapSize)
			.addGroup(layout.createParallelGroup(LEADING, false)
				.addComponent(nibble[13])
				.addComponent(nibble[5]))
			.addContainerGap(gapSize, gapSize)
			.addGroup(layout.createParallelGroup(LEADING, false)
				.addComponent(nibble[12])
				.addComponent(nibble[4]))
			.addContainerGap(gapSize, gapSize)
			.addGroup(layout.createParallelGroup(LEADING, false)
				.addComponent(nibble[11])
				.addComponent(bitRef[4])
				.addComponent(nibble[3])
				.addComponent(bitRef[1]))
			.addContainerGap(gapSize, gapSize)
			.addGroup(layout.createParallelGroup(LEADING, false)
				.addComponent(nibble[10])
				.addComponent(nibble[2]))
			.addContainerGap(gapSize, gapSize)
			.addGroup(layout.createParallelGroup(LEADING, false)
				.addComponent(nibble[9])
				.addComponent(nibble[1]))
			.addContainerGap(gapSize, gapSize)
			.addGroup(layout.createParallelGroup(TRAILING, false)
				.addComponent(nibble[8])
				.addComponent(bitRef[3])
				.addComponent(nibble[0])
				.addComponent(bitRef[0]))
		);

		layout.setVerticalGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup(BASELINE, false)
				.addComponent(nibble[15])
				.addComponent(nibble[14])
				.addComponent(nibble[13])
				.addComponent(nibble[12])
				.addComponent(nibble[11])
				.addComponent(nibble[10])
				.addComponent(nibble[9])
				.addComponent(nibble[8]))
			.addGroup(layout.createParallelGroup(BASELINE, false)
				.addComponent(bitRef[5])
				.addComponent(bitRef[4])
				.addComponent(bitRef[3]))
			.addGroup(layout.createParallelGroup(BASELINE, false)
				.addComponent(nibble[7])
				.addComponent(nibble[6])
				.addComponent(nibble[5])
				.addComponent(nibble[4])
				.addComponent(nibble[3])
				.addComponent(nibble[2])
				.addComponent(nibble[1])
				.addComponent(nibble[0]))
			.addGroup(layout.createParallelGroup(BASELINE, false)
				.addComponent(bitRef[2])
				.addComponent(bitRef[1])
				.addComponent(bitRef[0]))
		);
	}

	/*Input:	int number of nibbles to be displayed				*
	 *Function:	Sets nibble and bitRef JLabel text to transparent or visible*
	 *		based on the input number of nibbles to be displayed.  Used	*
	 *		in conjunction with the word RadioPanel to only display	*
	 *		specified word lengths in the bitPanel				*/
	public void setBitPanel(int nibbles) {
		Color transparent = new Color(255, 0, 0, 0);

		//Set nibbles visible
		for(int i = 0; i < nibbles; i++)
			nibble[i].setForeground(Color.DARK_GRAY);

		//Set nibbles not visible
		for(int i = nibbles; i < nibble.length; i++)
			nibble[i].setForeground(transparent);

		//Set bitRefs visible or not
		int n;
		switch(nibbles) {
		case 2:
			n = 1;
			break;
		case 4:
			n = 2;
			break;
		case 8:
			n = 3;
			break;
		default:
			n = bitRef.length;
		};

		for(int i = 0; i < bitRef.length; i++)
			if(i < n)
				bitRef[i].setForeground(Color.GRAY);
			else
				bitRef[i].setForeground(transparent);
	}
}
