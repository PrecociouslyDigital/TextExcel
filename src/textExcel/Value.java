package textExcel;

import java.util.Set;

public interface Value {
	public double getValue() throws NotARealCellException;
	public double getValue(SpreadsheetLocation loc) throws NotARealCellException, RecursiveLinkException;
}