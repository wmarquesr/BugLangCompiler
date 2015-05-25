package enums;

public enum Literal implements myEnum {
	FLOATLITERAL("floatliteral"),
	INTLITERAL("intliteral");

	private String type;
	
	private Literal(String value){
		type = value;
	}
	
	public String getType(){
		return type;
	}
}
