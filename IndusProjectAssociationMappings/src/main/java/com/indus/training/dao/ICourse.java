package com.indus.training.dao;

import com.indus.training.persist.entity.Course;



public interface ICourse {

	Boolean create(Course course) throws Exception;

	Course read(Long id) throws Exception;

	Boolean delete(int id);

	Course findByName(String title);

	Boolean update(Course course);
}
