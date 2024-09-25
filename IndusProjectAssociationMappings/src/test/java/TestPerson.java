import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.indus.training.dao.IPerson;
import com.indus.training.persist.dao.impl.PersonManager;
import com.indus.training.persist.entity.Name;
import com.indus.training.persist.entity.Person;

import java.util.Date;
import java.util.List;

public class TestPerson {

    private IPerson personManager;
    private Person testPerson;

    @Before
    public void setUp() throws Exception {
        personManager = new PersonManager();
        
        // Create a test Person
        testPerson = new Person();
        Name name = new Name();
        name.setFirst("Alice");
        name.setLast("Smith");
        name.setInitial('A');
        testPerson.setName(name);
        testPerson.setBirthday(new Date());

        // Persist the test Person
        personManager.create(testPerson);
    }

    @After
    public void tearDown() throws Exception {
        // Clean up test data
        personManager.delete(testPerson.getId());
    }

    @Test
    public void testCreatePerson() throws Exception {
        Person newPerson = new Person();
        Name newName = new Name();
        newName.setFirst("Bob");
        newName.setLast("Johnson");
        newName.setInitial('B');
        newPerson.setName(newName);
        newPerson.setBirthday(new Date());

        Boolean created = personManager.create(newPerson);
        assertTrue("Person creation failed", created);
    }

    @Test
    public void testReadPerson() throws Exception {
        Person retrievedPerson = personManager.read(testPerson.getId());
        assertNotNull("Retrieved person is null", retrievedPerson);
        assertEquals("First name should match", "Alice", retrievedPerson.getName().getFirst());
        assertEquals("Last name should match", "Smith", retrievedPerson.getName().getLast());
    }

    @Test
    public void testUpdatePerson() throws Exception {
        testPerson.getName().setFirst("Alice Updated");
        Boolean updated = personManager.update(testPerson);
        assertTrue("Person update failed", updated);

        // Verify the update
        Person retrievedPerson = personManager.read(testPerson.getId());
        assertEquals("First name should be updated", "Alice Updated", retrievedPerson.getName().getFirst());
    }

    @Test
    public void testDeletePerson() throws Exception {
        Long idToDelete = testPerson.getId();
        Boolean deleted = personManager.delete(idToDelete);
        assertTrue("Person deletion failed", deleted);
        
        // Verify the deletion
        Person retrievedPerson = personManager.read(idToDelete);
        assertNull("Person should be deleted", retrievedPerson);
    }

    @Test
    public void testListAllPersons() throws Exception {
        List<Person> persons = personManager.listAllPersons();
        assertNotNull("Person list should not be null", persons);
        assertTrue("Person list should contain at least one person", persons.size() >= 1);
    }
}
