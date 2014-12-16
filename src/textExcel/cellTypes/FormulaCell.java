/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package textExcel.cellTypes;

import java.util.List;

/**
 *
 * @author s-yinb
 */
public abstract class FormulaCell extends RealCell{
    private List<RealCell> dependent;
    private List<FormulaCell> dependencies;
    public abstract void calcValue();
    public List<FormulaCell> getAffected(){
        return dependencies;
    }
    public void setDependencies(List<FormulaCell> cells){
        dependencies = cells;
    }
    public void addDependency(FormulaCell cell){
        dependencies.add(cell);
    }
    private void applyChange(){
        for(FormulaCell cell : dependencies)
            cell.calcValue();
    }
    
}
