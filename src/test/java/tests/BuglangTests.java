package tests;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;

import lexicalAnalyzer.LexicalAnalyzer;

public class BuglangTests {

	@Test
	public void testShell() {
		try {
			URI source = ClassLoader.getSystemResource("ShellSort.bl").toURI();
			LexicalAnalyzer la = new LexicalAnalyzer(new File(source));
			
			URI sourceRes = ClassLoader.getSystemResource("shellresult").toURI();
			BufferedReader res = new BufferedReader(new FileReader(sourceRes.getPath()));
			
			String line = res.readLine();
			while (la.size() != 0) {
				assertEquals(line, la.nextToken().toString());
				line = res.readLine();
			}
			
			res.close();
		} catch (URISyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}