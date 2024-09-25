
package com.indus.training.dao;

	import com.indus.training.persist.entity.Student;

	public interface IStudent {

	    Boolean create(Student student) throws Exception;

	    Student read(int id) throws Exception;

	    Boolean delete(int id);

	    Student findByName(String name);

	    Boolean update(Student student);
	}



