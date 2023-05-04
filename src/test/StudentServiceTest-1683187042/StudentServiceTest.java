import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private List<Student> studentList;

    private Student student;
    private int id;
    private String name;
    private int age;

    @BeforeEach
    public void setUp() {
        id = 1;
        name = "John Doe";
        age = 20;
        student = new Student(id, name, age);
    }

    @Test
    public void testAddStudent_success() {
        studentService.addStudent(id, name, age);

        verify(studentList, times(1)).add(student);
    }

    @Test
    public void testAddStudent_invalidId() {
        int invalidId = -1;

        studentService.addStudent(invalidId, name, age);

        verify(studentList, never()).add(any(Student.class));
    }

    @Test
    public void testAddStudent_emptyName() {
        String emptyName = "";

        studentService.addStudent(id, emptyName, age);

        verify(studentList, never()).add(any(Student.class));
    }

    @Test
    public void testAddStudent_invalidAge() {
        int invalidAge = -1;

        studentService.addStudent(id, name, invalidAge);

        verify(studentList, never()).add(any(Student.class));
    }

    @Test
    public void testAddStudent_edgeCaseMaxInt() {
        int maxIntId = Integer.MAX_VALUE;
        int maxIntAge = Integer.MAX_VALUE;

        studentService.addStudent(maxIntId, name, maxIntAge);

        verify(studentList, times(1)).add(any(Student.class));
    }

    @Test
    public void testAddStudent_edgeCaseMinInt() {
        int minIntId = Integer.MIN_VALUE;
        int minIntAge = Integer.MIN_VALUE;

        studentService.addStudent(minIntId, name, minIntAge);

        verify(studentList, never()).add(any(Student.class));
    }
}
