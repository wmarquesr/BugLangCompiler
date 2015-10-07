package lexicalAnalyzer;

import java.util.Comparator;

import enums.MyEnum;

public class Token implements Comparable<Token> {

	private MyEnum type;
	private String value;
	private int line;
	private int arraySize;
	private int precedency;
			
	public Token(MyEnum type, String value, int line, int precedency){
		this(type, value, -1, line, precedency);
	}
	
	public Token(MyEnum type, String value, int arraySize, int line, int precedency){
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

	public MyEnum getType() {
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

	@Override
	public int compareTo(Token o) {
		return o.precedency - this.precedency;
	}	
	
	static public Comparator<Token> comparator = new Comparator<Token>() {
		public int compare(Token t1, Token t2) {
			return t1.compareTo(t2);
		}
	};
}
