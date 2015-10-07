package lexicalAnalyzer;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import enums.AritOperator;
import enums.Command;
import enums.Literal;
import enums.LogicOperator;
import enums.RelOperator;
import enums.Symbol;
import enums.Type;

public class LexicalAnalyzer {

	private int line = 1;	
	private Scanner scanner;
	private Scanner tokenScanner;
	
	final private String regex = AritOperator.regex() + "|" + Command.regex() + "|" + Literal.regex() + "|" +
				LogicOperator.regex() + "|" + RelOperator.regex() + "|" + Symbol.regex() + "|" + Type.regex();

	/**
	 * Take the file .bl on the uri.
	 * @param uri Uri to file .bl
	 * @throws FileNotFoundException if source is not found
	 */
	public LexicalAnalyzer(URI uri) throws FileNotFoundException {
		scanner = new Scanner(new File(uri));
		if (scanner.hasNextLine())
			tokenScanner = new Scanner(scanner.nextLine());
	}

	/**
	 * Return a token in the list of tokens.
	 * @return The next token of the list
	 */
	public Token nextToken() {
		if(!tokenScanner.hasNext()){
			tokenScanner = new Scanner(scanner.nextLine());
			++line;
		}

		Token token = searchToken(tokenScanner.next());
		
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

	/**
	 * 
	 * @return true if there is tokens remaining to be consumed.
	 */
	public boolean hasNext() {
		return scanner.hasNext();
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
				System.out.println(la.nextToken());
			
			la.close();
		} catch (URISyntaxException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}		
}
