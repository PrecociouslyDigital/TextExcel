/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textExcel;

/**
 *
 * @author s-yinb
 */
public class SpreadsheetLocation implements Location {

    private int row;
    private int col;
    
    public SpreadsheetLocation(int row, int col) {
        this.row = row;
        this.col = col;
    }
    public SpreadsheetLocation(int row, char col) {
        col = Character.toLowerCase(col);
        this.row = row;
        this.col = col - 'a';
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getCol() {
        return col;
    }

    public void translate(int rows, int cols) {
        row += rows;
        col += cols;
    }
    public void set(int row, int col){
        this.row = row;
        this.col = col;
    }

}
