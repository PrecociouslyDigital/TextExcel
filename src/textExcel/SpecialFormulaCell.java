
package textExcel;

import textExcel.CellRange;
import textExcel.NotACellException;
import textExcel.SpreadsheetLocation;
import textExcel.TextExcel;

/*This program is unsafe. So we added a safety pig.
| _._ _..._ .-',     _.._())
|'-.      '  /-._.-'    ',/
|   )         \            '.
|  / _    _    |             \
| |  a    a    /              |
| \   .-.                     ;    
|  '-('' ).-'       ,'       ;
|     '-;           |      .'
|        \           \    /
|        | 7  .__  _.-\   \
|        | |  |  `/  /  /
|       /,_|  |   /,_/   /
|          /,_/      '-'

 */
public class SpecialFormulaCell extends RealCell{
    //private List<SpreadsheetLocation> dependencies;
    //private SpreadsheetLocation thisLocation;
    private boolean error;
    //private CellRange range;
    private FormulaType type;
    private int xGreater;
    private int yGreater;
    private int xLesser;
    private int yLesser;
    private enum FormulaType{
        AVG,
        SUM,
    }
    private FormulaType parseFormula(String given){
        given = given.toUpperCase();
        switch(given){
            case "AVG":
                return FormulaType.AVG;
            case "SUM":
                return FormulaType.SUM;
            default:
            	return null;
        }
    }

    /**
     *
     * @param toBeParsed
     * @throws NotACellException
     */
    public SpecialFormulaCell(String toBeParsed) throws NotACellException {
        String[] parts = toBeParsed.split(" ");
        error = false;
        type = parseFormula(parts[0]);
        toBeParsed = parts[1];
        SpreadsheetLocation end = new SpreadsheetLocation(toBeParsed.split("-")[0].trim());
        SpreadsheetLocation start = new SpreadsheetLocation(toBeParsed.split("-")[1].trim());
        yGreater = (end.getCol() > start.getCol()) ? end.getCol() : start.getCol();
        yLesser = (end.getCol() < start.getCol()) ? end.getCol() : start.getCol();
        xGreater = (end.getRow() > start.getRow()) ? end.getRow() : start.getRow();
        xLesser = (end.getRow() < start.getRow()) ? end.getRow() : start.getRow();
        calcValue();
        //super(toBeParsed);
    }

     
    @Override
    public String abbreviatedCellText() {
        if(!error)
            return String.format("%7f...", value);
        else
            return "#ERROR";
    }
    @Override
    public String fullCellText() {
        if(!error)
            return Double.toString(value);
        else
            return "#ERROR";
    }
    public void calcValue(){// I'm sorry.
    	double sum = 0;
    	error = false;
        /*if(type == FormulaType.ERR){
            error = true;
            return;
        }else{
            error = false;
        }
        
        while(range.hasNext()){
            sum += ((RealCell) (TextExcel.sheet.getCell(range.getNext()))).getValue();
        }
        if(type == FormulaType.AVG)
        	sum / range.ge*/
    	for(int i = xLesser; i < xGreater; i++){
    		for(int j = yLesser; j < yGreater; j++){
        		Cell cell = TextExcel.sheet.getCell(new SpreadsheetLocation(i,j));
        		if(cell instanceof RealCell){
        			sum += ((RealCell) cell).getValue();
        		}else{
        			error = true;
        			return;
        		}
        	}
    	}
    	if(type == FormulaType.AVG)
        	sum /= (xGreater - xLesser + 1) * (yGreater- yLesser + 1);
    	value = sum;
    }
    /*private void applyChange(){
        for(SpreadsheetLocation cell : dependencies){
            Cell celled = TextExcel.sheet.getCell(cell);
            if(celled instanceof FormulaCell){
                FormulaCell k = (FormulaCell) celled;
                k.calcValue();
                error = false;
            }else{
                error = true;
            }
        }
    }
    public void destroy(){
        for(int i = 0; i < dependent.size(); i++){
            Cell celled = TextExcel.sheet.getCell(dependent.get(i));
            if(celled instanceof RealCell){
                RealCell k = (RealCell) celled;
                k.removeDependency(thisLocation);
            }
        }
    }*/
}