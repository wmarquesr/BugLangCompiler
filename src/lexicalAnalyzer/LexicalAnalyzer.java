package lexicalAnalyzer;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;

import enums.AritOperator;
import enums.Command;
import enums.LogicOperator;
import enums.RelOperator;
import enums.Symbol;
import enums.Type;

public class LexicalAnalyzer {

	private List<Token> tokenList;
	private List<String> currentFileLine;
	private List<String> allFileLines;
	private File sourceCode;
	private int line = 0;	

	public LexicalAnalyzer(File sourceCode) throws IOException{
		this.tokenList = new LinkedList<Token>();
		this.sourceCode = sourceCode;
		this.currentFileLine = new LinkedList<String>();
		this.allFileLines = new LinkedList<String>();

		List<String> lines = Files.readAllLines(sourceCode.toPath(), StandardCharsets.UTF_8);
		allFileLines = convertListToLinkedList(lines);
	}

	public Token nextToken(){

		if (allFileLines.size() == 0) {
			return null;
		}
		if(currentFileLine.size() == 0){			
			currentFileLine = convertArrayToList(allFileLines.get(0).split(" "));
			allFileLines.remove(0);
			line++;
		}

		Token thisToken = searchToken(cleanString(currentFileLine.get(0)));
		currentFileLine.remove(0);

		return thisToken;

	}

	private Token searchToken(String tk){
		Token thisToken;

		switch (tk) {
		case "int":
			thisToken = new Token(Type.INTTYPE, Type.INTTYPE.getType(), line);
			tokenList.add(thisToken);
			break;
		case "float":
			thisToken = new Token(Type.FLOATTYPE, Type.FLOATTYPE.getType(), line);
			tokenList.add(thisToken);
			break;
		case "string":
			thisToken = new Token(Type.STRINGTYPE, Type.STRINGTYPE.getType(), line);
			tokenList.add(thisToken);
			break;
		case "+":
			thisToken = new Token(AritOperator.ADDOPERATOR, AritOperator.ADDOPERATOR.getType(), line);
			tokenList.add(thisToken);
			break;
		case "-":
			thisToken = new Token(AritOperator.SUBOPERATOR, AritOperator.SUBOPERATOR.getType(), line);
			tokenList.add(thisToken);
			break;
		case "*":
			thisToken = new Token(AritOperator.MULTOPERATOR, AritOperator.MULTOPERATOR.getType(), line);
			tokenList.add(thisToken);
			break;
		case "/":
			thisToken = new Token(AritOperator.DIVOPERATOR, AritOperator.DIVOPERATOR.getType(), line);
			tokenList.add(thisToken);
			break;
		case "¬":
			thisToken = new Token(LogicOperator.NEGOPERATOR, LogicOperator.NEGOPERATOR.getType(), line);
			tokenList.add(thisToken);
			break;
		case "&":
			thisToken = new Token(LogicOperator.ANDOPERATOR, LogicOperator.ANDOPERATOR.getType(), line);
			tokenList.add(thisToken);
			break;
		case "|":
			thisToken = new Token(LogicOperator.OROPERATOR, LogicOperator.OROPERATOR.getType(), line);
			tokenList.add(thisToken);
			break;
		case "==":
			thisToken = new Token(RelOperator.EQUALOPERATOR, RelOperator.EQUALOPERATOR.getType(), line);
			tokenList.add(thisToken);
			break;
		case "!=":
			thisToken = new Token(RelOperator.NEQUALOPERATOR, RelOperator.NEQUALOPERATOR.getType(), line);
			tokenList.add(thisToken);
			break;
		case "<":
			thisToken = new Token(RelOperator.LTOPERATOR, RelOperator.LTOPERATOR.getType(), line);
			tokenList.add(thisToken);
			break;
		case "<=":
			thisToken = new Token(RelOperator.LTEOPERATOR, RelOperator.LTEOPERATOR.getType(), line);
			tokenList.add(thisToken);
			break;
		case ">":
			thisToken = new Token(RelOperator.GTOPERATOR, RelOperator.GTOPERATOR.getType(), line);
			tokenList.add(thisToken);
			break;
		case ">=":
			thisToken = new Token(RelOperator.GTEOPERATOR, RelOperator.GTEOPERATOR.getType(), line);
			tokenList.add(thisToken);
			break;
		case "if":
			thisToken = new Token(Command.IFCOMMAND, Command.IFCOMMAND.getType(), line);
			tokenList.add(thisToken);
			break;
		case "while":
			thisToken = new Token(Command.WHILECOMMAND, Command.WHILECOMMAND.getType(), line);
			tokenList.add(thisToken);
			break;
		case "for":
			thisToken = new Token(Command.FORCOMMAND, Command.FORCOMMAND.getType(), line);
			tokenList.add(thisToken);
			break;
		case "(":
			thisToken = new Token(Symbol.OPENPARENTESIS, Symbol.OPENPARENTESIS.getType(), line);
			tokenList.add(thisToken);
			break;
		case ")":
			thisToken = new Token(Symbol.CLOSEPARENTESIS, Symbol.CLOSEPARENTESIS.getType(), line);
			tokenList.add(thisToken);
			break;
		case "{":
			thisToken = new Token(Symbol.OPENBRACE, Symbol.OPENBRACE.getType(), line);
			tokenList.add(thisToken);
			break;
		case "}":
			thisToken = new Token(Symbol.CLOSEBRACE, Symbol.CLOSEBRACE.getType(), line);
			tokenList.add(thisToken);
			break;
		case ";":
			thisToken = new Token(Symbol.SEMICOLON, Symbol.SEMICOLON.getType(), line);
			tokenList.add(thisToken);
			break;
		case ":":
			thisToken = new Token(Symbol.COLON, Symbol.COLON.getType(), line);
			tokenList.add(thisToken);
			break;
		case ".":
			thisToken = new Token(Symbol.DOT, Symbol.DOT.getType(), line);
			tokenList.add(thisToken);
			break;
		case ",":
			thisToken = new Token(Symbol.COMMA, Symbol.COMMA.getType(), line);
			tokenList.add(thisToken);
			break;
		case "\"":
			thisToken = new Token(Symbol.QUOTATION, Symbol.QUOTATION.getType(), line);
			tokenList.add(thisToken);
			break;
		case "=":
			thisToken = new Token(Symbol.ATROPERATOR, Symbol.ATROPERATOR.getType(), line);
			tokenList.add(thisToken);
			break;
		default:
			if (isNumber(tk)) {
				
				if (tk.contains(".")) {
					thisToken = new Token(Type.FLOATTYPE, tk, line);
					tokenList.add(thisToken);
					break;
				} else {
					thisToken = new Token(Type.INTTYPE, tk, line);
					tokenList.add(thisToken);
					break;
				}
			}
			
			if (tk.contains(":")) {
				thisToken = new Token(Type.IDENTIFIER, tk, Integer.parseInt(tk.substring(tk.indexOf(":")+1)), line);
				tokenList.add(thisToken);
				break;
			}
			
			thisToken = new Token(Type.IDENTIFIER, tk, line);
			tokenList.add(thisToken);
			break;
		}

		return thisToken;
	}
	
