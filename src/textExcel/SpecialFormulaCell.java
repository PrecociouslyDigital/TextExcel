

package textExcel;

import textExcel.SpreadsheetLocation;

/**
 *
 * @author s-yinb
 */
public class SpecialFormulaCell extends FormulaCell {
    private boolean average;
    private CellRange range;
    public SpecialFormulaCell(boolean average, SpreadsheetLocation start, SpreadsheetLocation end){
    	this.average = average;
    	range = new CellRange(start, end);
    }
	public double calcValue(){
		return calcValue();
	}
}
