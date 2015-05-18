package enums;

public enum Command implements myEnum {

	WHILECOMMAND("while"),
	FORCOMMAND("for"),
	IFCOMMAND("if");
	
	private String type;
	
	private Command(String value){
		type = value;
	}
	
	public String getType(){
		return type;
	}
}
