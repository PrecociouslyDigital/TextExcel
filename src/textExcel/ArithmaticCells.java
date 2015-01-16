

package textExcel;

import textExcel.SpreadsheetLocation;

/**
 *
 * @author s-yinb
 */
public class ArithmaticCells extends SpecialFormulaCell {
    
	
	public ArithmaticCells(String toBeParsed) throws NotACellException {
		super(toBeParsed);
		// TODO Auto-generated constructor stub
	}
	class Expression{
		private multSection[] secs;
		private boolean[] operators;
		public Expression(String toBeParsed){
			
		}
		class multSection{
			
		}
	}
	enum Operators{
		plus,
		minus,
		times,
		divides
	}
}
