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
        this.row = row - 1;
        this.col = col - 'a';
    }
    public SpreadsheetLocation(String s) throws NotACellException {
        s = s.toLowerCase();
        try{
        this.col = Byte.parseByte(s.substring(1, s.length())) - 1;
        }catch(StringIndexOutOfBoundsException e){
            NotACellException up = new NotACellException();
            throw up;
        }catch(NumberFormatException e){
            NotACellException up = new NotACellException();
            throw up;//I'm sorry.
        }
        this.row = s.charAt(0) - 'a';
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