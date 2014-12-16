package textExcel.cellTypes;
/**
 *
 * @author Benjamin
 */
public class DateCell implements Cell{
    private byte day;
    private byte month;
    private int year;
    public DateCell(String parsed) throws NotADateException{
        String[] pieces = parsed.split("/", 3);
        try{
            month = Byte.parseByte(pieces[0]);
            day = Byte.parseByte(pieces[1]);
            year = Integer.parseInt(pieces[2]);
        }catch(NumberFormatException e){
            throw new NotADateException();
        }
    }
    @Override
    public String abbreviatedCellText() {
        return month + "/" + day + "/" + year;
    }

    @Override
    public String fullCellText() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
