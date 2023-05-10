package excel01;

import java.util.ArrayList;
import java.util.Scanner;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;



/*FUNCTIONS
 * 
 * 
 * basic: 
 * 
 * B3 --> displays the content of the cell
 * F7 = "hi" --> put the text to the cell
 * B2 = 5 --> put the number to the cell
 * C2 = 4.5 --> put the number to the cell
 * clear --> clear all
 * clear H13 --> clear the particular cell
 * quit --> quit the program
 * 
 * 
 * formulas:
 * 
 * A10 = ( SUM A1-B3 ) --> calculates the sum of the values in cell range
 * B10 = ( AVG A1-B3 ) --> calculates the average of the values in cell range
 * B3 = ( 4 * 6 + 3 ) --> calculates the simple math given as the expression
 * 
 * 
 */
public class main {
	public static void main(String[] args) throws ScriptException {
		
		
		//making blank cells
		Cell[][] cells = new Cell[21][13];
		
		for (int i=0; i<21;i++) {
			for (int j=0; j<13;j++) {
				cells[i][j]= new Cell();
			}
		}
		
		for (int i=1; i<21;i++) {
			char letter = (char)(64+i);
			for (int j=1; j<13;j++) {
				cells[i][j].setCellIdentifier(String.valueOf(letter)+String.valueOf(j));
				cells[i][j].setFullCellText("");
			}
		}
		//making blank spreadsheet
		Spreadsheet thisSpreadsheet = new Spreadsheet();
		System.out.println(thisSpreadsheet.grid(cells));
		
		
		//waiting for input
		boolean cont = true;
		while(cont) {
			//get the input
			Scanner scan = new Scanner(System.in);
			String answer = scan.nextLine();
		
			//quit
			if(answer.toLowerCase().equals("quit")){
				cont = false;
			}
			
			
			else if(answer.toLowerCase().contains("clear")) {
				//clear<cell>
				if (answer.length()>5) {
					thisSpreadsheet.clear(answer.substring(6));
				}
				
				//clear
				else {
					thisSpreadsheet.clear();
				}
			}
			
			//<cell> = <value>
			
			//<cell>
			else if (answer.length()==2) {
				thisSpreadsheet.getValue(answer);
			}
			else if (answer.contains("=")) {
				answer = answer.toUpperCase();
				
				//determine formula or regular input
				if (answer.contains("(")) { //formula
					
					if(answer.contains("SUM") || answer.contains("AVG")) {
						int spaceIndex = answer.indexOf(" ");
						int dashIndex = answer.indexOf("-");
						String id = answer.substring(0,spaceIndex);
						String start = answer.substring(spaceIndex+9,dashIndex);
						String end = answer.substring(dashIndex+1, answer.length()-2);
						
						if (answer.contains("SUM")) {
							thisSpreadsheet.sumFormula(id, start, end);
						}
						else if(answer.contains("AVG")) {
							thisSpreadsheet.avgFormula(id, start, end);
						}
						
					}
					
					else if (answer.contains("*")) {
						
						int spaceIndex = answer.indexOf(" ");
						
						//determining whether answer has letters or numbers
						String expression = answer.substring(7, answer.length()-2);
						int len = expression.length();
						boolean letter = false;
						for (int i=0; i<len; i++){
							if((Character.isLetter(expression.charAt(i)) == true)) {
								letter = true;
								break;
							}
						}
						if (letter == true) {
							//letter contained
							thisSpreadsheet.mathLetterContained(answer.substring(0,spaceIndex),expression);
						}
						else if (letter == false) {
							// letter not contained
							thisSpreadsheet.mathNumOnly(answer.substring(0,spaceIndex), expression);
						}
					}
				}else {//regular
					int spaceIndex = answer.indexOf(" ");
					thisSpreadsheet.setValue(answer.substring(0,spaceIndex), answer.substring(spaceIndex+3));
				}
				
			}
			
			else {
				continue;
			}
			
			System.out.println(thisSpreadsheet.grid(cells));
			
			
		}
		System.out.println("the end");
		
		
				
	}
}
