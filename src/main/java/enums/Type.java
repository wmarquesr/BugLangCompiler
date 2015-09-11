package enums;

public enum Type implements myEnum {
	
	INTTYPE("int"), 
	FLOATTYPE("float"), 
	STRINGTYPE("string"),
	ERROR("error"),
	IDENTIFIER("");

	private String type;
	
	private Type(String value){
		type = value;
	}
	
	public String getType(){
		return type;
	}
}
