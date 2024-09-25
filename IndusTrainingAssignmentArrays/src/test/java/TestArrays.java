

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


//import java.lang.reflect.Array;


import com.indus.training.core.domain.Array2Input;
import com.indus.training.core.domain.Array2Output;
import com.indus.training.core.svc.Arrays;

import junit.framework.TestCase;

public class TestArrays extends TestCase {
	
	private Arrays arrayObj=null;

	protected void setUp() throws Exception {
		arrayObj=new Arrays();
		super.setUp();
	}

	protected void tearDown() throws Exception {
		arrayObj=null;
		super.tearDown();
	}

	public void testAdd() {
		//1.Input
		Array2Input arrayInObj=new Array2Input();
		
		arrayInObj.matrix1=new int[][] {{5,6},{2,3}};
		arrayInObj.matrix2=new int[][] {{2,3},{1,4}};
		//2.Expected Result
		Array2Output expResult= new Array2Output();
		expResult.matrix1=new int[][]{{5,6},{2,3}};
		expResult.matrix2=new int[][] {{2,3},{1,4}};
		expResult.resultmatrix=new int[][] {{7,9},{3,7}};
		expResult.operation="add";
		//3.Actual result
		Array2Output actResult=arrayObj.add(arrayInObj);
		//4.Assertion
		assertArrayEquals(expResult.matrix1,actResult.matrix1);
		assertArrayEquals(expResult.matrix2,actResult.matrix2);
		assertArrayEquals(expResult.resultmatrix,actResult.resultmatrix);
		assertEquals(expResult.operation,actResult.operation);
		
		
		
		
		
		
	}

}
