package utils;

public class MyReader {
	
	private String string;
	private int index = 0;
	
	public MyReader(String string) {
		this.string = string;
	}

	public String read() {
		char c = string.charAt(index);
		++index;
		return String.valueOf(c);
	}
	
	public boolean isEmpty() {
		return (index == string.length());
	}
}
