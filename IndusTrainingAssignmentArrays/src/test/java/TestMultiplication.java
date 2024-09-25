//import com.indus.training.core.domain.MultiplicationOutput;
import static org.junit.Assert.assertArrayEquals;

import com.indus.training.core.domain.MultiplicationInput;
import com.indus.training.core.domain.MultiplicationOutput;
import com.indus.training.core.svc.Multiplication;

import junit.framework.TestCase;

public class TestMultiplication extends TestCase {
	private Multiplication mulObj=null;

	protected void setUp() throws Exception {
		mulObj=new Multiplication();
		super.setUp();
	}

	protected void tearDown() throws Exception {
		mulObj=null;
		super.tearDown();
	}

	public void testMul() {
		//1.Input
		
		MultiplicationInput mulInObj=new MultiplicationInput();
		mulInObj.matrix1=new int[][]{{2,3},{3,4}};
		mulInObj.matrix2=new int[][] {{3,4},{3,4}};
		//2.Expected Result
	MultiplicationOutput expResult=new MultiplicationOutput();
	expResult.matrix1=new int[][] {{2,3},{3,4}};
	expResult.matrix2=new int[][] {{3,4},{3,4}};
	expResult.result=new int[][] {{15,20},{21,28}};
	expResult.operation="mul";
	//3.Actual result
	MultiplicationOutput actResult=mulObj.mul(mulInObj);
	//4.Assertion
	assertArrayEquals(expResult.matrix1,actResult.matrix1);
	assertArrayEquals(expResult.matrix2,actResult.matrix2);
	assertArrayEquals(expResult.result,actResult.result);
	assertEquals(expResult.operation,actResult.operation);
	
	
		
	}

}
