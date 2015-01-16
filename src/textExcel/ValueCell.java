package textExcel;

public class ValueCell extends RealCell {
	public ValueCell(String toBeParsed) {
        value = Double.parseDouble(toBeParsed);
    }
}
