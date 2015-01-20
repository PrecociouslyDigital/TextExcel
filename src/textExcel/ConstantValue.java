package textExcel;

import java.util.List;
import java.util.Set;

public class ConstantValue implements Value {
	private double value;
	public ConstantValue(double value){
		this.value = value;
	}
	public ConstantValue(String value) throws NumberFormatException{
		this.value = Double.parseDouble(value);
	}
	@Override
	public double getValue() throws NotARealCellException {
		// TODO Auto-generated method stub
		return value;
	}

}
