import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.*;
import static javax.swing.GroupLayout.Alignment.*;
import java.awt.Font;

/*ButtonPanel class is a child of JPanel.  It organizes and updates the buttons
	on the calculator.
*/
public class ButtonPanel
extends JPanel implements ActionListener {
	private final JButton[] number = new JButton[16];	//Array of numeral buttons
	private final JButton[] ops = new JButton[15];		//Array of operation btns
	private final JButton[] empty = new JButton[15];	//Array of empty btns for spacing
	private final Calculator owner;
        
	private final int btnDim = 28;	//Dimensions of the buttons

	public ButtonPanel(Calculator owner) {
		//Initialize the button panel and its buttons
		this.owner = owner;
		setNumBtns();
		setOpsBtns();
		setEmptyBtns();

		//Lay out the buttons on the ButtonPanel
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(false);

		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup(LEADING, false)
				.addComponent(ops[14], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(empty[0], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(empty[1], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(empty[2], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(empty[3], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(empty[4], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			.addGroup(layout.createParallelGroup(LEADING, false)
				.addComponent(ops[13], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(empty[5], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(empty[6], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(empty[7], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(empty[8], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(empty[9], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			.addGroup(layout.createParallelGroup(LEADING, false)
				.addComponent(number[10], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(number[11], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(number[12], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(number[13], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(number[14], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(number[15], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			.addGroup(layout.createParallelGroup(LEADING, false)
				.addGroup(layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup(LEADING, false)
						.addComponent(empty[10], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(ops[12], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(number[7], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(number[4], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(number[1], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGroup(layout.createParallelGroup(LEADING, false)
						.addComponent(empty[11], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(ops[11], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(number[8], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(number[5], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(number[2], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
				.addComponent(number[0], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			.addGroup(layout.createParallelGroup(LEADING, false)
				.addComponent(empty[12], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(ops[10], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(number[9], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(number[6], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(number[3], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(ops[9], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			.addGroup(layout.createParallelGroup(LEADING, false)
				.addComponent(empty[13], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(ops[8], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(ops[7], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(ops[6], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(ops[5], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(ops[4], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			.addGroup(layout.createParallelGroup(LEADING, false)
				.addComponent(empty[14], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(ops[3], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(ops[2], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(ops[1], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(ops[0], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);

		layout.setVerticalGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup(BASELINE, false)
				.addComponent(ops[14], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(ops[13], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(number[10], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(empty[10], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(empty[11], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(empty[12], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(empty[13], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(empty[14], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			.addGroup(layout.createParallelGroup(BASELINE, false)
				.addComponent(empty[0], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(empty[5], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(number[11], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(ops[12], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(ops[11], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(ops[10], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(ops[8], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(ops[3], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			.addGroup(layout.createParallelGroup(BASELINE, false)
				.addComponent(empty[1], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(empty[6], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(number[12], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(number[7], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(number[8], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(number[9], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(ops[7], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(ops[2], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			.addGroup(layout.createParallelGroup(BASELINE, false)
				.addComponent(empty[2], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(empty[7], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(number[13], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(number[4], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(number[5], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(number[6], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(ops[6], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(ops[1], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			.addGroup(layout.createParallelGroup(TRAILING, false)
				.addGroup(layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup(BASELINE, false)
						.addComponent(empty[3], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(empty[8], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(number[14], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(number[1], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(number[2], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(number[3], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(ops[5], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGroup(layout.createParallelGroup(BASELINE, false)
						.addComponent(empty[4], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(empty[9], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(number[15], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(number[0], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(ops[9], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(ops[4], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
				.addComponent(ops[0], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
	}

	/*Initialize the numeral buttons by setting size and assigning:	*
	 *	display character								*
	 *	action listener								*/
	private void setNumBtns() {
		for(int i = 0; i < 10; i++) {
			number[i] = new JButton(Integer.toString(i));
                        number[i].setPreferredSize(new Dimension(btnDim, btnDim));
			number[i].addActionListener(this);
		}
		for(int i = 10; i < 16; i++) {
			number[i] = new JButton(Character.toString((char)('A' - 10 + i)));
                        number[i].setPreferredSize(new Dimension(btnDim, btnDim));
			number[i].addActionListener(this);
		}

		enableDecButtons();
	}

	/*Initialize the operations btns by setting size and assigning:	*
	 *	display character								*
	 *	action listener								*/
	private void setOpsBtns() {
		ops[0] = new JButton("=");
		ops[1] = new JButton("\u215Fx");
		ops[2] = new JButton("%");
		ops[3] = new JButton("\u221A");
		ops[4] = new JButton("+");
		ops[5] = new JButton("-");
		ops[6] = new JButton("\u00D7");
		ops[7] = new JButton("\u00f7");
		ops[8] = new JButton("\u00B1");
		ops[9] = new JButton("\u22C5");
		ops[10] = new JButton("C");
		ops[11] = new JButton("CE");
		ops[12] = new JButton("\u2190");
		ops[13] = new JButton("Mod");
		ops[14] = new JButton("Quot");

		Font f = new Font("Default", Font.PLAIN, 10);
		ops[1].setFont(f);
		ops[10].setFont(f);
		ops[11].setFont(f);
		ops[13].setFont(f);
		ops[14].setFont(f);

		for(int i = 0; i < ops.length; i++) {
                        ops[i].setPreferredSize(new Dimension(btnDim, btnDim));
			switch(i) {
			case 1: case 2: case 3: case 9:
				continue;
			default:
				ops[i].addActionListener(this);
			};
		}

		//These ops are disabled for the programming calculator
		ops[1].setEnabled(false);
		ops[2].setEnabled(false);
		ops[3].setEnabled(false);
		ops[9].setEnabled(false);
	}

	//Initializes the empty buttons
	private void setEmptyBtns() {
		for(int i = 0; i < empty.length; i++) {
			empty[i] = new JButton("");
                        empty[i].setPreferredSize(new Dimension(btnDim, btnDim));
			empty[i].setEnabled(false);
		}
	}

	//Enables the hex buttons (numerals 0 through F)
	public void enableHexButtons() {
		enableNumberButtons(16);
	}

	//Enables the dec buttons (numerals 0 through 9)
	public void enableDecButtons() {
		enableNumberButtons(10);
	}

	//Enables the oct buttons (numerals 0 through 7)
	public void enableOctButtons() {
		enableNumberButtons(8);
	}

	//Enables the bin buttons (numerals 0 and 1)
	public void enableBinButtons() {
		enableNumberButtons(2);
	}

	/*Input:	int value of radix					*
	 *Function:	Sets buttons less than input radix to enabled	*
			Disables buttons greater than the input radix	*/
	private void enableNumberButtons(int end) {
		for(int i = 0; i < end; i++)
			number[i].setEnabled(true);
		for(int i = end; i < 16; i++)
			number[i].setEnabled(false);
	}

	/*Input:	ActionEvent from Calculator						*
	 *Function:	Performs instructions associated with the press of a button	*/
	public void actionPerformed(ActionEvent e) {
		JButton temp = (JButton)e.getSource();

		if(temp == ops[0]) {		//=
			if(CalcEngine.getCurrentOperation() == CalcEngine.Operation.NO_OP)
				CalcEngine.setOperand(CalcEngine.OPERAND_ONE, owner.getNumberPanel().getNumDisplay().getText());
			else
				CalcEngine.setOperand(CalcEngine.OPERAND_TWO, owner.getNumberPanel().getNumDisplay().getText());
			CalcEngine.equalsOp();
			owner.getNumberPanel().getNumDisplay().setText(CalcEngine.getMem());
		}
		else if(temp == ops[4]) {	//+
			if(CalcEngine.getCurrentOperation() != CalcEngine.Operation.NO_OP) {
				CalcEngine.setOperand(CalcEngine.OPERAND_TWO, owner.getNumberPanel().getNumDisplay().getText());
				CalcEngine.equalsOp();
				owner.getNumberPanel().getNumDisplay().setText(CalcEngine.getMem());
			}
			CalcEngine.setOperand(CalcEngine.OPERAND_ONE, owner.getNumberPanel().getNumDisplay().getText());
			CalcEngine.setOp(CalcEngine.Operation.ADD);
			owner.getNumberPanel().getNumDisplay().setText("0");
		}
		else if(temp == ops[5]) {	//-
			if(CalcEngine.getCurrentOperation() != CalcEngine.Operation.NO_OP) {
				CalcEngine.setOperand(CalcEngine.OPERAND_TWO, owner.getNumberPanel().getNumDisplay().getText());
				CalcEngine.equalsOp();
				owner.getNumberPanel().getNumDisplay().setText(CalcEngine.getMem());
			}
			CalcEngine.setOperand(CalcEngine.OPERAND_ONE, owner.getNumberPanel().getNumDisplay().getText());
			CalcEngine.setOp(CalcEngine.Operation.SUB);
			owner.getNumberPanel().getNumDisplay().setText("0");
		}
		else if(temp == ops[6]) {	//*
			if(CalcEngine.getCurrentOperation() != CalcEngine.Operation.NO_OP) {
				CalcEngine.setOperand(CalcEngine.OPERAND_TWO, owner.getNumberPanel().getNumDisplay().getText());
				CalcEngine.equalsOp();
				owner.getNumberPanel().getNumDisplay().setText(CalcEngine.getMem());
			}
			CalcEngine.setOperand(CalcEngine.OPERAND_ONE, owner.getNumberPanel().getNumDisplay().getText());
			CalcEngine.setOp(CalcEngine.Operation.MULT);
			owner.getNumberPanel().getNumDisplay().setText("0");
		}
		else if(temp == ops[7] || temp == ops[14]) {	/// and quot
			if(CalcEngine.getCurrentOperation() != CalcEngine.Operation.NO_OP) {
				CalcEngine.setOperand(CalcEngine.OPERAND_TWO, owner.getNumberPanel().getNumDisplay().getText());
				CalcEngine.equalsOp();
				owner.getNumberPanel().getNumDisplay().setText(CalcEngine.getMem());
			}
			CalcEngine.setOperand(CalcEngine.OPERAND_ONE, owner.getNumberPanel().getNumDisplay().getText());
			CalcEngine.setOp(CalcEngine.Operation.DIV);
			owner.getNumberPanel().getNumDisplay().setText("0");
		}
		else if(temp == ops[8]) {	//+/-
			Long l = Long.parseLong(owner.getNumberPanel().getNumDisplay().getText(), CalcEngine.getRadix());
			l *= -1L;
			owner.getNumberPanel().getNumDisplay().setText(Long.toString(l, CalcEngine.getRadix()).toUpperCase());
		}
		else if(temp == ops[10]) {	//C
			owner.getNumberPanel().getNumDisplay().setText("0");
			CalcEngine.clearMem();
		}
		else if(temp == ops[11]) {	//CE
			owner.getNumberPanel().getNumDisplay().setText("0");
		}
		else if(temp == ops[12]) {	//Backspace
			StringBuilder cur = new StringBuilder(
				owner.getNumberPanel().getNumDisplay().getText());
			int len = cur.toString().length();
			if(len == 1)
				cur.setCharAt(0, '0');
			else
				cur.deleteCharAt(len - 1);
			owner.getNumberPanel().getNumDisplay().setText(cur.toString());
		}
		else if(temp == ops[13]) {	//mod
			if(CalcEngine.getCurrentOperation() != CalcEngine.Operation.NO_OP) {
				CalcEngine.setOperand(CalcEngine.OPERAND_TWO, owner.getNumberPanel().getNumDisplay().getText());
				CalcEngine.equalsOp();
				owner.getNumberPanel().getNumDisplay().setText(CalcEngine.getMem());
			}
			CalcEngine.setOperand(CalcEngine.OPERAND_ONE, owner.getNumberPanel().getNumDisplay().getText());
			CalcEngine.setOp(CalcEngine.Operation.MOD);
			owner.getNumberPanel().getNumDisplay().setText("0");
		}
		else {				//number buttons
			for(int i = 0; i < 16; i++) {
				if(temp == number[i]) {
					StringBuilder cur = new StringBuilder(
						owner.getNumberPanel().getNumDisplay().getText());
					if(cur.toString().equals("0"))
						cur.deleteCharAt(0);
					if(i < 10)
						cur.append(i);
					else
						cur.append((char)('A' - 10 + i));
					owner.getNumberPanel().getNumDisplay().setText(cur.toString());
				}
			}
		}

		owner.getNumberPanel().updateNibbles();
	}
}
