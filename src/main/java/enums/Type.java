package enums;

import java.util.List;

import lexicalAnalyzer.Token;

public enum Type implements MyEnum {
	
	INTTYPE("int", "int", 1), 
	FLOATTYPE("float", "float", 1), 
	STRINGTYPE("string", "string", 1),
	ERROR("error", "", 1),
	IDENTIFIER("", "[a-zA-Z]+\\w*|[a-zA-Z]+:\\d+", 5);

	private String type;
	private String regex;
	private int precedency;
	
	private Type(String type, String regex, int precedency){
		this.type = type;
		this.regex = regex;
		this.precedency = precedency;
	}
	
	static final public String regex() {
		return INTTYPE.regex + "|" + FLOATTYPE.regex + "|" + STRINGTYPE.regex + "|" + IDENTIFIER.regex;
	}
	
	static public void checkRegex(List<Token> list, String lex, int line) {
		if (lex.matches(INTTYPE.regex))
			list.add(INTTYPE.getToken(line));
		if (lex.matches(FLOATTYPE.regex))
			list.add(FLOATTYPE.getToken(line));
		if (lex.matches(STRINGTYPE.regex))
			list.add(STRINGTYPE.getToken(line));
		if (lex.matches(IDENTIFIER.regex)) {
			if (lex.contains(":")) 
				list.add(IDENTIFIER.getToken(lex, line, Integer.valueOf(lex.substring(lex.indexOf(":") + 1))));
			else
				list.add(IDENTIFIER.getToken(lex, line, -1));
		}
	}
	
	@Override
	public Token getToken(int line) {
		return new Token(this, type, line, precedency);
	}
	
	public Token getToken(String lex, int line, int size) {
		return new Token(this, lex, size, line, precedency);
	}
}
