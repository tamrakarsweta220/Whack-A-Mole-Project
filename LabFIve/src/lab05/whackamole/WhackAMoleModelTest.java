package lab05.whackamole;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WhackAMoleModelTest {
	private WhackAMoleModel wM;

	@BeforeEach
	void setUp() throws Exception {
		wM = new WhackAMoleModel();
	}

	@Test
	void testConstructor() {
		assertEquals("wrong no.of rows",4,wM.getRows());
		assertEquals("wrong no.of cols",4,wM.getCols());
		assertEquals("wrong score",0,wM.getScore());
	}
	
	@Test
	void testWhack() {
		int initRow = wM.getMoleRow();
		int initCol = wM.getMoleCol();
		boolean changed=false;
		wM.whack(initRow,initCol);
		int newRow = wM.getMoleRow();
		int newCol = wM.getMoleCol();
		if (newRow != initRow || newCol != initCol) {
			changed=true;
		}
		assertTrue("mole is not changed", changed);
	}
}
