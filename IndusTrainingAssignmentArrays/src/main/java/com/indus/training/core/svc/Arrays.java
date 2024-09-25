package com.indus.training.core.svc;

import com.indus.training.core.domain.Array2Input;
import com.indus.training.core.domain.Array2Output;
import com.indus.training.core.impl.IArrays;

public class Arrays implements IArrays {

	@Override
	public Array2Output add(Array2Input ArrayInObj) {
		
		Array2Output arrayOutObj=null;
		
		
		int[][] matrix1=ArrayInObj.matrix1;
		int[][] matrix2=ArrayInObj.matrix2;
		int[][]resultmatrix=new int[2][2];
		
		resultmatrix[0][0] = matrix1[0][0] + matrix2[0][0];
		resultmatrix[0][1] = matrix1[0][1] + matrix2[0][1];
		resultmatrix[1][0] = matrix1[1][0] + matrix2[1][0];
		resultmatrix[1][1] = matrix1[1][1] + matrix2[1][1];

		
				arrayOutObj =new Array2Output();
		
		arrayOutObj.matrix1=matrix1;
		arrayOutObj.matrix2=matrix2;
		arrayOutObj.resultmatrix=resultmatrix;
		arrayOutObj.operation="add";
		
		
		
		return arrayOutObj;
	}

}
