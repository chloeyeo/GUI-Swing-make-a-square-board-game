package game;

import javax.swing.JButton;
import java.awt.Color;

@SuppressWarnings("serial")
public class GameButton extends JButton {
	
    private String symbol = null;
    
    public GameButton(String s) {
    	super(s);
    }
    
    public GameButton() {
    	super();
    }
    
    public void setSymbol(String symbol) {
    	this.symbol = symbol;
        this.setText(symbol);
		if (symbol.equals("1")) {
			this.setBackground(Color.red);
		} else if (symbol.equals("2")) {
			this.setBackground(Color.blue);
		}
		this.setEnabled(false);
    }
    
    public String getSymbol() {
    	return this.symbol;
    }
    
    public void reset() {
    	symbol = null;
    	this.setText("");
    	this.setBackground(null); // changes it back to default background colour.
    	this.setEnabled(true);
    }
}
