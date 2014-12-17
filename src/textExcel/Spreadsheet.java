package textExcel;
// Update this file with your own code.

import textExcel.cellTypes.Cell;
import textExcel.cellTypes.EmptyCell;
import textExcel.cellTypes.FormulaCell;
import textExcel.cellTypes.TextCell;


public class Spreadsheet implements Grid {
    
    Cell[][] data;
    public static final int x = 12;
    public static final int y = 20;
    public Spreadsheet(){
        data = new Cell[y][x];
        for(int i = 0; i < y; i++)
            for(int j = 0; j < x; j++)
                data[i][j] = new EmptyCell();
    }
    public void clear(){
        data = new Cell[y][x];
        for(int i = 0; i < y; i++)
            for(int j = 0; j < x; j++)
                data[i][j] = new EmptyCell();
    }
    public Spreadsheet(int x, int y){
        data = new Cell[y][x];
        for(int i = 0; i < y; i++)
            for(int j = 0; j < x; j++)
                data[i][j] = new EmptyCell();
    }
    @Override
    public String processCommand(String command) {
        String[] parts = command.split(" ", 3);
        switch(parts[2].charAt(0)){
            case '"':
                setCell(new SpreadsheetLocation(parts[0]), new TextCell(parts[2].substring(1,parts[2].length()-2)));
        }
        return "";
    }
    @Override
    public int getRows() {
        // TODO Auto-generated method stub
        return data.length;
    }
    @Override
    public int getCols() {
        // TODO Auto-generated method stub
        return data[0].length;
    }
    @Override
    public Cell getCell(Location loc) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public String getGridText() {
        // TODO Auto-generated method stub
        String grid = "  ";
        for(byte i = 0; i < data[0].length; i++)
            grid += String.format("|%-10c", (char)(i + 'A'));
        grid += "|\n";
        for(int i = 0; i < data.length; i++)
            grid += String.format("%2d", i+1) + formatRow(data[i]);
        return grid;
    }
    public String formatCellText(Cell cell){
        return "|"+cell.abbreviatedCellText();
    }
    public String formatRow(Cell[] cells){
        String row = "";
        for(Cell cell : cells)
            row += formatCellText(cell);
        row += "|\n";
        return row;
    }
    public void setCell(SpreadsheetLocation loc, Cell cell){
        Cell toBeDeleted = data[loc.getCol()][loc.getRow()];
        if(toBeDeleted instanceof FormulaCell)
            ((FormulaCell) toBeDeleted).destroy();
        data[loc.getCol()][loc.getRow()] = cell;
    }
}