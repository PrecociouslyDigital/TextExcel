package textExcel;
// Update this file with your own code.

import java.util.Scanner;


public class TextExcel
{

	public static void main(String[] args)
	{
		Grid sheet = new Spreadsheet(); // Keep this as the first statement in main
		String input = "";
                Scanner con = new Scanner(System.in);
                while(!input.equalsIgnoreCase("quit")){
                    System.out.println(sheet.processCommand(input));
                }
	}
}
