/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package textExcel.cellTypes;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import textExcel.SpreadsheetLocation;
import textExcel.TextExcel;

/**
 *
 * @author s-yinb
 */
public abstract class FormulaCell extends RealCell{
    private List<SpreadsheetLocation> dependent;
    private List<SpreadsheetLocation> dependencies;
    private SpreadsheetLocation thisLocation;
    private boolean error = false;
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
    public abstract void calcValue();

    private void applyChange(){
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
    }
}
