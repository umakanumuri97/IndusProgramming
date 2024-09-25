import static org.junit.Assert.assertArrayEquals;

import com.indus.training.core.domain.Array3Input;
import com.indus.training.core.domain.Array3Output;
import com.indus.training.core.svc.Arrays3;

import junit.framework.TestCase;

public class TestArrays3 extends TestCase {
	private Arrays3 arrObj=null;
	

	protected void setUp() throws Exception {
		arrObj=new Arrays3();
		super.setUp();
	}

	protected void tearDown() throws Exception {
		arrObj=null;
		super.tearDown();
	}

	public void testAdd() {
		//1.Input
		Array3Input arrInObj=new Array3Input();
		arrInObj.matrix1=new int[][] {{2,3,2},{2,3,4},{1,3,4}};
		arrInObj.matrix2=new int[][] {{2,3,2},{2,3,4},{1,3,4}};
		//2.Expected Result
		Array3Output expResult=new Array3Output();
		expResult.matrix1=new int[][] {{2,3,2},{2,3,4},{1,3,4}};
		expResult.matrix2=new int[][] {{2,3,2},{2,3,4},{1,3,4}};
		expResult.result=new int[][] {{4,6,4},{4,6,8},{2,6,8}};
		expResult.operation="add";
		//3.ACtual result
		Array3Output actResult=arrObj.add(arrInObj);
		//4.Assertion
		assertArrayEquals(expResult.matrix1,actResult.matrix1);
		assertArrayEquals(expResult.matrix2,actResult.matrix2);
		assertArrayEquals(expResult.result,actResult.result);
		assertEquals(expResult.operation,actResult.operation);
		
		
		
		
		
		
	}

}
