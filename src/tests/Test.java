package tests;

import java.io.File;
import java.io.IOException;

import enums.Type;
import lexicalAnalyzer.LexicalAnalyzer;
import lexicalAnalyzer.Token;

public class Test {

	public static void main(String[] args) throws IOException {
		
		String path = "C:\\Users\\W\\Desktop\\ShellSort.bl";
		File file = new File(path);
		
		LexicalAnalyzer lex = new LexicalAnalyzer(file);
		
		while(lex.getAllFileLines().size() != 0){
			lex.nextToken().printToken();
			
		}
	}
}
