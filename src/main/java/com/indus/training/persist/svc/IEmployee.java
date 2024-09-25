package com.indus.training.persist.svc;

import com.indus.training.persist.entity.Details;

public interface IEmployee {
	
	public Boolean insertEmployee(Details dtlObj);
	public Boolean updateEmployee(Details dtlObj) ;
	public Boolean deleteEmployee(String employeeID);
	public Details fetchEmployee(String employeeID);
}
