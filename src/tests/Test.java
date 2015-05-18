package tests;

import java.io.IOException;

import lexicalAnalyzer.LexicalAnalyzer;

public class Test {

	public static void main(String[] args) throws IOException {
		
		String file = "/home/guajar/Desktop/programacao/java/desktop/BugLangCompiler/testfile.bl";
		
		LexicalAnalyzer lex = new LexicalAnalyzer(file);
		
		int index = 7;
		for (int i = 0; i < index; i++) {
			lex.nextToken().printToken();
			System.out.println(lex.line);
		}
		
	}

}
