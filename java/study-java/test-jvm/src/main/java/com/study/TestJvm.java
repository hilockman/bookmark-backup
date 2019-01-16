package com.study;

import java.util.ArrayList;
import java.util.List;

public class TestJvm {

	
	public static class BytesTest {
		public BytesTest() {
			byte [] buffers = new byte[1024 * 64 * 54];
		}
		
	}
	public static void main(String[] args) throws InterruptedException {
		List<BytesTest> bufferList = new ArrayList<BytesTest>();
		for (int i = 0 ; i < 500; i++) {
			bufferList.add(new BytesTest());
		}
		
		
		Thread.sleep(50000);
		
	}

}
