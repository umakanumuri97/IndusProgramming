package com.indus.training.core;

/**
 * Description:Performs Addition, Subtraction, Multiplication and Division of
 * two double values
 */

public class Calci {

	/**
	 * Description: Performs Addition of two double values
	 * 
	 * @param param1
	 * @param param2
	 * @return
	 */

	public double addition(double param1, double param2) {
		double result = 0.0;
		{
			result = param1 + param2;
		}
		return result;

	}

	/**
	 * Description:Performs Subtraction of two double values
	 * 
	 * @param param1
	 * @param param2
	 * @return
	 */

	public double subtraction(double param1, double param2) {
		double result = 0.0;
		{
			result = param1 - param2;
		}
		return result;

	}

	/**
	 * Description:Performs multiplication of two double values
	 * 
	 * @param param1
	 * @param param2
	 * @return
	 */

	public double multiplication(double param1, double param2) {
		double result = 0.0;
		{
			result = param1 * param2;
		}
		return result;

	}

	/**
	 * Description: Performs division between two double values
	 * 
	 * @param param1
	 * @param param2
	 * @return
	 */

	public double division(double param1, double param2) {
		double result = 0.0;
		{
			result = param1 / param2;
		}
		return result;

	}

}
