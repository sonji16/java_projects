package excel01;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class Spreadsheet {
	String result;
	Cell[][] cellsss;
	
	//constructor
	public String grid(Cell[][] cellsss) {
		this.cellsss = cellsss;
		result = "   |";
		
			//0st line
			for (int j=1; j<13; j++) {
				result += String.valueOf((char)(64+j))+"         |";
			}
			result +="\n";
			
			//1st line and beyond
			for(int i=1; i<21;i++) {
				for (int j=0; j<13;j++) {
					if (j==0) {
						if (i>=10) {
							result+= i+ " |";
						}
						else {
							result+= i + "  |";
						}
						
						
					}else {
						result += this.cellsss[i][j].getFullCellText();
						for (int k=0; k<10-this.cellsss[i][j].getFullCellText().length();k++) {
							result+= " ";
						}
						result += "|";
					}
				}
				result +="\n";
			}
			
			
		
		
		
		return result;
	}
	
	//methods
	
	public void clear() {
		for(int i=1; i<21;i++) {
			for (int j=1; j<13;j++) { 
				cellsss[i][j].setFullCellText("");
			}
		}
	}
	
	public void clear(String s) {
		s = s.toUpperCase();
		int j = s.charAt(0)-64;
		int i = Integer.valueOf(s.substring(1));
		
		cellsss[i][j].setFullCellText("");
		
	}
	
	public void setValue(String identifier, String value) {
		identifier = identifier.toUpperCase();
		
		int j = identifier.charAt(0)-64;
		int i = Integer.valueOf(identifier.substring(1));
		
		if (value.contains("\"")){
			cellsss[i][j].setFullCellText(value.substring(1, value.length()-1));
		}
		else {
			cellsss[i][j].setFullCellText(value);
		}
	}
	
	
	public void getValue(String identifier) {
		identifier = identifier.toUpperCase();
		int j = identifier.charAt(0)-64;
		int i = Integer.valueOf(identifier.substring(1));
		
		System.out.println(cellsss[i][j].getFullCellText());
	}
	
	//formulas
	public void sumFormula(String identifier, String startId, String endId) {
		//all already uppercase
		//separating the identifier into indexes
		int j = identifier.charAt(0)-64; //L
		int i = Integer.valueOf(identifier.substring(1));//14
		
		int jj = startId.charAt(0)-64;//B
		int ii = Integer.valueOf(startId.substring(1));//6
		
		int jjj = endId.charAt(0)-64;//C
		int iii = Integer.valueOf(endId.substring(1));//12
		
		double result = 0;
		for (int a = ii; a<=iii; a++) {
			for(int b = jj; b<=jjj; b++) {
				result+= Double.valueOf(cellsss[a][b].getFullCellText());
			}
		}
		String resultt = String.valueOf(result);
		cellsss[i][j].setFullCellText(resultt);
		
	}
	
	public void avgFormula(String identifier, String startId, String endId) {
		//all already uppercase
		//separating the identifier into indexes
		int j = identifier.charAt(0)-64; //L
		int i = Integer.valueOf(identifier.substring(1));//14
		
		int jj = startId.charAt(0)-64;//B
		int ii = Integer.valueOf(startId.substring(1));//6
		
		int jjj = endId.charAt(0)-64;//C
		int iii = Integer.valueOf(endId.substring(1));//12
		
		double result = 0;
		for (int a = ii; a<=iii; a++) {
			for(int b = jj; b<=jjj; b++) {
				result+= Double.valueOf(cellsss[a][b].getFullCellText());
			}
		}
		int num = (iii-ii +1)*(jjj-jj+1);
		result = result/(double) num;
		String resultt = String.valueOf(result);
		cellsss[i][j].setFullCellText(resultt);
				
	}
	
	public void mathNumOnly(String identifier, String expression) throws ScriptException {
		int j = identifier.charAt(0)-64; 
		int i = Integer.valueOf(identifier.substring(1));
		
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");
		
		cellsss[i][j].setFullCellText(String.valueOf(engine.eval(expression)));
	}
	
	public void mathLetterContained(String identifier, String expression) throws ScriptException {
		int j = identifier.charAt(0)-64; 
		int i = Integer.valueOf(identifier.substring(1));
		
		String temp;
		int y;
		
		for (int k=0;k<expression.length();k++) {
			int x;
;			if ((Character.isLetter(expression.charAt(k)))==true) {
				x= expression.charAt(k)-64;
				temp = expression.substring(k+1);
				y = Integer.valueOf(expression.substring(k+1,temp.indexOf(" ")));
				expression = expression.substring(0, k)+cellsss[y][x].getFullCellText()+expression.substring(temp.indexOf(" "));
			}

			ScriptEngineManager mgr = new ScriptEngineManager();
			ScriptEngine engine = mgr.getEngineByName("JavaScript");
			
			cellsss[i][j].setFullCellText(String.valueOf(engine.eval(expression)));
		}
	}
	
}
