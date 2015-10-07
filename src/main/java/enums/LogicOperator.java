package enums;

import java.util.List;

import lexicalAnalyzer.Token;

public enum LogicOperator implements MyEnum {
	NEGOPERATOR("¬", "¬", 1),
	ANDOPERATOR("&", "&", 5),
	OROPERATOR("|", "\\|", 5);

	private String type;
	private String regex;
	private int precedency;

	private LogicOperator(String type, String regex, int precedency){
		this.type = type;
		this.regex = regex;
		this.precedency = precedency;
	}
	
	static final public String regex() {
		return NEGOPERATOR.regex + "|" + ANDOPERATOR.regex + "|" + OROPERATOR.regex;
	}
	
	static public void checkRegex(List<Token> list, String lex, int line) {
		if (lex.matches(NEGOPERATOR.regex))
			list.add(NEGOPERATOR.getToken(line));
		if (lex.matches(ANDOPERATOR.regex))
			list.add(ANDOPERATOR.getToken(line));
		if (lex.matches(OROPERATOR.regex))
			list.add(OROPERATOR.getToken(line));
	}
	
	@Override
	public Token getToken(int line) {
		return new Token(this, type, line, precedency);
	}
}
