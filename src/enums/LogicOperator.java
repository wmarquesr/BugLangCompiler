package enums;

public enum LogicOperator implements myEnum {
	NEGOPERATOR("¬"),
	ANDOPERATOR("&"),
	OROPERATOR("|");

	private String type;

	private LogicOperator(String value){
		type = value;
	}

	public String getType(){
		return type;
	}
}
