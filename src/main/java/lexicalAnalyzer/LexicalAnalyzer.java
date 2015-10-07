package lexicalAnalyzer;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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
	private int line = 0;	
	private Scanner scanner;
	
	final private String regex = AritOperator.regex() + "|" + Command.regex() + "|" + Literal.regex() + "|" +
				LogicOperator.regex() + "|" + RelOperator.regex() + "|" + Symbol.regex() + "|" + Type.regex();

	/**
	 * Take the file .bl on the uri.
	 * @param uri Uri to file .bl
	 * @throws FileNotFoundException if source is not found
	 */
	public LexicalAnalyzer(URI uri) throws FileNotFoundException {
		this.currentFileLine = new ArrayList<String>();
		scanner = new Scanner(new File(uri));
	}

	/**
	 * Return a token in the list of tokens.
	 * @return The next token of the list
	 */
	public Token nextToken() {		
		if (!scanner.hasNextLine())
			return null;
		
		if(currentFileLine.size() == 0){
			currentFileLine = convertArrayToList(removeWhitespace(scanner.nextLine()).split("\\s"));
			line++;
		}

		Token token = searchToken(currentFileLine.get(0));
		currentFileLine.remove(0);
		
		return token;

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

	/**
	 * 
	 * @return The quantity of lines on the file .el
	 */
	public boolean hasNext() {
		return scanner.hasNext();
	}
	
	private String removeWhitespace(String s) {
		Pattern replace = Pattern.compile("\\s+");
		Matcher rm = replace.matcher(s.trim());
		return rm.replaceAll(" ");
	}
	
	/**
	 * Close the lexical analyzer.
	 */
	public void close() {
		scanner.close();
	}
	
	public static void main(String[] args) {
		URI uri;
		try {
			uri = ClassLoader.getSystemResource("ShellSort.bl").toURI();
			LexicalAnalyzer la = new LexicalAnalyzer(uri);
		
			while(la.hasNext())	
				System.out.println(la.nextToken().toString());
			
			la.close();
		} catch (URISyntaxException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}		
}
