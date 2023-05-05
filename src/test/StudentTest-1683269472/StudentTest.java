import java.util.ArrayList;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class StudentTest {

    @InjectMocks
    private Student student;

    @Mock
    private ArrayList<Student> students;

    @Mock
    private Scanner scanner;

    @BeforeEach
    public void setUp() {
        student = new Student(1, "John Doe", 25);
    }

    @Test
    public void testStudentConstructor_success() {
        assertEquals(1, student.getId());
        assertEquals("John Doe", student.getName());
        assertEquals(25, student.getAge());
    }

    @Test
    public void testStudentConstructor_negativeAge() {
        assertThrows(IllegalArgumentException.class, () -> new Student(1, "John Doe", -1));
    }

    @Test
    public void testStudentConstructor_nullName() {
        assertThrows(NullPointerException.class, () -> new Student(1, null, 25));
    }

    @Test
    public void testStudentConstructor_emptyName() {
        assertThrows(IllegalArgumentException.class, () -> new Student(1, "", 25));
    }

    @Test
    public void testStudentConstructor_invalidId() {
        assertThrows(IllegalArgumentException.class, () -> new Student(-1, "John Doe", 25));
    }

    @Test
    public void testAddStudent_success() {
        when(students.add(student)).thenReturn(true);
        assertEquals(true, students.add(student));
    }

    @Test
    public void testAddStudent_failure() {
        when(students.add(null)).thenReturn(false);
        assertEquals(false, students.add(null));
    }

    @Test
    public void testGetStudentById_success() {
        when(students.get(0)).thenReturn(student);
        assertEquals(student, students.get(0));
    }

    @Test
    public void testGetStudentById_failure() {
        when(students.get(1)).thenReturn(null);
        assertEquals(null, students.get(1));
    }

    @Test
    public void testUpdateStudent_success() {
        Student updatedStudent = new Student(1, "Jane Doe", 26);
        when(students.set(0, updatedStudent)).thenReturn(student);
        assertEquals(student, students.set(0, updatedStudent));
    }

    @Test
    public void testUpdateStudent_failure() {
        when(students.set(1, null)).thenReturn(null);
        assertEquals(null, students.set(1, null));
    }

    @Test
    public void testRemoveStudent_success() {
        when(students.remove(0)).thenReturn(student);
        assertEquals(student, students.remove(0));
    }

    @Test
    public void testRemoveStudent_failure() {
        when(students.remove(1)).thenReturn(null);
        assertEquals(null, students.remove(1));
    }
}

