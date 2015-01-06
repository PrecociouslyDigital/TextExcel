package textExcel.cellTypes;

import java.util.List;
import textExcel.SpreadsheetLocation;

/**
This program is unsafe. So we added a safety pig.
* | _._ _..._ .-',     _.._(`))
* |'-.      '  /-._.-'    ',/
* |   )         \            '.
* |  / _    _    |             \
* | |  a    a    /              |
* | \   .-.                     ;
* |  '-('' ).-'       ,'       ;
* |     '-;           |      .'
* |        \           \    /
* |        | 7  .__  _.-\   \
* |        | |  |  ``/  /`  /
* |       /,_|  |   /,_/   /
* |          /,_/      '`-'
* 
 * @author Benjamin
 */
public class RealCell implements Cell {
    public double value;

    public RealCell(String toBeParsed) {
        
        value = Double.parseDouble(toBeParsed);
    }
    

    @Override
    public String abbreviatedCellText() {
        String print = value + "";
        print = print.substring(beginIndex, endIndex);
        return String.format("%-10f", value);
    }
    @Override
    public String fullCellText() {
        return Double.toString(value);
    }
    public double getValue(){
        return value;
    }
}