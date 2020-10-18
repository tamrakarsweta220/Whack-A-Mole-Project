package lab05.whackamole;

import java.util.*;

/**
 * A model (in the MVC sense) for a whack-a-mole game. The model keeps track of
 * the location of the mole. It has a method for whacking a location and handles
 * scoring points and moving the mole if it was there and deducting points if
 * the mole was not there.
 * 
 * @author Grant Braught
 * 
 * @author
 * @version
 */
public class WhackAMoleModel extends Observable{

	private int score;
	private int rows;
	private int cols;
	private int moleRow;
	private int moleCol;
	private Random rnd;
    /**
     * Construct a new 4x4 Whack-a-Mole board. The mole is initially at a
     * randomly selected location. The score is initially 0.
     */
    public WhackAMoleModel() {
    	rnd =new Random();
    	moleRow = rnd.nextInt(4);
    	moleCol = rnd.nextInt(4);
    	rows = 4;
    	cols = 4;
    	score = 0;
    }
    
    /**
     * Get the number of rows on the board.
     * 
     * @return the number of rows.
     */
    public int getRows() {
        return rows;
    }

    /**
     * Get the number of columns on the board.
     * 
     * @return the number of columns.
     */
    public int getCols() {
        return cols;
    }
    
    /**
     * Get the row containing the mole.
     * 
     * @return the row containing the mole.
     */
    public int getMoleRow() {
        return moleRow;
    }

    /**
     * Get the column containing the mole.
     * 
     * @return the column containing the mole.
     */
    public int getMoleCol() {
        return moleCol;
    }

    /**
     * Get the current score.
     * 
     * @return the score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Whack the hole at the specified row and column. When a hole is whacked
     * the score is increased if there was a mole at that location and decreased
     * if there was not. Either way the location of the mole is changed and any
     * observers are notified of the change.
     * 
     * @param row the row to whack
     * @param col the column to whack
     */
    public void whack(int row, int col) {
    	if(moleRow == row && moleCol == col) {
    		score=score+10;
    	}
    	else {
    		score=score-5;
    	}
    	int tempX = moleRow;
    	int tempY = moleCol;
    	while(tempX == moleRow && tempY == moleCol) {
    		moleRow = rnd.nextInt(4);
    		moleCol = rnd.nextInt(4);
    	}
    	setChanged();
        notifyObservers();
    }
}
