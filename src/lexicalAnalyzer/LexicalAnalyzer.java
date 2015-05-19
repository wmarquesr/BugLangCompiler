package lexicalAnalyzer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import enums.AritOperator;
import enums.Command;
import enums.LogicOperator;
import enums.RelOperator;
import enums.Symbol;
import enums.Type;

public class LexicalAnalyzer {

	List<Token> tokenList;
	BufferedReader br;
	int line = 0;
	int row = 0;
	List<String> currentLine;

	public LexicalAnalyzer(String file) throws IOException{
		this.tokenList = new LinkedList<Token>();
		FileReader fr = new FileReader(file);
		br = new BufferedReader(fr);
	}

	public Token nextToken(){
		if (currentLine == null)
			currentLine = new ArrayList<String>();
		/*if (allLines.size() == 0) {
			System.out.println("No more tokens");
			return new Token(null, null);
		} else {*/
			if(currentLine.isEmpty()){			
				try {
					currentLine = convertArrayToList(br.readLine().split(" "));
					row = 0;
					++line;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			Token thisToken = searchToken(currentLine.get(row));
			++row;
			return thisToken;
		//}
	}

	private Token searchToken(String tk){
		Token thisToken;

		//Verify if tk is a valid token in Type
		switch (tk) {
		case "int":
			thisToken = new Token(Type.INTTYPE, Type.INTTYPE.getType(), line);
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
			if (idisnumber(tk)) {
				thisToken = new Token(Type.ERROR, Type.ERROR.getType(), line);
			break;	
			}
			thisToken = new Token(Type.IDENTIFIER, tk, line);
			break;
		}

		return thisToken;
	}

	private boolean idisnumber(String tk) {
	if (tk.startsWith("1") || tk.startsWith("2") || tk.startsWith("3") 
			|| tk.startsWith("4") || tk.startsWith("5") || tk.startsWith("6") 
			|| tk.startsWith("7") || tk.startsWith("8") || tk.startsWith("9")
			|| tk.startsWith("0")) {
		return true;
	}
	
		return false;
	}
	
	private LinkedList<String> convertArrayToList(String[] s){
		LinkedList<String> news = new LinkedList<String>();

		for (int i = 0; i < s.length; i++) {
			news.add(s[i]);
		}

		return news;
	}

}
