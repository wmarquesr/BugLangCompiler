package enums;

public enum RelOperator implements myEnum {
	EQUALOPERATOR("="),
	NEQUALOPERATOR("!="),
	GTOPERATOR(">"),
	LTOPERATOR("<"),
	GTEOPERATOR(">="),
	LTEOPERATOR("<=");
	
private String type;
	
	private RelOperator(String value){
		type = value;
	}
	
	public String getType(){
		return type;
	}
}
