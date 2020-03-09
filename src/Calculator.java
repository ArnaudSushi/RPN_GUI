import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
//import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class Calculator extends JFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private JPanel stackPanel = new JPanel();
	private JLabel stackLabel = new JLabel("Stack");
	//private JButton clearStackButton = new JButton("Clear stack");
	
	private JPanel mainPanel = new JPanel();
	private JScrollPane scroll;
	private JTextArea historyArea = new JTextArea();
	private JTextField operationField = new JTextField();
	
	private int histRank = -1;
	private List<String> history = new ArrayList<String>();
	private String originalComm = "";
	
	private Stack stack;
	private SyntaxChecker _syntaxChecker;
	
	public Calculator() {
		this.stack = new Stack();
		this._syntaxChecker = new SyntaxChecker(this.stack);
		
		this.getContentPane().setLayout(new BorderLayout());
		
		this.stackPanel.setLayout(new BoxLayout(this.stackPanel, BoxLayout.PAGE_AXIS));
		this.stackPanel.add(this.stackLabel);
		this.stackPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		this.getContentPane().add(this.stackPanel, BorderLayout.WEST);
		
		this.historyArea.setFocusable(false);
		this.historyArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		this.scroll = new JScrollPane(this.historyArea);
		
		this.operationField.addActionListener(this);
		this.operationField.setActionCommand("calcul");
		this.operationField.addKeyListener(this);
		
		this.mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.mainPanel.setLayout(new BorderLayout());
		this.mainPanel.add(this.scroll, BorderLayout.CENTER);
		this.mainPanel.add(this.operationField, BorderLayout.SOUTH);
		
		this.getContentPane().add(this.mainPanel);
		
		this.setSize(new Dimension(500, 500));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void write(String s) {
		this.historyArea.append(s + "\n");
	}
	
	private void refreshStackPanel() {
		this.stackPanel.removeAll();
		this.stackPanel.add(this.stackLabel);
		this.stack.getRegisters().forEach(reg -> stackPanel.add(new JLabel(String.valueOf(reg))));
		this.getContentPane().update(this.getContentPane().getGraphics());
		this.setVisible(true);
	}
	
	private void historyUp() {
		if (this.originalComm == "") this.originalComm = this.operationField.getText();
		if (this.history.size() == 0) return;
		this.operationField.setText(this.history.get(this.histRank));
		if (this.histRank != 0) {
			this.histRank --;
		}
	}
	
	private void historyDown() {
		if (this.history.size() == 0) return;
		this.operationField.setText(this.history.get(this.histRank));
		if (this.histRank != this.history.size() - 1) {
			this.histRank ++;
		} else {
			this.operationField.setText(this.originalComm);
			this.originalComm = "";
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "calcul":
			this.history.add(this.operationField.getText());
			this.histRank = this.history.size() - 1;
			System.out.println(this.history + "\n" + this.histRank);
			
			String res = this._syntaxChecker.checkInputs(this.operationField.getText());
			this.operationField.setText("");
			this.write(res);
			break;
		default:
			return;
		}
		this.refreshStackPanel();
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case 38:
			this.historyUp();
			break;
		case 40:
			this.historyDown();
			break;
		default:
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}
}