

package textExcel;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import textExcel.SpreadsheetLocation;

/**
 *
 * @author s-yinb
 */
public class ArithmaticCells extends FormulaCell {
	private Value[] vals;
	enum Operators{PLUS,MINUS,TIMES,DIVIDES}
	private Operators[] ops;

	public ArithmaticCells(String toBeParsed) throws NotACellException, NotAnOperatorException{
		String[] things = toBeParsed.trim().split(" ");
		vals = new Value[things.length/2 + 1];
		ops = new Operators[things.length/2];
		thing = toBeParsed;
		byte i = 0;
		while(i < things.length){
			
			if(i%2 == 0){
				try{
				vals[i/2] = new ConstantValue(things[i]);
				}catch(NumberFormatException e){
					vals[i/2] = new LinkedValue(things[i]);
				}
			}else{
				switch(things[i]){
					case "+":
						ops[i/2] = Operators.PLUS;
						break;
					case "-":
						ops[i/2] = Operators.MINUS;
						break;
					case "*":
						ops[i/2] = Operators.TIMES;
						break;
					case "/":
						ops[i/2] = Operators.DIVIDES;
						break;
					default:
						throw new NotAnOperatorException();
				}
			}
		}
	}
	@Override
	public double getValue(){
		try{
		double total = vals[0].getValue();
		for(int i = 1; i < vals.length; i++){
			/*switch(ops[i-1]){
				case PLUS:
					total += vals[i].getValue();
				case MINUS:
					total -= vals[i].getValue();
				case TIMES:
					total *= vals[i].getValue();
				case DIVIDES:
					total /= vals[i].getValue();
			}*/
			if(ops[i-1] == Operators.PLUS)
				total += vals[i].getValue();
			else if(ops[i-1] == Operators.MINUS)
				total -= vals[i].getValue();
			else if(ops[i-1] == Operators.TIMES)
				total *= vals[i].getValue();
			else if(ops[i-1] == Operators.DIVIDES)
				total /= vals[i].getValue();
		}
		error = false;
		return total;
		}catch(NotARealCellException e){
			error = true;
			return 0;
		}catch(StackOverflowError e){
			error = true;
			return 0;
		}
	}
	@Override
	public double getValue(SpreadsheetLocation loc) {
		try{
			double total = vals[0].getValue();
			for(int i = 1; i < vals.length; i++){
				/*switch(ops[i-1]){
					case PLUS:
						total += vals[i].getValue();
					case MINUS:
						total -= vals[i].getValue();
					case TIMES:
						total *= vals[i].getValue();
					case DIVIDES:
						total /= vals[i].getValue();
				}*/
				if(ops[i-1] == Operators.PLUS)
					total += vals[i].getValue(loc);
				else if(ops[i-1] == Operators.MINUS)
					total -= vals[i].getValue(loc);
				else if(ops[i-1] == Operators.TIMES)
					total *= vals[i].getValue(loc);
				else if(ops[i-1] == Operators.DIVIDES)
					total /= vals[i].getValue(loc);
			}
			error = false;
			return total;
			}catch(NotARealCellException | RecursiveLinkException | StackOverflowError e){
				error = true;
				return 0;
			}
	}

}