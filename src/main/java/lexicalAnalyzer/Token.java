package lexicalAnalyzer;

import enums.myEnum;

public class Token {

	private myEnum type;
	private String value;
	private int line;
	private int arraySize = -1;
	
	public Token(myEnum type, String value, int line){
		this.type = type;
		this.value = value;
		this.line  = line;
	}
	
	public Token(myEnum type, String value, int arraySize, int line){
		this.type = type;
		this.value = value;
		this.arraySize = arraySize;
		this.line  = line;
	}

	@Override
	public String toString(){
		if (arraySize == -1) {
			return ("[ " + type + " , " + value + " , " + line + " ]");
		} else {
			return ("[ " + type + " , " + value + " , " + arraySize + " , " + line + " ]");
		}
		
	}

	public myEnum getType() {
		return type;
	}

	public String getValue() {
		return value;
	}
	
	public int getArraySize() {
		return arraySize;
	}

	public int getLine() {
		return line;
	}	
}
