package enums;

import java.util.List;

import lexicalAnalyzer.Token;

public enum RelOperator implements MyEnum {
	EQUALOPERATOR("=="),
	NEQUALOPERATOR("!="),
	GTOPERATOR(">"),
	LTOPERATOR("<"),
	GTEOPERATOR(">="),
	LTEOPERATOR("<=");
	
	private String type;
	private  String regex;
	private int precedency = 4;
	
	private RelOperator(String type){
		this.type = type;
		this.regex = type;
	}
	
	static final public String regex() {
		return EQUALOPERATOR.regex + "|" + NEQUALOPERATOR.regex + "|" + GTOPERATOR.regex + 
				"|" + LTOPERATOR.regex + "|" + GTEOPERATOR.regex + "|" + LTEOPERATOR.regex;
	}
	
	static public void checkRegex(List<Token> list, String lex, int line) {
		if (lex.matches(EQUALOPERATOR.regex))
			list.add(EQUALOPERATOR.getToken(line));
		if (lex.matches(NEQUALOPERATOR.regex))
			list.add(NEQUALOPERATOR.getToken(line));
		if (lex.matches(GTOPERATOR.regex))
			list.add(GTOPERATOR.getToken(line));
		if (lex.matches(LTOPERATOR.regex))
			list.add(LTOPERATOR.getToken(line));
		if (lex.matches(GTEOPERATOR.regex))
			list.add(GTEOPERATOR.getToken(line));
		if (lex.matches(LTEOPERATOR.regex))
			list.add(LTEOPERATOR.getToken(line));
	}
	
	@Override
	public Token getToken(int line) {
		return new Token(this, type, line, precedency);
	}
}
