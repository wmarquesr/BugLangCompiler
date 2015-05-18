package lexicalAnalyzer;

import enums.myEnum;

public class Token {

	private myEnum type;
	private String value;
	int line;
	//private int currentTokenIndex;
	
	public Token(myEnum type, String value, int line){
		this.type = type;
		this.value = value;
		this.line  = line;
	}

	public void printToken(){
		System.out.println("[" + type + "," + value + "]");
	}
}
