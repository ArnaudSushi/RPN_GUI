import java.util.ArrayList;

class Stack {

	private ArrayList<Float> registers;
	
	public Stack() {
		this.registers = new ArrayList<Float>();
	}
	
	public String printResult() {
		if (this.registers.size() == 1) {
			return "" + this.registers.get(0);
		}
		return "";
	}
	
	public ArrayList<Float> getRegisters() {
		return this.registers;
	}
		
	public float push(float number) {
		this.registers.add(0, number);
		return number;
	}
	
	private void checkTwoOperands() throws IllegalArgumentException {
		if (this.registers.size() < 2) throw new IllegalArgumentException();
	}
	
	private float pop() {
		return this.registers.remove(0);
	}
	
	public void clear() {
		this.registers.clear();
	}
	
	//Operations
	public String add() {
		try {
			this.checkTwoOperands(); //Throws exception if less than 2 operands.
			this.registers.add(0, this.pop() + this.pop());
			return String.valueOf(this.registers.get(0));
		} catch (IllegalArgumentException e) {
			return "Excpecting at least 2 numbers in the stack to perform addition";
		}
	}
	
	public String sub() {
		try {
			this.checkTwoOperands(); //Throws exception if less than 2 operands.
			this.registers.add(0, - this.pop() + this.pop());
			return String.valueOf(this.registers.get(0));
		} catch (IllegalArgumentException e) {
			return "Excpecting at least 2 numbers in the stack to perform substraction";
		}
	}
	
	public String mul() {
		try {
			this.checkTwoOperands(); //Throws exception if less than 2 operands.
			this.registers.add(0, this.pop() * this.pop());
			return String.valueOf(this.registers.get(0));
		} catch (IllegalArgumentException e) {
			return "Excpecting at least 2 numbers in the stack to perform multiplication";
		}
	}
	public String div() {
		try {
			this.checkTwoOperands(); //Throws exception if less than 2 operands.
			this.registers.add(0, 1/this.pop() * this.pop());
			return String.valueOf(this.registers.get(0));
		} catch (IllegalArgumentException e) {
			return "Excpecting at least 2 numbers in the stack to perform division";
		}
	}
}