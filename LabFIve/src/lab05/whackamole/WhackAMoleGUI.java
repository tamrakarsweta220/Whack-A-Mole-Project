package lab05.whackamole;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;


/**
 * A GUI for the Whack-a-Mole game.
 *
 * @author Grant Braught
 * 
 * @author Sweta Tamrakar
 * @version
 */
public class WhackAMoleGUI extends JFrame implements Observer {
	
	private WhackAMoleModel whackModel;
	
	private JLabel scoreLabel;

	private ImageIcon mole;
	private ImageIcon hole;
	private Random rnd;
	
	private JButton[][] jButtonArray;

	/**
     * Construct a new WhackAMoleGUI for the specified model.
     * 
     * @param myModel the model for this GUI.
     */
    public WhackAMoleGUI(WhackAMoleModel myModel) { 
    	super("Whack a Mole!");
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	whackModel = myModel;
    	whackModel.addObserver(this);
    	
    	// initializing fields
    	mole = new ImageIcon(WhackAMoleGUI.class.getResource("icons/gopher.jpg"));
    	hole = new ImageIcon(WhackAMoleGUI.class.getResource("icons/hole.jpg"));
    	rnd = new Random();
    	
    	// initializing jButtonArray and replacing it by the jButtonArray created below
        jButtonArray = new JButton[whackModel.getRows()][whackModel.getCols()];		
//    	jButtonArray = createJButtonArray();
    	
    	this.mainPanel();
    	
        this.pack();
    }
    
    private JPanel mainPanel() {
    	// creating a mainPanel to add individual row panels in Y axis
    	// each individual row panels require a row number(i) to call elements from the jButtonArray
        JPanel mainPanel = new JPanel();
        this.add(mainPanel);
        
        scoreLabel = new JLabel("Score: "+whackModel.getScore());
        mainPanel.add(scoreLabel);
        
        JPanel radioPanel = this.createRadioButtons(3);
        mainPanel.add(radioPanel);
        
        mainPanel.add(Box.createVerticalGlue()); 
        
        for(int i =0; i<whackModel.getRows(); i++) {
        	mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        	mainPanel.add(getAMoleRow(i));
        }
        
        mainPanel.add(Box.createVerticalGlue());
        
        return mainPanel;
    }
    
    
    private JPanel getAMoleRow(int row) {
    	JPanel panel= new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        
        int moleRow = whackModel.getMoleRow();
    	int moleCol = whackModel.getMoleCol();
    	
    	JButton newButton = null;
        for(int j=0; j <whackModel.getCols(); j++) {
        	if(row==moleRow && j==moleCol) {
        		newButton = new JButton(mole);
        	}
        	else {
        		newButton = new JButton(hole);
        	}
        	panel.add(newButton);
        	
        	jButtonArray[row][j] = newButton;
        	
        	ButtonListener newBList = new ButtonListener(row,j);
        	newButton.addActionListener(newBList);
        }
        
        return panel;
    }
    
    private JPanel createRadioButtons(int level) {
    	JPanel radioButtonPanel= new JPanel();
    	radioButtonPanel.setLayout(new BoxLayout(radioButtonPanel, BoxLayout.X_AXIS));
    	
    	JRadioButton newRadioButton = null;
    	for (int i=0; i<level; i++) {
    		String levelNumber = Integer.toString(i);
    		String levelNum = "Level" + levelNumber;
    		newRadioButton = new JRadioButton(levelNum, false);
    		radioButtonPanel.add(newRadioButton);
    	}
    	return radioButtonPanel;
    }
    
    private class ButtonListener implements ActionListener {
    	private int row;
    	private int col;
    	
    	public ButtonListener(int row, int col) {
    		this.row = row;
    		this.col = col;
    	}
    	
    	public void actionPerformed(ActionEvent e) {
    		System.out.println("Button (" + row + "," + col + ") is clicked!");
    		whackModel.whack(row, col);
    	}
    }
    
    
    /**
     * Update the GUI to reflect the state of the model.  This method
     * repaints all of the buttons with a hole and then repaints the button
     * with the mole on it.
     */
    public void update(Observable o, Object arg) { 
    	System.out.println("entered here");
    	for(int i=0; i<whackModel.getRows(); i++) {
    		for(int j=0; j<whackModel.getCols(); j++) {
    			jButtonArray[i][j].setIcon(hole);
    		}
    	}
    	jButtonArray[whackModel.getMoleRow()][whackModel.getMoleCol()].setIcon(mole);
    	//JButton moleButton = jButtonArray[whackModel.getMoleRow()][whackModel.getMoleCol()];
    	//moleButton.setIcon(mole);
    	scoreLabel.setText("Score: " + whackModel.getScore());
    }
    
    /**
     * Run the WhackAMole game.
     * 
     * @param args none
     */
    public static void main(String[] args) {
        WhackAMoleModel wamm = new WhackAMoleModel();
        WhackAMoleGUI gui = new WhackAMoleGUI(wamm);
        gui.setVisible(true);
    }
}
