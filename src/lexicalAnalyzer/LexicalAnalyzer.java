package lexicalAnalyzer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class LexicalAnalyzer {

	private List<Token> tokenList;
	private File sourceCode;
	private List<String> currentLine;
	private List<String> allLines;

	public LexicalAnalyzer(File sourceCode) throws IOException{
		this.tokenList = new LinkedList<Token>();
		this.sourceCode = sourceCode;
		this.currentLine = new LinkedList<String>();
		this.allLines = new LinkedList<String>();
		
		List<String> lines = Files.readAllLines(sourceCode.toPath(), StandardCharsets.UTF_8);
		allLines = convertListToLinkedList(lines);
	}

	public Token nextToken(){
		/*if (allLines.size() == 0) {
			System.out.println("No more tokens");
			return new Token(null, null);
		} else {*/
			if(currentLine.size() == 0){			
				currentLine = convertArrayToList(allLines.get(0).split(" "));
				allLines.remove(0);
			}

			Token thisToken = searchToken(currentLine.get(0));
			currentLine.remove(0);

			return thisToken;
		//}
	}

	private Token searchToken(String tk){
		Token thisToken;

		//Verify if tk is a valid token in Type
		switch (tk) {
		case "int":
			thisToken = new Token(Type.INT, Type.INT.getType());
			tokenList.add(thisToken);
			return thisToken;
		case "float":
			thisToken = new Token(Type.FLOAT, Type.FLOAT.getType());
			tokenList.add(thisToken);
			return thisToken;
		case "string":
			thisToken = new Token(Type.STRING, Type.STRING.getType());
			tokenList.add(thisToken);
			return thisToken;
		default:
			String index = Integer.toString(tokenList.size() - 1);
			thisToken = new Token(Type.ID, index);
			return thisToken;
		}
	}

	private LinkedList<String> convertArrayToList(String[] s){
		LinkedList<String> news = new LinkedList<String>();

		for (int i = 0; i < s.length; i++) {
			news.add(s[i]);
		}

		return news;
	}
	
	private void printList(List<String> currentLine2){
		for (int i = 0; i < currentLine2.size(); i++) {
			System.out.println(currentLine2.get(i));
		}
	}
	
	private LinkedList<String> convertListToLinkedList(List<String> list){
		LinkedList<String> ll = new LinkedList<String>();
		for (int i = 0; i < list.size(); i++) {
			ll.add(list.get(i));
		}
		
		return ll;
	}

}
