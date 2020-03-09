class CommandHandler {
	
	Stack stack;
	
	public CommandHandler(Stack stack) {
		this.stack = stack;
	}
	
	public String executeCommand(String s) {
		switch (s) {
			case "clear":
				this.stack.clear();
				return "clear";
			case "help":
				return "  Please enter an RPN expression or a command:\n"
						+ "  RPN expression involves float numbers and the operation +, -, *, / (e.g.: 1.1 -2 +).\n"
						+ "  A fully evaluated expression will display the result.\n"
						+ "  Incomplete expression are evaluated and the resulting stack is displayed.\n"
						+ "  Type \"clear\" to erase all previous calculations and empty the stack.\n"
						+ "  Type \"help\" to see this help.";
			default:
				return "Unknown command, please type \"help\" to see the existing commands";
		}
	}
}