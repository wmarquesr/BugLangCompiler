package enums;

import java.util.List;

import lexicalAnalyzer.Token;

public enum Command implements MyEnum {

	WHILECOMMAND("while"),
	FORCOMMAND("for"),
	RETURNCOMMAND("return"),
	IFCOMMAND("if");
	
	private String type;
	private String regex;
	private int precedency = 0;
	
	private Command(String type){
		this.type = type;
		this.regex = type;
	}
	
	static final public String regex() {
		return WHILECOMMAND.regex + "|" + FORCOMMAND.regex + "|" + RETURNCOMMAND.regex + "|" + IFCOMMAND.regex;
	}
	
	static public void checkRegex(List<Token> list, String lex, int line) {
		if (lex.matches(WHILECOMMAND.regex))
			list.add(WHILECOMMAND.getToken(line));
		if (lex.matches(FORCOMMAND.regex))
			list.add(FORCOMMAND.getToken(line));
		if (lex.matches(RETURNCOMMAND.regex))
			list.add(RETURNCOMMAND.getToken(line));
		if (lex.matches(IFCOMMAND.regex))
			list.add(IFCOMMAND.getToken(line));
	}
	
	@Override
	public Token getToken(int line) {
		return new Token(this, type, line, precedency);
	}
}
