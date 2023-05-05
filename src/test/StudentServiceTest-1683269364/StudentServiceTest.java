import java.util.ArrayList;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private ArrayList<Student> studentList;

    private Student student1;
    private Student student2;

    @BeforeEach
    public void setUp() {
        student1 = new Student(1, "John Doe");
        student2 = new Student(2, "Jane Doe");
    }

    @Test
    public void testIsPresent_success() {
        when(studentList.get(0)).thenReturn(student1);
        when(studentList.get(1)).thenReturn(student2);
        when(studentList.size()).thenReturn(2);

        String result = studentService.isPresent(1);

        assertEquals("Student with ID 1 is present", result);
    }

    @Test
    public void testIsPresent_failure() {
        when(studentList.get(0)).thenReturn(student1);
        when(studentList.get(1)).thenReturn(student2);
        when(studentList.size()).thenReturn(2);

        String result = studentService.isPresent(3);

        assertEquals("Student with ID 3 is not present", result);
    }

    @Test
    public void testIsPresent_edgeCase_emptyList() {
        when(studentList.size()).thenReturn(0);

        String result = studentService.isPresent(1);

        assertEquals("Student with ID 1 is not present", result);
    }

    @Test
    public void testIsPresent_edgeCase_invalidId() {
        when(studentList.get(0)).thenReturn(student1);
        when(studentList.get(1)).thenReturn(student2);
        when(studentList.size()).thenReturn(2);

        String result = studentService.isPresent(-1);

        assertEquals("Invalid student ID", result);
    }
}

