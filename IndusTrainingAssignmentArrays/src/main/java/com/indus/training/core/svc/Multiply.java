package com.indus.training.core.svc;

import com.indus.training.core.domain.MultiplyInput;
import com.indus.training.core.domain.MultiplyOutput;
import com.indus.training.core.impl.IMultiply;

public class Multiply implements IMultiply {

	@Override
	public MultiplyOutput mul(MultiplyInput mulInObj) {
		MultiplyOutput mulOutObj = null;
		{

			int[][] matrix1 = mulInObj.matrix1;
			int[][] matrix2 = mulInObj.matrix2;
			int n = matrix1.length;
			int[][] result = new int[n][n];

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					result[i][j] = 0;
					for (int k = 0; k < n; k++) {
						result[i][j] += matrix1[i][k] * matrix2[k][j];
					}

				}
			}
			mulOutObj = new MultiplyOutput();

			mulOutObj.matrix1 = matrix1;
			mulOutObj.matrix2 = matrix2;
			mulOutObj.result = result;
			mulOutObj.operation = "mul";

			// TODO Auto-generated method stub
			return mulOutObj;
		}
	}
}
