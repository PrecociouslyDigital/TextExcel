/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package textExcel.cellTypes;

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
public class FormulaCell extends RealCell{
    //private List<SpreadsheetLocation> dependencies;
    //private SpreadsheetLocation thisLocation;
    private boolean error = false;
    private CellRange range;
    private FormulaType type;
    private enum FormulaType{
        AVG,
        SUM,
        ERR
    }
    private FormulaType parseFormula(String given){
        given = given.toUpperCase();
        switch(given){
            case "AVG":
                return FormulaType.AVG;
            case "SUM":
                return FormulaType.SUM;
            default:
                return FormulaType.ERR;
        }
    }

    /**
     *
     * @param toBeParsed
     * @throws NotACellException
     */
    public FormulaCell(String toBeParsed) throws NotACellException {
        String[] parts = toBeParsed.split(" ");
        type = parseFormula(parts[0]);
        toBeParsed = parts[1];
        range = new CellRange(new SpreadsheetLocation(toBeParsed.split("-")[0].trim()),new SpreadsheetLocation(toBeParsed.split("-")[1].trim()));
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
        if(type == FormulaType.ERR){
            error = true;
            return;
        }else{
            error = false;
        }
        double sum = 0;
        while(range.hasNext()){
            sum += ((RealCell) (TextExcel.sheet.getCell(range.getNext()))).getValue();
        }
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