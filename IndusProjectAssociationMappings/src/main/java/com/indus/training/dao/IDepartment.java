package com.indus.training.dao;

import com.indus.training.persist.entity.Department;

public interface IDepartment {

	public Boolean create(Department department)throws Exception;

	public Department read(int id);

	public Boolean delete(int id);

	Department findByName(String name);

	Boolean update(Department department);

}
