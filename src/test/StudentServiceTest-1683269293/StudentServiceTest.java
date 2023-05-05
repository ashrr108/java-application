import java.util.ArrayList;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private Scanner input;

    private ArrayList<Student> studentList;

    @BeforeEach
    public void setUp() {
        studentList = new ArrayList<>();
        studentList.add(new Student(1, "John Doe", 25));
        studentList.add(new Student(2, "Jane Doe", 22));
        studentList.add(new Student(3, "Jim Brown", 30));
        studentService.setStudentList(studentList);
    }

    @Test
    public void testFetchStudent_success() {
        when(input.nextInt()).thenReturn(1);
        Student result = studentService.fetchStudent();
        assertEquals(1, result.getId());
        assertEquals("John Doe", result.getName());
        assertEquals(25, result.getAge());
    }

    @Test
    public void testFetchStudent_failure() {
        when(input.nextInt()).thenReturn(99);
        Student result = studentService.fetchStudent();
        assertEquals(null, result);
    }

    @Test
    public void testFetchStudent_edgeCase_emptyList() {
        studentService.setStudentList(new ArrayList<>());
        when(input.nextInt()).thenReturn(1);
        Student result = studentService.fetchStudent();
        assertEquals(null, result);
    }

    @Test
    public void testFetchStudent_edgeCase_negativeId() {
        when(input.nextInt()).thenReturn(-1);
        Student result = studentService.fetchStudent();
        assertEquals(null, result);
    }
}

