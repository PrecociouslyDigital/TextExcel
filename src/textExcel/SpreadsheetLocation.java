package textExcel;

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
    public SpreadsheetLocation(String s) {
        s = s.toLowerCase();
        this.row = Byte.parseByte(s.substring(1));
        this.col = s.charAt(2) - 'a';
    }
    @Override
    public int getRow() {
        return row;
        
    }
    @Override
    public int getCol() {
        return col;
        
    }
    /*public void translate(int rows, int cols) {
        row += rows;
        col += cols;
    }
    public void set(int row, int col){
        this.row = row;
        this.col = col;
    }*/
}