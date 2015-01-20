package textExcel;

public abstract class FormulaCell extends RealCell {
	protected String thing;
	public FormulaCell(String toBeParsed) {
		super(toBeParsed);
		// TODO Auto-generated constructor stub
	}
	public FormulaCell(){
		super("0");
	}
	public abstract double getValue(SpreadsheetLocation loc);
	public String fullCellText(){
		return "(" + thing + ")";
	}
	public String abbreviatedCellText(){
		if(error)
			return "#ERROR";
		else
			return getValue() + "";
	}
}