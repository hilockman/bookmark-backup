package org.study;

import java.util.Random;

import org.jblas.DoubleMatrix;
import org.junit.Test;

public class MatrixTest {


	static double [][] array = new double[3000][3000];
	static double [][] array1 = new double[3000][3000];
	
	static {
		
		Utils.fillMatrix(array);		
		
		Utils.fillMatrix(array1);
	}
	
	
	@Test
	public void testMultiply() {

		
		DoubleMatrix m =  new DoubleMatrix(array);
			
		DoubleMatrix m1 =  new DoubleMatrix(array1);
		
		DoubleMatrix m3 = m.mmul(m1);
	}
}
