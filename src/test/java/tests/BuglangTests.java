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
	public void testEspacos() {
		try {
			URI source = ClassLoader.getSystemResource("espacos.bl").toURI();
			LexicalAnalyzer la = new LexicalAnalyzer(source);
			
			URI sourceRes = ClassLoader.getSystemResource("espacosresult").toURI();
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
	public void testJuntos() {
		URI source;
		try {
			source = ClassLoader.getSystemResource("juntos.bl").toURI();
			LexicalAnalyzer la = new LexicalAnalyzer(source);
		
			URI sourceRes = ClassLoader.getSystemResource("juntosresult").toURI();
			BufferedReader res = new BufferedReader(new FileReader(sourceRes.getPath()));
			
			String line = res.readLine();
			while (la.hasNext()) {
				assertEquals(line, la.nextToken().toString());
				line = res.readLine();
			}
			
			res.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testBothShell() {
		try {
			URI source = ClassLoader.getSystemResource("ShellSort.bl").toURI();
			LexicalAnalyzer la = new LexicalAnalyzer(source);
		
			URI source2 = ClassLoader.getSystemResource("ShellSortJunto.bl").toURI();
			LexicalAnalyzer la2 = new LexicalAnalyzer(source2);			
			
			while (la.hasNext())
				assertEquals(la.nextToken().toString(), la2.nextToken().toString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}