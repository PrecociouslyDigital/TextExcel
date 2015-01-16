package textExcel;

import java.util.List;

public class LinkedValue implements Value {
	private SpreadsheetLocation link;
	public LinkedValue(String parse) throws NotACellException{
			link = new SpreadsheetLocation(parse);
	}
	public LinkedValue(SpreadsheetLocation loc){
		link = loc;
	}
	@Override
	public double getValue(List<SpreadsheetLocation> list) throws NotARealCellException,RecursiveLinkException{
		// TODO Auto-generated method stub
		if(list.contains(link))
			throw new RecursiveLinkException();
		list.add(link);
		Cell cell = TextExcel.sheet.getCell(link);
		if(cell instanceof RealCell){
			if(cell instanceof FormulaCell){
				return ((FormulaCell) cell).getDoubleRecur(list);
			}else{
				return ((RealCell) cell).getValue();
			}
		}else{
			throw new NotARealCellException();
		}
	}
	

}
