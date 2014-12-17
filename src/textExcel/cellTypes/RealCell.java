package textExcel.cellTypes;

import java.util.List;
import textExcel.SpreadsheetLocation;

/**
 *
 * @author Benjamin
 */
public class RealCell implements Cell {
    public double value;
    private List<SpreadsheetLocation> dependencies;

    @Override
    public String abbreviatedCellText() {
        return String.format("%7f...", value);
    }
    @Override
    public String fullCellText() {
        return Double.toString(value);
    }
    public double getValue(){
        return value;
    }
    public List<SpreadsheetLocation> getAffected(){
        return dependencies;
    }
    public void setDependencies(List<SpreadsheetLocation> cells){
        dependencies = cells;
    }
    public void addDependency(SpreadsheetLocation cell){
        dependencies.add(cell);
    }
    public void removeDependency(SpreadsheetLocation loc){
        dependencies.remove(loc);
    }
}