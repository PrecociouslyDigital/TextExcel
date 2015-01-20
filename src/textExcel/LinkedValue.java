package textExcel;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class LinkedValue implements Value {
	private SpreadsheetLocation link;
	public LinkedValue(String parse) throws NotACellException{
		link = new SpreadsheetLocation(parse);
	}
	public LinkedValue(SpreadsheetLocation loc){
		link = loc;
	}
	@Override
	public double getValue() throws NotARealCellException {
		Cell cell = new EmptyCell();
		Grid sheet = TextExcel.sheet;
		cell = TextExcel.sheet.getCell(link);
		if(cell instanceof RealCell){
			if(cell instanceof FormulaCell){
				FormulaCell thing = ((FormulaCell) cell);
				if(thing.error){
					throw new NotARealCellException();
				}else{
					return thing.getValue(link);
				}
			}
			return ((RealCell) cell).getValue();
		}else{
			throw new NotARealCellException();
		}
	}
	@Override
	public double getValue(SpreadsheetLocation loc) throws NotARealCellException, RecursiveLinkException {
		if(loc.getCol() ==  link.getCol() && loc.getRow() ==  link.getRow())
			throw new RecursiveLinkException();
		Cell cell = new EmptyCell();
		Grid sheet = TextExcel.sheet;
		cell = TextExcel.sheet.getCell(link);
		if(cell instanceof RealCell){
			if(cell instanceof FormulaCell){
				FormulaCell thing = ((FormulaCell) cell);
				if(thing.error){
					throw new NotARealCellException();
				}else{
					return thing.getValue(loc);
				}
			}
			return ((RealCell) cell).getValue();
		}else{
			throw new NotARealCellException();
		}
	}
	
}
