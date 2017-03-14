import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import static javax.swing.GroupLayout.Alignment.*;
import java.awt.Font;

/*RadioPanel class is a child of JPanel.  It organizes and updates the radio panels
	on the calculator.  The contained radio panel UI are:
		radixPanel - JPanel
		wordPanel - JPanel
*/
public class RadioPanel
extends JPanel implements ActionListener {
	private JRadioButton	hexButton,		//radix buttons
					decButton,
					octButton,
					binButton,
					qWordButton,	//word buttons
					dWordButton,
					wordButton,
					byteButton;

	private JPanel	radixPanel,
				wordPanel;

	private final Calculator owner;

	public RadioPanel(Calculator owner) {
		this.owner = owner;
		Font f = new Font("Default", Font.PLAIN, 10);

		//Set up the radix and word panels
		setRadixPanel(f);
		setWordPanel(f);

		//Lay out the radix and word panel on the RadioPanel
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(false);

		layout.setHorizontalGroup(layout.createParallelGroup(LEADING, false)
			.addComponent(radixPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			.addComponent(wordPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);

		layout.setVerticalGroup(layout.createSequentialGroup()
			.addComponent(radixPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			.addComponent(wordPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
	}

	/*Input:	Font f for the radio button labels				*
	 *Function:	Initializes the radix radio buttons and radix panel	*/
	private void setRadixPanel(Font f) {
		//Initialize each button and attach action listener
		hexButton = new JRadioButton("Hex");
		hexButton.setFont(f);
		hexButton.addActionListener(this);

		decButton = new JRadioButton("Dec", true);
		decButton.setFont(f);
		decButton.addActionListener(this);

		octButton = new JRadioButton("Oct");
		octButton.setFont(f);
		octButton.addActionListener(this);

		binButton = new JRadioButton("Bin");
		binButton.setFont(f);
		binButton.addActionListener(this);

		//Add each button to a button group
		ButtonGroup bg = new ButtonGroup();
		bg.add(hexButton);
		bg.add(decButton);
		bg.add(octButton);
		bg.add(binButton);

		//Create the radixPanel and add each button
		radixPanel = new JPanel();
		radixPanel.add(hexButton);
		radixPanel.add(decButton);
		radixPanel.add(octButton);
		radixPanel.add(binButton);

		//Set the layout of the panel
		radixPanel.setLayout(new BoxLayout(radixPanel, BoxLayout.PAGE_AXIS));
		radixPanel.setBorder(BorderFactory.createLineBorder(Color.black));
	}

	/*Input:	Font f for the radio button labels				*
	 *Function:	Initializes the word radio buttons and word panel	*/
	private void setWordPanel(Font f) {
		//Initialize each button and attach action listener
		qWordButton = new JRadioButton("Qword", true);
		qWordButton.setFont(f);
		qWordButton.addActionListener(this);

		dWordButton = new JRadioButton("Dword");
		dWordButton.setFont(f);
		dWordButton.addActionListener(this);

		wordButton = new JRadioButton("Word");
		wordButton.setFont(f);
		wordButton.addActionListener(this);

		byteButton = new JRadioButton("Byte");
		byteButton.setFont(f);
		byteButton.addActionListener(this);

		//Add each button to a button group
		ButtonGroup bg = new ButtonGroup();
		bg.add(qWordButton);
		bg.add(dWordButton);
		bg.add(wordButton);
		bg.add(byteButton);

		//Create the wordPanel and add each button
		wordPanel = new JPanel();
		wordPanel.add(qWordButton);
		wordPanel.add(dWordButton);
		wordPanel.add(wordButton);
		wordPanel.add(byteButton);

		//Set the layout of the panel
		wordPanel.setLayout(new BoxLayout(wordPanel, BoxLayout.PAGE_AXIS));
		wordPanel.setBorder(BorderFactory.createLineBorder(Color.black));
	}

	/*Input:	ActionEvent from Calculator								*
	 *Function:	Performs instructions associated with the change of a radio button	*/
	public void actionPerformed(ActionEvent e) {
		Object temp = e.getSource();
		if(temp == hexButton) {		//Changes the radix to 16
			//Enables hex buttons on ButtonPanel
			owner.getButtonPanel().enableHexButtons();

			//Parses number displayed in NumPanel using the current radix
			String s = owner.getNumberPanel().getNumDisplay().getText();
			long l = Long.parseLong(s, CalcEngine.getRadix());

			//Changes current radix to hex
			CalcEngine.setRadix(CalcEngine.Radix.HEX);

			//Writes number back to display in hex radix
			owner.getNumberPanel().getNumDisplay().setText(Long.toString(l, 16).toUpperCase());
		}
		else if(temp == decButton) {	//Changes the radix to 10 using same flow as above
			owner.getButtonPanel().enableDecButtons();

			String s = owner.getNumberPanel().getNumDisplay().getText();
			long l = Long.parseLong(s, CalcEngine.getRadix());

			CalcEngine.setRadix(CalcEngine.Radix.DEC);

			owner.getNumberPanel().getNumDisplay().setText(Long.toString(l, 10));
		}
		else if(temp == octButton) {	//Changes the radix to 8 using same flow as above
			owner.getButtonPanel().enableOctButtons();

			String s = owner.getNumberPanel().getNumDisplay().getText();
			long l = Long.parseLong(s, CalcEngine.getRadix());

			CalcEngine.setRadix(CalcEngine.Radix.OCT);

			owner.getNumberPanel().getNumDisplay().setText(Long.toString(l, 8));
		}
		else if(temp == binButton) {	//Changes the radix to 2 using same flow as above
			owner.getButtonPanel().enableBinButtons();

			String s = owner.getNumberPanel().getNumDisplay().getText();
			long l = Long.parseLong(s, CalcEngine.getRadix());

			CalcEngine.setRadix(CalcEngine.Radix.BIN);

			owner.getNumberPanel().getNumDisplay().setText(Long.toString(l, 2));
		}	//Word buttons set the visible number of bits on the bitPanel of the NumPanel
		else if(temp == qWordButton) {
			owner.getNumberPanel().setBitPanel(16);
		}
		else if(temp == dWordButton) {
			owner.getNumberPanel().setBitPanel(8);
		}
		else if(temp == wordButton) {
			owner.getNumberPanel().setBitPanel(4);
		}
		else {
			owner.getNumberPanel().setBitPanel(2);
		}
	}
}