	//Remove all blank spaces and tabulation.
	private String cleanString(String str){
		char[] c = str.toCharArray();
		String newStr = "";
		
		for (int i = 0; i < c.length; i++) {
			if (c[i] != ' ' && c[i] != '\t') {
				newStr = newStr + c[i];
			}
		}
		
		return newStr;
	}

	private boolean isNumber(String tk) {
		if (tk.startsWith("1") || tk.startsWith("2") || tk.startsWith("3") 
				|| tk.startsWith("4") || tk.startsWith("5") || tk.startsWith("6") 
				|| tk.startsWith("7") || tk.startsWith("8") || tk.startsWith("9")
				|| tk.startsWith("0")) {
			return true;
		}

		return false;
	}
	
	public List<String> getAllFileLines(){
		return allFileLines;
	}
	
	public List<Token> getTokenList(){
		return tokenList;
	}

	private LinkedList<String> convertArrayToList(String[] s){
		LinkedList<String> news = new LinkedList<String>();

		for (int i = 0; i < s.length; i++) {
			news.add(s[i]);
		}

		return news;
	}

	private LinkedList<String> convertListToLinkedList(List<String> lines) {
		LinkedList<String> news = new LinkedList<String>();

		for (int i = 0; i < lines.size(); i++) {
			news.add(lines.get(i));
		}

		return news;
	}

}
