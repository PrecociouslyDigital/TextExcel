package textExcel;
// Update this file with your own code.

public class Spreadsheet implements Grid {
    
    Cell[][] data;
    public static final int x = 20;
    public static final int y = 12;
    public Spreadsheet(){
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
}
