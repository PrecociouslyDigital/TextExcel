/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textExcel;

import java.util.Iterator;

/**
 *
 * @author s-yinb
 */
public class CellRange {

    private int xGreater;
    private int yGreater;
    private int xLesser;
    private int yLesser;
    private int xDist;
    private int yDist;
    private int counter = 0;

    public CellRange(SpreadsheetLocation start, SpreadsheetLocation end) {

        if (start.getCol() > end.getCol()) {
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
        }
        xDist = xGreater - xLesser;
        yDist = yGreater - yLesser;
    }

    public SpreadsheetLocation getNext() {
        if(hasNext())
            return new SpreadsheetLocation(counter % xDist, counter / xDist);
        else
            return null;
    }
    public boolean hasNext(){
        return counter <= (xDist+1) * (yDist+1);
    }
}
