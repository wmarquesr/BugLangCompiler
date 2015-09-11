package enums;

public enum AritOperator implements myEnum {
	ADDOPERATOR("+"),
	SUBOPERATOR("-"),
	MULTOPERATOR("*"),
	DIVOPERATOR("/");

	private String type;
	
	private AritOperator(String value){
		type = value;
	}
	
	public String getType(){
		return type;
	}
}
