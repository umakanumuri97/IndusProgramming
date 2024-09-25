package com.indus.training.dao;

import java.util.List;

import com.indus.training.persist.entity.Spaceship;

public interface ISpaceship {
	boolean create(Spaceship spaceShip);

	Spaceship read(Long id);

	boolean update(Spaceship spaceShip);

	boolean delete(Long id);

	List<Spaceship> getAllSpaceShips();
}
