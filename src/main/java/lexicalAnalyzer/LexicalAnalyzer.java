package lexicalAnalyzer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
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

public class LexicalAnalyzer {

	private int line = 1;	
	private Scanner scanner = null;
	private String buffer = "";
	private Reader reader = null;
	final private String regex = AritOperator.regex() + "|" + Command.regex() + "|" + Literal.regex() + "|" +
				LogicOperator.regex() + "|" + RelOperator.regex() + "|" + Symbol.regex() + "|" + Type.regex();

	/**
	 * Take the file .bl on the uri.
	 * @param uri Uri to file .bl
	 * @throws Exception 
	 */
	public LexicalAnalyzer(URI uri) throws Exception {
		scanner = new Scanner(new File(uri));
		if (!scanner.hasNextLine())
			throw new Exception("empty file");
		reader = new StringReader(scanner.nextLine());
	}

	/**
	 * Return a token in the list of tokens.
	 * @return The next token of the list
	 */
	public Token nextToken() {
		int i;
		try {
			while(scanner.hasNext()) {
				if ((i = reader.read()) == -1) {
					reader = new StringReader(scanner.nextLine());
					++line;
				}
				
				while(i != -1) {
					String c = String.valueOf(((char) i));
					
					if (c.matches("\\s")) {
						if (buffer.length() > 0) {
							Token token = searchToken(buffer);
							buffer = "";
							return token;
						} else {
							i = reader.read();
							continue;
						}
					}  else if (c.matches(":")) {
						if (buffer.length() > 0) {
							if (buffer.matches("[a-zA-Z]+\\w*(:\\d+)?"))
								buffer += c;
							else
								return Type.ERROR.getToken(line);
						} else
							buffer = c;
					} else if (c.compareTo("\"") == 0) {
						if (buffer.length() > 0) {
							Token token = searchToken(buffer);
							buffer = c;
							return token;
						} 
						
						buffer = c;
						if (!inApas())
							return Type.ERROR.getToken(line);							
						
					} else if (c.matches(regex)) {
					
						if (buffer.length() > 0) {
							if ((buffer + c).matches(regex))
								buffer += c;
							else {
								if (buffer.compareTo("\"") == 0) {
									buffer = c;
									return Type.ERROR.getToken(line);
								}
								
								Token token = searchToken(buffer);
								buffer = c;
								return token;
							}
						} else {
							if (c.matches(regex))
								buffer += c;
							else
								return Type.ERROR.getToken(c + " doesn't", line, -1);
						}
					}
//				if (!c.matches("\\s")) {
//					if (!buffer.matches(regex)) 
//						return Type.ERROR.getToken(buffer.matches("\\s+") + " doesn't not exist!", line, -1);
//					else {
//						Token token = searchToken(buffer);
//						buffer = "";
//						return token;
//					}
//				} else {
//					if (buffer.length() != 0)
//						if (!(buffer + c).matches(regex)) {
//							Token token = searchToken(buffer);
//							buffer = c;
//							return token;
//						} else
//							buffer += c;
//					else
//						if (!c.matches(regex))
//							return Type.ERROR.getToken(c + " doesn't not exist!", line, -1);
//						else
//							buffer += c;
//				}
					
					i = reader.read();					
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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
		
		if (list.size() > 1) 
			list.sort(Token.comparator);
		
		return list.get(0);
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
	
	private boolean inApas() {
		try {
			int i;
			while((i = reader.read()) != -1) {
				String c = String.valueOf(((char) i));
				
				if (c.compareTo("\"") == 0) {
					buffer += c;
					return true;
				}
				buffer += c;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		buffer = "";
		return false;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		URI uri;
 		try {
			uri = ClassLoader.getSystemResource("ShellSort.bl").toURI();
			LexicalAnalyzer la = new LexicalAnalyzer(uri);
 		
 			while(la.hasNext())	
 				System.out.println(la.nextToken());
 			
 			la.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}		
}
