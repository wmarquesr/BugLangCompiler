package lexicalAnalyzer;

import java.util.Map;

public class Token {

	private Type type;
	private String value;
	//private int currentTokenIndex;
	
	public Token(Type type, String value){
		this.type = type;
		this.value = value;
		//this.currentTokenIndex = 0;
	}
	
	public void printToken(){
		System.out.println("[" + type + "," + value + "]");
	}
}
