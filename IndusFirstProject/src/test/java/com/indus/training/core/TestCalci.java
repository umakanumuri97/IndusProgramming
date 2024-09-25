package com.indus.training.core;

import junit.framework.TestCase;

public class TestCalci extends TestCase {
	private Calci calObj = null;

	protected void setUp() throws Exception {
		calObj = new Calci();
		System.out.println("TestCalci:setUp");
	}

	protected void tearDown() throws Exception {
		calObj = null;
		System.out.println("TestCalci:tearDown");
	}

	public void testAdditionScenario1() {
		System.out.println("TestCalci:testAdditionScenario1");
		// 1. Inputs
		double param1 = 10.0;
		double param2 = 20.0;

		// 2. Expected Result
		double expResult = 30.0;

		// 3. Actual Result
		double actualResult = calObj.addition(param1, param2);

		// 4. Assertion
		assertEquals(expResult, actualResult, 0);
	}

	public void testAdditionScenario2() {
		System.out.println("TestCalci:testAdditionScenario2");
		// 1. Inputs
		double param1 = 20.0;
		double param2 = 10.0;

		// 2. Expected Result
		double expResult = 30.0;

		// 3. Actual Result
		double actualResult = calObj.addition(param1, param2);

		// 4. Assertion
		assertEquals(expResult, actualResult, 0);
	}

	public void testSubtractionScenario1() {
		System.out.println("TestCalci:testSubtractionScenario1");
		// 1. Inputs
		double param1 = 20.0;
		double param2 = 10.0;

		// 2. Expected Result
		double expResult = 10.0;

		// 3. Actual Result
		double actualResult = calObj.subtraction(param1, param2);

		// 4. Assertion
		assertEquals(expResult, actualResult, 0);
	}

	public void testSubtractionScenario2() {
		System.out.println("TestCalci:testSubtractionScenario2");
		// 1. Inputs
		double param1 = 20.0;
		double param2 = 5.0;

		// 2. Expected Result
		double expResult = 15.0;

		// 3. Actual Result
		double actualResult = calObj.subtraction(param1, param2);

		// 4. Assertion
		assertEquals(expResult, actualResult, 0);
	}

	public void testMultiplicationScenario1() {
		System.out.println("TestCalci:testMultiplicationScenario1");
		// 1. Inputs
		double param1 = 2.0;
		double param2 = 3.0;

		// 2. Expected Result
		double expResult = 6.0;

		// 3. Actual Result
		double actualResult = calObj.multiplication(param1, param2);

		// 4. Assertion
		assertEquals(expResult, actualResult, 0);
	}

	public void testMultiplicationScenario2() {
		System.out.println("TestCalci:testMultiplicationScenario2");
		// 1. Inputs
		double param1 = 2.0;
		double param2 = 4.0;

		// 2. Expected Result
		double expResult = 8.0;

		// 3. Actual Result
		double actualResult = calObj.multiplication(param1, param2);

		// 4. Assertion
		assertEquals(expResult, actualResult, 0);
	}

	public void testDivisionScenario1() {
		System.out.println("TestCalci:testDivisionScenario1");
		// 1. Inputs
		double param1 = 6.0;
		double param2 = 2.0;

		// 2. Expected Result
		double expResult = 3.0;

		// 3. Actual Result
		double actualResult = calObj.division(param1, param2);

		// 4. Assertion
		assertEquals(expResult, actualResult, 0);
	}

	public void testDivisionScenario2() {
		System.out.println("TestCalci:testDivisionScenario2");
		// 1. Inputs
		double param1 = 6.0;
		double param2 = 1.0;

		// 2. Expected Result
		double expResult = 6.0;

		// 3. Actual Result
		double actualResult = calObj.division(param1, param2);

		// 4. Assertion
		assertEquals(expResult, actualResult, 0);
	}

}
