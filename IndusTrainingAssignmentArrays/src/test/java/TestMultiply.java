import static org.junit.Assert.assertArrayEquals;

import com.indus.training.core.domain.MultiplyInput;
import com.indus.training.core.domain.MultiplyOutput;
import com.indus.training.core.svc.Multiply;

import junit.framework.TestCase;

public class TestMultiply extends TestCase {
	private Multiply mulObj=null;

	protected void setUp() throws Exception {
		mulObj=new Multiply();
		super.setUp();
	}

	protected void tearDown() throws Exception {
		mulObj=null;
		super.tearDown();
	}

	public void testMul() {
		
		//1.input
		MultiplyInput mulInObj=new MultiplyInput();
		mulInObj.matrix1=new int[][] {{2,3},{4,5}};
		mulInObj.matrix2=new int[][] {{3,4},{2,3}};
		//2.Expected Result
		MultiplyOutput expResult=new MultiplyOutput();
		expResult.matrix1=new int[][] {{2,3},{4,5}};
		expResult.matrix2=new int[][] {{3,4},{2,3}};
		expResult.result=new int[][] {{12,17},{22,31}};
		expResult.operation="mul";
		//3.ACtual result
		MultiplyOutput actResult=mulObj.mul(mulInObj);
		//4.Assertion
		assertArrayEquals(expResult.matrix1,actResult.matrix1);
		assertArrayEquals(expResult.matrix2,actResult.matrix2);
		assertEquals(expResult.operation,actResult.operation);
		assertArrayEquals(expResult.result,expResult.result);
		
	
	}

}
