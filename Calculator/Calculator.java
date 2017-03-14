import javax.swing.*;
import static javax.swing.GroupLayout.Alignment.*;

/* Calculator class is a child of JFrame and is responsible for containing and
   the layout of the UI elements of the program
	It contains the following 4 main interface elements of the calculator:
		NumberPanel
		ButtonPanel
		RadioPanel
		CalcMenuBar
*/
public class Calculator
extends JFrame {
	private final NumberPanel np;	//contains the text field displaying the input/output and 
						//the bit field
	private final ButtonPanel bp;	//contains all the number and operation buttons

	private final RadioPanel rp;	//contains radio buttons controlling the current radix and
						//number of bits to display in the bit field

	private final CalcMenuBar mb;	//contains the menu items

	public Calculator() {
		np = new NumberPanel();
		bp = new ButtonPanel(this);
		rp = new RadioPanel(this);
		mb = new CalcMenuBar(this);

		this.setJMenuBar(mb);

		//Set the layout of the UI elements
		GroupLayout layout = new GroupLayout(this.getContentPane());
		this.getContentPane().setLayout(layout);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(layout.createParallelGroup(CENTER, false)
			.addComponent(np)
			.addGroup(layout.createSequentialGroup()
				.addComponent(rp)
				.addContainerGap(5, 5)
				.addComponent(bp))
		);

		layout.setVerticalGroup(layout.createSequentialGroup()
			.addComponent(np)
			.addContainerGap(5, 5)
			.addGroup(layout.createParallelGroup(BASELINE, false)
				.addComponent(rp)
				.addComponent(bp))
		);
	}

	//Getter method for the NumberPanel
	public NumberPanel getNumberPanel() {
		return np;
	}

	//Getter method for the ButtonPanel
	public ButtonPanel getButtonPanel() {
		return bp;
	}
}
