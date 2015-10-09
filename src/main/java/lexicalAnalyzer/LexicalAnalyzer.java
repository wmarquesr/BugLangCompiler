package lexicalAnalyzer;

import java.io.File;
import java.net.URI;
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
import utils.MyReader;

public class LexicalAnalyzer {

	private int line = 1;
	private Scanner scanner = null;
	private String buffer = "";
	private MyReader reader = null;
	final private String regex = AritOperator.regex() + "|" + Command.regex() + "|" + Literal.regex() + "|"
			+ LogicOperator.regex() + "|" + RelOperator.regex() + "|" + Symbol.regex() + "|" + Type.regex();

	/**
	 * Take the file .bl on the uri.
	 * 
	 * @param uri
	 *            Uri to file .bl
	 * @throws Exception
	 */
	public LexicalAnalyzer(URI uri) throws Exception {
		scanner = new Scanner(new File(uri));
		if (!scanner.hasNextLine())
			throw new Exception("empty file");
		reader = new MyReader(scanner.nextLine());
	}

	/**
	 * Return a token in the list of tokens.
	 * 
	 * @return The next token of the list
	 */
	public Token nextToken() {
		if (!buffer.isEmpty() && !buffer.matches("[a-zA-Z]+\\w*(:\\d+)?" + "|<|>|=|!|\"")) {
			return searchToken(buffer);
		}
		
		if (reader.isEmpty()) {
			reader = new MyReader(scanner.nextLine());
			++line;
		}
		while (!reader.isEmpty()) {
			String c = reader.read();

			
			if (c.matches("\\s")) {
				if (!buffer.isEmpty()) 
					return searchToken(buffer);
				else
					continue;
			} else if (c.matches(":")) {
				if (!buffer.isEmpty()) {
					if (buffer.matches("[a-zA-Z]+\\w*(:\\d+)?")) {
						buffer += c;
						continue;
					}
				}
				
				buffer = c;
				continue;
			} else if ((c.compareTo("\"") == 0) || buffer.matches("\"")) {
				if (!buffer.isEmpty() && !buffer.matches("\"")) {
					Token token = searchToken(buffer);
					buffer = c;
					return token;
				}
				
				buffer += c;
				inApas();
				return searchToken(buffer);
			} else if (c.matches(regex)) {
				if (!buffer.isEmpty()) {
					if (!(buffer + c).matches(regex)) {
						if (buffer.compareTo("\"") == 0) {
							buffer = c;
							return Type.ERROR.getToken(line);
						}
						
						Token token = searchToken(buffer);
						buffer = c;
						return token;	
					}
					
					buffer += c;
					continue;
				}
				
				if (c.matches(regex + "|<|>|=|!")) {
					buffer = c;
					continue;
				}
				
				return Type.ERROR.getToken(line);
			}
			
			if (reader.isEmpty() && !buffer.isEmpty()) {
				reader = new MyReader(scanner.nextLine());
				++line;
			}
			
		}

		return (buffer.length() > 0) ? searchToken(buffer) : Type.ERROR.getToken("Surprise error!", line, -1);
	}

	private Token searchToken(String lex) {
		List<Token> list = new ArrayList<Token>();

		AritOperator.checkRegex(list, lex, line);
		Command.checkRegex(list, lex, line);
		Literal.checkRegex(list, lex, line);
		LogicOperator.checkRegex(list, lex, line);
		RelOperator.checkRegex(list, lex, line);
		Symbol.checkRegex(list, lex, line);
		Type.checkRegex(list, lex, line);
		
		buffer = "";

		if (list.size() == 0)
			return Type.ERROR.getToken(line);

		if (list.size() > 1)
			list.sort(Token.comparator);
		
		return list.get(0);
	}

	/**
	 * 
	 * @return true if there is tokens remaining to be consumed.
	 */
	public boolean hasNext() {
		return scanner.hasNextLine() || !buffer.isEmpty() || !reader.isEmpty();
	}

	/**
	 * Close the lexical analyzer.
	 */
	public void close() {
		scanner.close();
	}

	private boolean inApas() {
			while (!reader.isEmpty()) {
				String c = reader.read();

				if (c.compareTo("\"") == 0) {
					buffer += c;
					return true;
				}
				buffer += c;
			}
		
	
		buffer = "";
		return false;
	}

	public static void main(String[] args) {
		URI uri;
		try {
			uri = ClassLoader.getSystemResource("ShellSort.bl").toURI();
			LexicalAnalyzer la = new LexicalAnalyzer(uri);

			while (la.hasNext())
				System.out.println(la.nextToken());

			la.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
