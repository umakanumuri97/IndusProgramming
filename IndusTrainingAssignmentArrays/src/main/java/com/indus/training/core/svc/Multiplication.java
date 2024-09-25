package com.indus.training.core.svc;

import com.indus.training.core.domain.MultiplicationInput;
import com.indus.training.core.domain.MultiplicationOutput;
import com.indus.training.core.impl.IMultiplication;

public class Multiplication implements IMultiplication {

	@Override
	public MultiplicationOutput mul(MultiplicationInput MulInObj) {
		MultiplicationOutput mulOutObj = null;

		{
			int[][] matrix1 = MulInObj.matrix1;
			int[][] matrix2 = MulInObj.matrix2;
			int[][] result = new int[2][2];
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					// result[i][j] = 0;
					for (int k = 0; k < 2; k++) {
						result[i][j] += matrix1[i][k] * matrix2[k][j];

					}

				}
				mulOutObj = new MultiplicationOutput();

				mulOutObj.matrix1 = matrix1;
				mulOutObj.matrix2 = matrix2;
				mulOutObj.result = result;
				mulOutObj.operation = "mul";

			}

			// TODO Auto-generated method stub
			return mulOutObj;
		}
	}
}
