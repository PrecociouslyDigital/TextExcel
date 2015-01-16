package textExcel;

import java.util.List;

public interface Value {
	public double getValue(List<SpreadsheetLocation> list) throws NotARealCellException,RecursiveLinkException;
}