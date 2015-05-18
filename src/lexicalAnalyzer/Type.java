package lexicalAnalyzer;

public enum Type {
	
	INT("int"), 
	FLOAT("float"), 
	STRING("string"),
	ID("");
	
	private String type;
	
	private Type(String value){
		type = value;
	}
	
	public String getType(){
		return type;
	}
}
