package textExcel;

public class TempFormulaCell extends RealCell{
	String text;
    public TempFormulaCell(String text){
    	this.text = text.substring(1,text.length()-1);
    }
    @Override
    public String abbreviatedCellText() {
        return (text + "            ").substring(0,10);
    }

    @Override
    public String fullCellText() {
        return "(" + text + ")";
    }
}
