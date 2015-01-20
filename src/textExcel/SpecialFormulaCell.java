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
		try{
		double total = 0;
		int row;
		int col = 0;
		for(row = start.getRow(); row <= end.getRow(); row++){
			for(col = start.getCol(); col <= end.getCol(); col++){
				Cell cell = TextExcel.sheet.getCell(new SpreadsheetLocation(row,col));
				if(cell instanceof RealCell)
					if(cell instanceof FormulaCell){
						FormulaCell thing = ((FormulaCell) cell);
						if(thing.error){
							error = true;
							return 0;
						}else{
							total += thing.getValue(new SpreadsheetLocation(row, col));
						}
					}else
						total += ((RealCell) cell).getValue();
				else{
					error = true;
					return 0;
				}
			}
		}
		return avg ? total / (row * col) : total;
		}catch(Throwable e){
			error = true;
			return 0;
		}
	}
	@Override
	public double getValue(SpreadsheetLocation loc) {
		double total = 0;
		for(int row = start.getRow(); row <= end.getRow(); row++){
			for(int col = start.getCol(); col <= end.getCol(); col++){
				Cell cell = TextExcel.sheet.getCell(new SpreadsheetLocation(row,col));
				if(cell instanceof RealCell)
					if(cell instanceof FormulaCell){
						FormulaCell thing = ((FormulaCell) cell);
						if(thing.error){
							error = true;
							return 0;
						}else{
							return thing.getValue(new SpreadsheetLocation(row, col));
						}
					}
				else{
					error = true;
					return 0;
				}
			}
		}
		return total;
	}
}
