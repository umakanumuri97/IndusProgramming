import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.indus.training.dao.IDepartment;
import com.indus.training.dao.IEmployee;
import com.indus.training.persist.dao.impl.DepartmentManager;
import com.indus.training.persist.dao.impl.EmployeeManager;
import com.indus.training.persist.entity.Department;
import com.indus.training.persist.entity.Employee;

public class EmployeeDepartmentTest {

    private IEmployee employeeManager;
    private IDepartment departmentManager;

    @Before
    public void setUp() throws Exception {
        // Initialize the DAO implementations
        employeeManager = new EmployeeManager();
        departmentManager = new DepartmentManager();
    }

    @After
    public void tearDown() throws Exception {
        // Clean up the test data (if needed)
        // Ensure that entities created during tests are cleaned up to avoid conflicts
        // Not implemented here, but you might want to use methods to delete the test data.
    }

    @Test
    public void testCreateEmployeeWithDepartment()  {
        try {
            // 1. Create a Department
            Department department = new Department();
            department.setName("CEO");
            
            // Persist Department
            Boolean departmentCreated = departmentManager.create(department);
            assertTrue("Department creation failed", departmentCreated);

            // 2. Create Employees
            Employee employee1 = new Employee();
            employee1.setName("Sumana Akkavarm");
            employee1.setDepartment(department);

            Employee employee2 = new Employee();
            employee2.setName("");
            employee2.setDepartment(department);

            // Persist Employees
            Boolean employee1Created = employeeManager.create(employee1);
            Boolean employee2Created = employeeManager.create(employee2);

            assertTrue("Employee 1 creation failed", employee1Created);
            assertTrue("Employee 2 creation failed", employee2Created);

            // 3. Verify the Employees are correctly associated with the Department
            Department retrievedDepartment = departmentManager.read(department.getId());
            assertNotNull("Retrieved department is null", retrievedDepartment);
            assertTrue("Department should have employees", retrievedDepartment.getEmployees().size() > 0);

            boolean employee1Found = retrievedDepartment.getEmployees().stream()
                .anyMatch(emp -> emp.getName().equals("Alice"));
            boolean employee2Found = retrievedDepartment.getEmployees().stream()
                .anyMatch(emp -> emp.getName().equals("Bob"));

            assertTrue("Employee Alice not found in department", employee1Found);
            assertTrue("Employee Bob not found in department", employee2Found);
        } catch (Exception e) {
            fail("Exception occurred during test: " + e.getMessage());
        }
    }
}
