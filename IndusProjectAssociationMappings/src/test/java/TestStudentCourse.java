import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.indus.training.dao.ICourse;
import com.indus.training.dao.IStudent;
import com.indus.training.persist.dao.impl.CourseManager;
import com.indus.training.persist.dao.impl.StudentManager;
import com.indus.training.persist.entity.Course;
import com.indus.training.persist.entity.Student;

public class TestStudentCourse {

    private IStudent studentManager;
    private ICourse courseManager;
    private Course testCourse;

    @Before
    public void setUp() throws Exception {
        studentManager = new StudentManager();
        courseManager = new CourseManager();
        
        // Create a test course
        testCourse = new Course();
        testCourse.setTitle("Mathematics");
        courseManager.create(testCourse);
    }

    @After
    public void tearDown() throws Exception {
        // Clean up test data if needed (not shown here)
    }

    @Test
    public void testCreateStudentAndEnrollInCourse() throws Exception {
        // Create a Student
        Student student = new Student();
        student.setName("Alice");

        // Persist the Student
        boolean studentCreated = studentManager.create(student);
        assertTrue("Student creation failed", studentCreated);

        // Enroll the Student in the Course
        testCourse.getStudents().add(student);
        boolean courseUpdated = courseManager.update(testCourse);
        assertTrue("Course enrollment failed", courseUpdated);

        // Verify the Student is enrolled
        Course retrievedCourse = courseManager.read(testCourse.getId());
        assertNotNull("Retrieved course is null", retrievedCourse);
        assertTrue("Course should have students", retrievedCourse.getStudents().contains(student));
    }
    
}
