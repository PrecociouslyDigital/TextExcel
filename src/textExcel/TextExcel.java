package textExcel;
// Update this file with your own code.

import java.util.Scanner;

import textExcel.Tests.Helper;


public class TextExcel{
        public static Grid sheet = new Spreadsheet();
	public static void main(String[] args){
		String input = "";
                Scanner con = new Scanner(System.in);
                System.out.println(sheet.getGridText());
                input = con.nextLine().trim();
                while(!input.equalsIgnoreCase("quit")){
                    System.out.println(sheet.processCommand(input));
                    input = con.nextLine();
                }
	}
}
