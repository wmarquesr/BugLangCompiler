package tests;

import java.io.File;
import java.io.IOException;

import lexicalAnalyzer.LexicalAnalyzer;

public class Test {

	public static void main(String[] args) throws IOException {
		
		String path = new File("").getCanonicalPath();
		path+= "/ShellSort.bl";
		File file = new File(path);
		
		LexicalAnalyzer lex = new LexicalAnalyzer(file);
		
		while(lex.size() != 0){
			lex.nextToken().printToken();
			
		}
	}
}
