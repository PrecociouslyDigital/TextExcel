package textExcel;
// Update this file with your own code.

import java.util.Scanner;


public class TextExcel{
        public static Grid sheet;
	public static void main(String[] args){
		sheet = new Spreadsheet(); // Keep this as the first statement in main
		String input = "";
                Scanner con = new Scanner(System.in);
                System.out.println(sheet.getGridText());
                while(!input.equalsIgnoreCase("quit")){
                    System.out.println(sheet.processCommand(input));
                    input = con.nextLine();
                }
	}
}
