package enums;

import java.util.List;

import lexicalAnalyzer.Token;

public enum AritOperator implements MyEnum {
	ADDOPERATOR("+", "\\+", 3),
	SUBOPERATOR("-", "-", 3),
	MULTOPERATOR("*", "\\*", 2),
	DIVOPERATOR("/", "\\/", 2);

	private String type;
	private String regex;
	private int precedency;
	
	private AritOperator(String type, String regex, int precedency){
		this.type = type;
		this.regex = regex;
		this.precedency = precedency;
	}
	
	static final public String regex() {
		return ADDOPERATOR.regex + "|" + SUBOPERATOR.regex + "|" + MULTOPERATOR.regex + "|" + DIVOPERATOR.regex;
	}
	
	static public void checkRegex(List<Token> list, String lex, int line) {
		if (lex.matches(ADDOPERATOR.regex))
			list.add(ADDOPERATOR.getToken(line));
		if (lex.matches(SUBOPERATOR.regex))
			list.add(SUBOPERATOR.getToken(line));
		if (lex.matches(MULTOPERATOR.regex))
			list.add(MULTOPERATOR.getToken(line));
		if (lex.matches(DIVOPERATOR.regex))
			list.add(DIVOPERATOR.getToken(line));
	}

	@Override
	public Token getToken(int line) {
		return new Token(this, type, line, precedency);
	}
}
