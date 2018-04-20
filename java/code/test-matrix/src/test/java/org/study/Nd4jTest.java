package org.study;

import org.junit.Test;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

public class Nd4jTest {
	static double [][] array = new double[3000][3000];
	static double [][] array1 = new double[3000][3000];
	
	static {
		
		Utils.fillMatrix(array);		
		
		Utils.fillMatrix(array1);
	}
	
	
	@Test
	public void testMultiply() {
		
		INDArray m = Nd4j.create(array);
		INDArray m1 = Nd4j.create(array1);
		INDArray m3 = m.mmul(m1);
	}
}
