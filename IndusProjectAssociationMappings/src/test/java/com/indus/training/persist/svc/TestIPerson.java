package com.indus.training.persist.svc;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.indus.training.dao.IIPerson;
import com.indus.training.persist.dao.impl.IPersonManager;
import com.indus.training.persist.entity.ImmutablePerson;

import java.util.List;

public class TestIPerson {

    private IIPerson personManager;

    @Before
    public void setUp() throws Exception {
        personManager = new IPersonManager();
        // Optionally, you could prepopulate the database here
    }

    @After
    public void tearDown() throws Exception {
        // Clean up any created data
        List<ImmutablePerson> persons = personManager.listAll();
        for (ImmutablePerson person : persons) {
            personManager.delete(person.getId());
        }
    }

    @Test
    public void testCreateAndReadPerson() throws Exception {
        ImmutablePerson person = new ImmutablePerson(1L, "Alice", 30);
        boolean created = personManager.create(person);
        assertTrue("Person should be created", created);

        ImmutablePerson retrievedPerson = personManager.read(1L);
        assertNotNull("Retrieved person should not be null", retrievedPerson);
        assertEquals("Alice", retrievedPerson.getName());
        assertEquals(30, retrievedPerson.getAge());
    }

    @Test
    public void testUpdatePerson() {
        ImmutablePerson person = new ImmutablePerson(2L, "Bob", 25);
        try {
            personManager.create(person);
            // Attempting to update should throw an exception since ImmutablePerson cannot be updated
            personManager.update(person);
            fail("Should have thrown an exception due to immutability");
        } catch (UnsupportedOperationException e) {
            assertEquals("ImmutablePerson cannot be updated.", e.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void testDeletePerson() throws Exception {
        ImmutablePerson person = new ImmutablePerson(3L, "Charlie", 28);
        personManager.create(person);

        boolean deleted = personManager.delete(3L);
        assertTrue("Person should be deleted", deleted);

        ImmutablePerson retrievedPerson = personManager.read(3L);
        assertNull("Retrieved person should be null after deletion", retrievedPerson);
    }

    @Test
    public void testListAllPersons() throws Exception {
        personManager.create(new ImmutablePerson(4L, "David", 22));
        personManager.create(new ImmutablePerson(5L, "Eve", 26));

        List<ImmutablePerson> persons = personManager.listAll();
        assertFalse("List should not be empty", persons.isEmpty());
        assertEquals(2, persons.size());
    }
}
