import java.util.Arrays;
import java.util.ArrayList;

class SyntaxChecker {
	
	private Stack stack;
	private CommandHandler _commandHandler;
	private String resultForPrinting = "";
	
	public SyntaxChecker(Stack stack) {
		this.stack = stack;
		this._commandHandler = new CommandHandler(this.stack);
	}
	
	public String checkInputs(String line) {
		this.resultForPrinting = "";
		ArrayList<String> inputs = new ArrayList<String>(Arrays.asList(line.trim().split("\\s+")));
		try {
			for (String token : inputs) {
				try {
					this.resultForPrinting += String.valueOf(this.stack.push(Float.parseFloat(token))) + " ";
				} catch (NumberFormatException e) {
					switch (token) {
						case "+":
							this.resultForPrinting += this.stack.add() + " ";
							break;
						case "-":
							this.resultForPrinting += this.stack.sub() + " ";
							break;
						case "*":
							this.resultForPrinting += this.stack.mul() + " ";
							break;
						case "/":
							this.resultForPrinting += this.stack.div() + " ";
							break;
						default:
							if (inputs.size() == 1) {
								return this._commandHandler.executeCommand(token);
							}
							return "Non valid computation input \"" + token + "\", ending calculus";
					}
				}
 			}
		}
		catch (NumberFormatException e) { }
		catch (IllegalArgumentException e) {
			return "error";
		}
		return this.resultForPrinting;
	}
}