package textExcel;
// Update this file with your own code.
import java.util.logging.Level;
import java.util.logging.Logger;
import textExcel.cellTypes.Cell;
import textExcel.cellTypes.DateCell;
import textExcel.cellTypes.EmptyCell;
import textExcel.cellTypes.NotADateException;
import textExcel.cellTypes.RealCell;
import textExcel.cellTypes.TextCell;

public class Spreadsheet implements Grid {

    Cell[][] data;
    public static final int x = 12;
    public static final int y = 20;
    public static final String[] errorMessages = {
        "Sorry, I don't understand.",
        "I don't think you typed anything valid.",
        "Sorry, didn't get that.",
        "Okay, please type in something valid.",
        "Please, please, please type in something valid.",
        "TYPE IN SOMETHING VALID!",
        "THAT'S NOT VALID!",
        "NOT! VALID!",
        "ARRRRRRRRRRRGGGGGGHHHHHH!",
        "Why do I have to get stuck with the worst user ever?",
        "Sighs.",
        "Alright. I'm outta here. Dave, can you cover for me?",
        "Sure.",
        "Hi! I'm Dave!",
    };
    public static byte counter = -1;

    public Spreadsheet() {
        data = new Cell[y][x];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                data[i][j] = new EmptyCell();
            }
        }
    }

    public void clear() {
        data = new Cell[y][x];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                data[i][j] = new EmptyCell();
            }
        }
    }

    public Spreadsheet(int x, int y) {
        data = new Cell[y][x];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                data[i][j] = new EmptyCell();
            }
        }
    }

    @Override
    public String processCommand(String command) {
        if(command.equalsIgnoreCase("")){
            return "";
        }
        String[] parts = command.split(" ", 3);
        if (parts.length == 1) {
            if (command.equalsIgnoreCase("clear")) {
                clear();
                resetError();
                return getGridText();
            } else if (command.equalsIgnoreCase("test")) {
                setCell(new SpreadsheetLocation(2, 2), new TextCell("\'Hello\""));
                resetError();
                return getGridText();
            } else {
                try {
                String thing = this.getCell(new SpreadsheetLocation(command)).fullCellText();
                resetError();
                return thing;
                } catch (NotACellException ex) {
                    return errorMessage();
                }
            }
        }else if(parts.length == 2){
            if(parts[0].equalsIgnoreCase("clear"))
                try {
                    setCell(new SpreadsheetLocation(parts[2]), new EmptyCell());
            } catch (NotACellException ex) {
                    errorMessage();
            }
            else
                errorMessage();
        }
        if (parts[1].equalsIgnoreCase("=")) {
            try{
            setCell(new SpreadsheetLocation(parts[0]), parseCell(parts[2]));
            resetError();
            return getGridText();
            }catch(NotACellException e){
                return errorMessage();
            }
        }
        return errorMessage();
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
        return data[loc.getCol()-1][loc.getRow()];
    }

    @Override
    public String getGridText() {
        // TODO Auto-generated method stub
        String grid = "  ";
        for (byte i = 0; i < data[0].length; i++) {
            grid += String.format("|%-10c", (char) (i + 'A'));
        }
        grid += "|\n";
        for (int i = 0; i < data.length; i++) {
            grid += String.format("%2d", i + 1) + formatRow(data[i]);
        }
        return grid;
    }

    public String formatCellText(Cell cell) {
        return "|" + cell.abbreviatedCellText();
    }

    public String formatRow(Cell[] cells) {
        String row = "";
        for (Cell cell : cells) {
            row += formatCellText(cell);
        }
        row += "|\n";
        return row;
    }
    public void setCell(SpreadsheetLocation loc, Cell cell) {
        /*Cell toBeDeleted = data[loc.getCol()][loc.getRow()];
         if(toBeDeleted instanceof FormulaCell)
         ((FormulaCell) toBeDeleted).destroy();*/
        data[loc.getCol()-1][loc.getRow()] = cell;
    }
    private Cell parseCell(String toBeParsed) throws NotACellException {
        switch (toBeParsed.charAt(0)) {
            case '"':
                if (toBeParsed.endsWith("\"")) {
                    
                    return new TextCell(toBeParsed);
                } else {
                    throw new NotACellException();
                }
            case '(':
                if (toBeParsed.endsWith(")")) //TODO: lots
                {
                    break;
                }
            default:
                if (toBeParsed.contains("/")) {
                    try {
                        return new DateCell(toBeParsed);
                    } catch (NotADateException e) {
                        throw new NotACellException();
                    }
                } else if (toBeParsed.split(" ").length > 1) {
                    String[] parts = toBeParsed.split(" ");
                } else {
                    try {
                        return new RealCell(toBeParsed);
                    } catch (NumberFormatException e) {
                        throw new NotACellException();
                    }
                }

        }
        return new EmptyCell();
    }
    private String errorMessage(){
        counter++;
        counter %= errorMessages.length;
        return errorMessages[counter];
    }
    private void resetError(){
        counter = -1;
    }
}
