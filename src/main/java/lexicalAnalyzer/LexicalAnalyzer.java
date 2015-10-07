package lexicalAnalyzer;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import enums.AritOperator;
import enums.Command;
import enums.Literal;
import enums.LogicOperator;
import enums.RelOperator;
import enums.Symbol;
import enums.Type;

public class LexicalAnalyzer {

	private List<String> currentFileLine;
	private List<String> allFileLines;
	private int line = 0;	
	
	final private String regex = AritOperator.regex() + "|" + Command.regex() + "|" + Literal.regex() + "|" +
				LogicOperator.regex() + "|" + RelOperator.regex() + "|" + Symbol.regex() + "|" + Type.regex();

	/**
	 * Take the file .bl on the path, divided per line and put it on a list. 
	 * @param path Path to file .el
	 */
	public LexicalAnalyzer(File path) {
		this.currentFileLine = new ArrayList<String>();
		this.allFileLines = new ArrayList<String>();

		List<String> lines;
		try {
			lines = Files.readAllLines(path.toPath(), StandardCharsets.UTF_8);
			allFileLines = convertListToLinkedList(lines);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Return a token in the list of tokens.
	 * @return The next token of the list
	 */
	public Token nextToken() {		
		if (allFileLines.size() == 0)
			return null;
		
		if(currentFileLine.size() == 0){
			currentFileLine = convertArrayToList(removeWhitespace(allFileLines.get(0)).split("\\s"));
			allFileLines.remove(0);
			line++;
		}

		Token thisToken = searchToken(currentFileLine.get(0));
		currentFileLine.remove(0);

		return thisToken;

	}
	
	private Token searchToken(String lex) {
		List<Token> list = new ArrayList<Token>();
		Token token = AritOperator.SUBOPERATOR.getToken(line);
		if (!lex.matches(regex))
			return Type.ERROR.getToken(line);
		
		AritOperator.checkRegex(list, lex, line);
		Command.checkRegex(list, lex, line);
		Literal.checkRegex(list, lex, line);
		LogicOperator.checkRegex(list, lex, line);
		RelOperator.checkRegex(list, lex, line);
		Symbol.checkRegex(list, lex, line);
		Type.checkRegex(list, lex, line);
		
		if (list.size() > 1) 
			list.sort(Token.comparator);
		
		token = list.get(0);
		return token;
	}

	private List<String> convertArrayToList(String[] s){
		List<String> news = new ArrayList<String>();

		for (int i = 0; i < s.length; i++) {
			news.add(s[i]);
		}

		return news;
	}

	private List<String> convertListToLinkedList(List<String> lines) {
		List<String> news = new ArrayList<String>();

		for (int i = 0; i < lines.size(); i++) {
			news.add(lines.get(i));
		}

		return news;
	}

	/**
	 * 
	 * @return The quantity of lines on the file .el
	 */
	public int size() {
		return allFileLines.size();
	}
	
	private String removeWhitespace(String s) {
		Pattern replace = Pattern.compile("\\s+");
		Matcher rm = replace.matcher(s.trim());
		return rm.replaceAll(" ");
	}
	
public static void main(String[] args) {
	URI uri;
	try {
		uri = ClassLoader.getSystemResource("ShellSort.bl").toURI();
		LexicalAnalyzer la = new LexicalAnalyzer(new File(uri));
		
		while(la.size() != 0)	
			System.out.println(la.nextToken().toString());
	} catch (URISyntaxException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}	
}
