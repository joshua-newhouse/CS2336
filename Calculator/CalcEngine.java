/*CalcEngine class is the backend of the calculator.
	This class performs the calculations and maintains the radix state
*/
public class CalcEngine {
	//Memory for storing calculations
	private static long memory = 0L;

	//Two operands to operate on
	private static final long[] operand = {0L, 0L};
	public static final int OPERAND_ONE = 0;
	public static final int OPERAND_TWO = 1;

	//Enumeration of available operations
	public static enum Operation {
		NO_OP, ADD, SUB, MULT, DIV, MOD
	}
	private static Operation op = Operation.NO_OP;		//Current operation
	private static Operation prevOp = Operation.NO_OP;	//Previous operation

	//Enumeration of available radixes
	public static enum Radix {
		HEX(16), DEC(10), OCT(8), BIN(2);
		private final int value;

		private Radix(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}
	private static Radix radix = Radix.DEC;	//Current radix; initially 10

	//Getter for the currently set radix
	public static int getRadix() {
		return radix.getValue();
	}

	//Performs the appropriate calculation based on the currently set operation and operands
	public static void equalsOp() {
		if(op == Operation.NO_OP)	//Use last operation if not currently set
			op = prevOp;
		switch(op) {
		case ADD:
			memory = operand[OPERAND_ONE] + operand[OPERAND_TWO];
			break;
		case SUB:
			memory = operand[OPERAND_ONE] - operand[OPERAND_TWO];
			break;
		case MULT:
			memory = operand[OPERAND_ONE] * operand[OPERAND_TWO];
			break;
		case DIV:
			try {
				memory = operand[OPERAND_ONE] / operand[OPERAND_TWO];
			}
			catch(Exception ex) {
				System.out.println(ex.toString());
				clearMem();
			}
			break;
		case MOD:
			try {
				memory = operand[OPERAND_ONE] % operand[OPERAND_TWO];
			}
			catch(Exception ex) {
				System.out.println(ex.toString());
				clearMem();
			}
			break;
		default:
		};
		prevOp = op;		//Updates previous operation
		op = Operation.NO_OP;	//Clears current operation
	}

	//Getter for the first operand
	public static String getOperand() {
		return Long.toString(operand[OPERAND_ONE], radix.getValue()).toUpperCase();
	}

	/*Input:	int operand number, String of numerals				*
	 *Function:	Sets the operand to the number represented by the string	*/
	public static void setOperand(int on, String o) {
		operand[on] = Long.parseLong(o, radix.getValue());
	}

	/*Input:	Operation				*
	 *Function:	Sets the current operation	*/
	public static void setOp(Operation o) {
		op = o;
	}

	//Clears the memory and resets the current and previous operations to NO_OP
	public static void clearMem() {
		memory = 0L;
		prevOp = op = Operation.NO_OP;
	}

	//Returns a String of the number contained in memory in the current radix
	public static String getMem() {
		return Long.toString(memory, radix.getValue()).toUpperCase();
	}

	/*Input:	Radix				*
	 *Function:	Sets the current radix	*/
	public static void setRadix(Radix r) {
		radix = r;
	}

	//Returns the current operation
	public static Operation getCurrentOperation() {
		return op;
	}
}
