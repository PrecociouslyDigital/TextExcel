package textExcel;
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
        /*month %= 12;
        if(month == 0)
            month = 12;
        day %= 31;
        if(day == 0)
            day = 31;
        if(year < 100){
            if(year < 16){
                year += 2000;
            }else{
                year += 1900;
            }
        }*/
    }
    @Override
    public String abbreviatedCellText() {
        String toSender = String.format("%02d/%02d/%02d", month, day, year);
        while(toSender.length() < 9){
            toSender += " ";
        }
        return toSender;
    }
    @Override
    public String fullCellText() {
        return String.format("%02d/%02d/%02d", month, day, year);
    }
}