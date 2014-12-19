package textExcel.cellTypes;

import java.util.List;
import textExcel.SpreadsheetLocation;

/**
 *
 * @author Benjamin
 */
public class RealCell implements Cell {
    public double value;
    

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
}