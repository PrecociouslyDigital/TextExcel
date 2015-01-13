package textExcel;
import java.util.Iterator;
/**
 *
 * @author s-yinb
 */
public class CellRange {

    private final int xGreater;
    private final int yGreater;
    private final int xLesser;
    private final int yLesser;
    private final int xDist; 
    private final int yDist;
    private int counter = 0;

    public CellRange(SpreadsheetLocation start, SpreadsheetLocation end) {

        /*if (start.getCol() > end.getCol()) {
            yGreater = start.getCol();
            yLesser = end.getCol();
        } else {
            yGreater = end.getCol();
            yLesser = start.getCol();
        }
        if (start.getRow() > end.getRow()) {
            xGreater = start.getRow();
            xLesser = end.getRow();
        } else {
            xGreater = end.getRow();
            xLesser = start.getRow();
        }*/
        yGreater = (end.getCol() > start.getCol()) ? end.getCol() : start.getCol();
        yLesser = (end.getCol() < start.getCol()) ? end.getCol() : start.getCol();
        xGreater = (end.getRow() > start.getRow()) ? end.getRow() : start.getRow();
        xLesser = (end.getRow() < start.getRow()) ? end.getRow() : start.getRow();
        xDist = xGreater - xLesser;
        yDist = yGreater - yLesser;
        
    }

    public SpreadsheetLocation getNext() {
        if(hasNext()){
            return new SpreadsheetLocation(counter % xDist + xLesser, counter++ / xDist + yLesser);
        }
        else
            return null;
    }
    public boolean hasNext(){
        return counter <= (xDist+1) * (yDist+1);
    }
    public void rewind(){
        counter = 0;
    }
    public boolean contains(SpreadsheetLocation loc){
        return loc.getCol() > yLesser && loc.getCol() < yGreater && loc.getRow() > xLesser && loc.getRow() < xGreater;
    }
    
}