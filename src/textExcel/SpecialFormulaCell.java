package textExcel;

public class SpecialFormulaCell extends FormulaCell {
	private SpreadsheetLocation start;
	private SpreadsheetLocation end;
	private boolean avg;
	public SpecialFormulaCell(String thing) throws NotACellException{
		String[] things = thing.trim().split(" ");
		avg = things[0].equalsIgnoreCase("AVG");
		SpreadsheetLocation first = new SpreadsheetLocation(things[1]);
		SpreadsheetLocation seconds = new SpreadsheetLocation(things[3]);
		start = new SpreadsheetLocation((first.getRow() < seconds.getRow()) ? first.getRow() : seconds.getRow(), (first.getCol() < seconds.getCol()) ? first.getCol() : seconds.getCol());
		end = new SpreadsheetLocation((first.getRow() > seconds.getRow()) ? first.getRow() : seconds.getRow(), (first.getCol() > seconds.getCol()) ? first.getCol() : seconds.getCol());
	}
	public double getValue(){
		double total = 0;
		for(int row = start.getRow(); row <= end.getRow(); row++){
			for(int col = start.getCol(); col <= end.getCol(); col++){
				Cell cell = TextExcel.sheet.getCell(new SpreadsheetLocation(row,col));
				if(cell instanceof RealCell)
					total += ((RealCell) cell).getValue();
				else{
					error = true;
					return 0;
				}
			}
		}
		return total;
	}
}
