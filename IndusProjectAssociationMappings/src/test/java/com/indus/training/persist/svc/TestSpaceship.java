package com.indus.training.persist.svc;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.indus.training.persist.dao.impl.SpaceshipManager;
import com.indus.training.persist.entity.Spaceship;

import java.util.List;

public class TestSpaceship {
    private SpaceshipManager manager;

    @Before
    public void setUp() {
        manager = new SpaceshipManager();
    }

    @After
    public void tearDown() {
        // Clean up any test data if necessary
    }

    @Test
    public void testCreate() {
        Spaceship ship = new Spaceship();
        ship.setName("USS Enterprise");
        ship.setModel("NCC-1701");
        ship.setSpeed(1000.0);
        
        assertTrue(manager.create(ship));
    }

    @Test
    public void testRead() {
        Spaceship ship = new Spaceship();
        ship.setName("USS Voyager");
        ship.setModel("NCC-74656");
        ship.setSpeed(800.0);
        manager.create(ship);

        Spaceship foundShip = manager.read(ship.getId());
        assertNotNull(foundShip);
        assertEquals("USS Voyager", foundShip.getName());
    }

    @Test
    public void testUpdate() {
        Spaceship ship = new Spaceship();
        ship.setName("USS Defiant");
        ship.setModel("NX-74205");
        ship.setSpeed(900.0);
        manager.create(ship);

        ship.setSpeed(950.0);
        assertTrue(manager.update(ship));

        Spaceship updatedShip = manager.read(ship.getId());
        assertEquals(950.0, updatedShip.getSpeed(), 0);
    }

    @Test
    public void testDelete() {
        Spaceship ship = new Spaceship();
        ship.setName("USS Discovery");
        ship.setModel("NCC-1031");
        ship.setSpeed(1200.0);
        manager.create(ship);

        assertTrue(manager.delete(ship.getId()));
        assertNull(manager.read(ship.getId()));
    }

    @Test
    public void testGetAllSpaceShips() {
        List<Spaceship> ships = manager.getAllSpaceShips();
        
    }
}
