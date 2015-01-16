package textExcel;

import java.util.List;

public class ConstantValue implements Value {
	private double value;
	public ConstantValue(double value){
		this.value = value;
	}
	public ConstantValue(String value) throws NumberFormatException{
		this.value = Double.parseDouble(value);
	}
	@Override
	public double getValue(List<SpreadsheetLocation> list) {
		// TODO Auto-generated method stub
		return value;
	}

}
