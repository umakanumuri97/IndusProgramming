package com.indus.training.core.svc;

import com.indus.training.core.domain.Array3Input;
import com.indus.training.core.domain.Array3Output;
import com.indus.training.core.impl.IArrays3;

public class Arrays3 implements IArrays3 {

	@Override
	public Array3Output add(Array3Input arrInObj) {
		
		Array3Output arrOutObj=null;
		
		
		int[][] matrix1 = arrInObj.matrix1;
		int[][] matrix2 = arrInObj.matrix2;
		int[][] result = new int[3][3];

        result[0][0] = matrix1[0][0] + matrix2[0][0];
		result[0][1] = matrix1[0][1] + matrix2[0][1];
		result[0][2] = matrix1[0][2] + matrix2[0][2];
		result[1][0] = matrix1[1][0] + matrix2[1][0];
		result[1][1] = matrix1[1][1] + matrix2[1][1];
		result[1][2] = matrix1[1][2] + matrix2[1][2];
		result[2][0] = matrix1[2][0] + matrix2[2][0];
		result[2][1] = matrix1[2][1] + matrix2[2][1];
		result[2][2] = matrix1[2][2] + matrix2[2][2];
		
		arrOutObj=new Array3Output();
		arrOutObj.matrix1=matrix1;
		arrOutObj.matrix2=matrix2;
		arrOutObj.result=result;
		arrOutObj.operation="add";

		
		
		
		
		
		
		return arrOutObj;
	}

}
