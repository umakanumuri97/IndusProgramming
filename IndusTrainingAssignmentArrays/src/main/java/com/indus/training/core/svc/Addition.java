package com.indus.training.core.svc;

import com.indus.training.core.domain.AdditionInput;
import com.indus.training.core.domain.AdditionOutput;
import com.indus.training.core.impl.IAddition;

public class Addition implements IAddition {

	@Override
	public AdditionOutput add(AdditionInput addInObj) {
		AdditionOutput addOutObj = null;
		{
			int[][] matrix1 = addInObj.matrix1;
			int[][] matrix2 = addInObj.matrix2;

			int[][] result = new int[3][3];

			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {

					result[i][j] = matrix1[i][j] + matrix2[i][j];
				}
			}

			addOutObj = new AdditionOutput();

			addOutObj.matrix1 = matrix1;
			addOutObj.matrix2 = matrix2;
			addOutObj.result = result;
			addOutObj.operation = "add";

			// TODO Auto-generated method stub
			return addOutObj;
		}

	}
}
