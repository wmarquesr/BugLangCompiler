package tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import sun.misc.IOUtils;
import lexicalAnalyzer.*;

public class Test {

	public static void main(String[] args) throws IOException {
		
		File f = new File("C:\\Users\\W\\Desktop\\testfile.bl");
		FileInputStream fis = new FileInputStream(f);
		BufferedReader br = new BufferedReader(new FileReader(f));
		
		String[] s = "Separate this shit".split(" ");
		
		LexicalAnalyzer lex = new LexicalAnalyzer(f);
		
		/*int index = br.readLine().length();
		
		for (int i = 0; i < index; i++) {
			lex.nextToken().printToken();
		}*/
		
		lex.nextToken().printToken();
		lex.nextToken().printToken();
		lex.nextToken().printToken();
		lex.nextToken().printToken();
		lex.nextToken().printToken();
		lex.nextToken().printToken();
		lex.nextToken().printToken();
	}

}
