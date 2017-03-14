import javax.swing.*;
import java.awt.event.*;
import java.awt.datatransfer.*;
import java.awt.Toolkit;
import java.awt.Desktop;
import java.net.URI;

/*CalcMenuBar class is a child of JMenuBar.  It contains the menu bar UI
	on the calculator.  The menu bar is organized as follows:
		View	-> hide check box
		Edit	-> copy
		Help	-> Online Help
			-> About
*/
public class CalcMenuBar
extends JMenuBar implements ActionListener, ItemListener {
	private final JMenu	view,
					edit,
					help;

	private final JCheckBoxMenuItem hide;
	private final JMenuItem	copy,
					onlineHelp,
					about;

	private final Calculator owner;

	public CalcMenuBar(Calculator owner) {
		view = new JMenu("View");
		edit = new JMenu("Edit");
		help = new JMenu("Help");
		this.owner = owner;

		//Add menu components to the menu bar
		hide = new JCheckBoxMenuItem("Hide");
		hide.addItemListener(this);
		view.add(hide);

		copy = new JMenuItem("Copy");
		copy.addActionListener(this);
		edit.add(copy);

		onlineHelp = new JMenuItem("View Help");
		onlineHelp.addActionListener(this);
		about = new JMenuItem("About Calculator");
		about.addActionListener(this);
		help.add(onlineHelp);
		help.add(about);

		this.add(view);
		this.add(edit);
		this.add(help);
	}

	/*Input:	ItemEvent from Calculator					*
	 *Function:	Sets the calculator to visible or not if View->hide 	*
	 *		is selected								*/
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange() == ItemEvent.SELECTED)
			owner.getContentPane().setVisible(false);
		else
			owner.getContentPane().setVisible(true);
	}

	/*Input:	ActionEvent from Calculator				*
	 *Function:	Performs the instructions associated with the	*
	 *		action event source					*/
	public void actionPerformed(ActionEvent e) {
		Object temp = e.getSource();
		if(temp == copy) {	//Copies the number being displayed in NumPanel to the clipboard
			StringSelection strSel = new StringSelection(
				owner.getNumberPanel().getNumDisplay().getText());
			Clipboard clpbd = Toolkit.getDefaultToolkit().getSystemClipboard();
			clpbd.setContents(strSel, null);
		}
		else if(temp == onlineHelp) {
			try {			//Performs the same action as the MS Calc.exe
				Desktop.getDesktop().browse(new URI(
					"https://support.microsoft.com/en-us/allproducts"));
			}
			catch(Exception ex) {
			}
		}
		else {			//Displays message with information about this program
			JOptionPane.showMessageDialog(owner,
				"Calc.exe clone\nAuthor: Joshua Newhouse");
		}
	}
}
