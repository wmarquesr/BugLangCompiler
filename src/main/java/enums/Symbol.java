package enums;

public enum Symbol implements myEnum {

	OPENPARENTESIS("("),
	CLOSEPARENTESIS(")"),
	OPENBRACE("{"),
	CLOSEBRACE("}"),
	SEMICOLON(";"),
	COLON(":"),
	QUOTATION("\""),
	DOT("."),
	COMMA(","),
	ATROPERATOR("=");

private String type;
	
	private Symbol(String value){
		type = value;
	}
	
	public String getType(){
		return type;
	}
}
