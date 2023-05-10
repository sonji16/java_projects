package excel01;

import java.util.ArrayList;

public class Cell {
	
	//fields
	private String cellIdentifier;
	private String cellType;
	private String fullCellText;
	
	
	//constructors
	public Cell() {
		this.cellIdentifier = null;
		this.cellType = null;
		this.fullCellText = null;
	}
	public Cell(String cellIdentifier, String fullCellText) {
		
		//cellIedntifier
		this.cellIdentifier = cellIdentifier;
		
		//fullCellText
		
		if (fullCellText.length()>10) {
			this.fullCellText = fullCellText.substring(0, 10);
		}else {
			this.fullCellText = fullCellText;
		}
		
		
		//cellType
		if (fullCellText.contains("=")) {
			this.cellType = "FormulaCell";
		}
		else if (fullCellText.contains("%")) {
			this.cellType = "PercentCell";
		}
		else if (fullCellText.contains(".")) {
			this.cellType = "ValueCell";
		}else {
			this.cellType = "TextCell";
		}
	}
	
	
	//methods
	public void setCellIdentifier(String yeah) {
		this.cellIdentifier = yeah;
	}
	public void setCellType() {
		if (fullCellText.contains("=")) {
			this.cellType = "FormulaCell";
		}
		else if (fullCellText.contains("%")) {
			this.cellType = "PercentCell";
		}
		else if (fullCellText.contains(".")) {
			this.cellType = "ValueCell";
		}else {
			this.cellType = "TextCell";
		}
	}
	public void setFullCellText(String ye) {
		if (ye.length()>=10) {
			this.fullCellText = ye.substring(0,10);
		}else {
			this.fullCellText = ye;
		}
		setCellType();
	}
	public String getCellIdentifier() {
		return cellIdentifier;
	}
	public String getCellType() {
		return cellType;
	}
	public String getFullCellText() {
		return fullCellText;
	}
	public String print() {
		return getCellIdentifier()+","+getCellType()+","+getFullCellText();
	}
}
