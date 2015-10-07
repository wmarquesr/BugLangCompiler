package enums;

import java.util.List;

import lexicalAnalyzer.Token;

public enum Symbol implements MyEnum {

	OPENPARENTESIS("(", "\\("),
	CLOSEPARENTESIS(")", "\\)"),
	OPENBRACE("{", "\\{"),
	CLOSEBRACE("}", "\\}"),
	SEMICOLON(";", ";"),
	COLON(":", ":"),
	QUOTATION("\"", "\\\""),
	DOT(".", "\\."),
	COMMA(",", ","),
	ATROPERATOR("=", "=");

	private String type;
	private String regex;
	private int precedency = 0;
	
	private Symbol(String type, String regex){
		this.type = type;
		this.regex = regex;
	}
	
	static final public String regex() {
		return OPENPARENTESIS.regex + "|" + CLOSEPARENTESIS.regex + "|" + OPENBRACE.regex + "|" +
				CLOSEBRACE.regex + "|" + SEMICOLON.regex + "|" + COLON.regex + "|" + QUOTATION.regex +
				"|" + DOT.regex + "|" + COMMA.regex + "|" + ATROPERATOR.regex;
	}
	
	static public void checkRegex(List<Token> list, String lex, int line) {
		if (lex.matches(OPENPARENTESIS.regex))
			list.add(OPENPARENTESIS.getToken(line));
		if (lex.matches(CLOSEPARENTESIS.regex))
			list.add(CLOSEPARENTESIS.getToken(line));
		if (lex.matches(OPENBRACE.regex))
			list.add(OPENBRACE.getToken(line));
		if (lex.matches(CLOSEBRACE.regex))
			list.add(CLOSEBRACE.getToken(line));
		if (lex.matches(SEMICOLON.regex))
			list.add(SEMICOLON.getToken(line));
		if (lex.matches(COLON.regex))
			list.add(COLON.getToken(line));
		if (lex.matches(QUOTATION.regex))
			list.add(QUOTATION.getToken(line));
		if (lex.matches(DOT.regex))
			list.add(DOT.getToken(line));
		if (lex.matches(COMMA.regex))
			list.add(COMMA.getToken(line));
		if (lex.matches(ATROPERATOR.regex))
			list.add(ATROPERATOR.getToken(line));
	}
	
	@Override
	public Token getToken(int line) {
		return new Token(this, type, line, precedency);
	}
}
