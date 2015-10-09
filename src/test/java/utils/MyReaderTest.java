package utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import utils.MyReader;

public class MyReaderTest {

	@Test
	public void emptyness() {
		MyReader r = new MyReader("");
		assertTrue(r.isEmpty());
		
		r = new MyReader("a");
		assertFalse(r.isEmpty());
		r.read();		
		assertTrue(r.isEmpty());
	}
}
