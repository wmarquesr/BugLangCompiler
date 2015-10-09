package enums;

import java.util.List;

import lexicalAnalyzer.Token;

public enum Literal implements MyEnum {
	INTLITERAL("intliteral", "\\d+"),
	FLOATLITERAL("floatliteral", Literal.INTLITERAL.regex + ".\\d+"),
	STRINGLITERAL("stringliteral", "\".*\"");

	private String type;
	private String regex;
	private int precedency = 2;
	
	private Literal(String type, String regex) {
		this.type = type;
		this.regex = regex;
	}
	
	static final public String regex() {
		return INTLITERAL.regex + "|" + FLOATLITERAL.regex + "|" + STRINGLITERAL.regex;
	}
	
	static public void checkRegex(List<Token> list, String lex, int line) {
		if (lex.matches(INTLITERAL.regex))
			list.add(INTLITERAL.getToken(lex, line));
		if (lex.matches(FLOATLITERAL.regex))
			list.add(FLOATLITERAL.getToken(lex, line));
		if (lex.matches(STRINGLITERAL.regex))
			list.add(STRINGLITERAL.getToken(lex.substring(1, lex.length() - 1), line));
	}
	
	@Override
	public Token getToken(int line) {
		return new Token(this, type, line, precedency);
	}
	
	public Token getToken(String value, int line) {
		return new Token(this, value, line, precedency);
	}
}
