import static org.junit.Assert.assertArrayEquals;

import com.indus.training.core.domain.AdditionInput;
import com.indus.training.core.domain.AdditionOutput;
import com.indus.training.core.svc.Addition;

import junit.framework.TestCase;

public class TestAddition extends TestCase {
	private Addition addObj=null;

	protected void setUp() throws Exception {
		addObj=new Addition();
		super.setUp();
	}

	protected void tearDown() throws Exception {
		addObj=null;
		super.tearDown();
	}

	public void testAdd() {
		//1.Input
		AdditionInput addInObj=new AdditionInput();
		addInObj.matrix1=new int[][] {{2,3,2},{3,4,2},{2,3,4}};
		addInObj.matrix2=new int[][] {{2,3,2},{3,4,2},{2,3,4}};
		//2.Expected Result
		AdditionOutput expResult=new AdditionOutput();
		expResult.matrix1=new int[][] {{2,3,2},{3,4,2},{2,3,4}};
		expResult.matrix2=new int[][] {{2,3,2},{3,4,2},{2,3,4}};
		expResult.result=new int[][] {{4,6,4},{6,8,4},{8,10,10}};
		expResult.operation="add";
		//3.Actual result
		AdditionOutput actResult=addObj.add(addInObj);
		//4.Assertion
		assertArrayEquals(expResult.matrix1,actResult.matrix1);
		assertArrayEquals(expResult.matrix2,actResult.matrix2);
		assertArrayEquals(expResult.result,actResult.result);
		assertEquals(expResult.operation,actResult.operation);
		
		

	}

}
