package tests;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
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
			LexicalAnalyzer la = new LexicalAnalyzer(source);
			
			URI sourceRes = ClassLoader.getSystemResource("shellresult").toURI();
			BufferedReader res = new BufferedReader(new FileReader(sourceRes.getPath()));
			
			String line = res.readLine();
			while (la.hasNext()) {
				assertEquals(line, la.nextToken().toString());
				line = res.readLine();
			}
			
			la.close();
			res.close();
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSample1() {
		try {
			URI source = ClassLoader.getSystemResource("sample1.bl").toURI();
			LexicalAnalyzer la = new LexicalAnalyzer(source);
			
			URI sourceRes = ClassLoader.getSystemResource("sample1").toURI();
			BufferedReader res = new BufferedReader(new FileReader(sourceRes.getPath()));
			
			String line = res.readLine();
			while (la.hasNext()) {
				assertEquals(line, la.nextToken().toString());
				line = res.readLine();
			}
					
			la.close();
			res.close();
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}