package textExcel;

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
public abstract class RealCell implements Cell {
    public double value;
    protected boolean error;
    public RealCell(String toBeParsed) {
        value = Double.parseDouble(toBeParsed);
    }
    public RealCell(){
    	value = 0;
    }
    @Override
    public String abbreviatedCellText() {
        String toSender = error?"#ERROR             ":getValue() + "          ";
        return toSender.substring(0, 10);
        //return String.format("%-10f", value);
    }
    @Override
    public String fullCellText() {
        return error?"#ERROR":getValue() + "";
    }
    public double getValue(){
        return value;
    }
}