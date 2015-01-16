package textExcel;

import java.util.List;

public abstract class FormulaCell extends RealCell {
	public abstract double getDoubleRecur(List<SpreadsheetLocation> forbidden);
}