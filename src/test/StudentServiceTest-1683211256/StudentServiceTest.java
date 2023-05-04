import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private List<Student> studentList;

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testIsPresent_success() {
        Student student1 = new Student(1, "John Doe");
        Student student2 = new Student(2, "Jane Doe");

        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);

        when(studentList.iterator()).thenReturn(students.iterator());

        String input = "1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        studentService.isPresent();

        assertEquals("1", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testIsPresent_failure() {
        Student student1 = new Student(1, "John Doe");
        Student student2 = new Student(2, "Jane Doe");

        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);

        when(studentList.iterator()).thenReturn(students.iterator());

        String input = "3";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        studentService.isPresent();

        assertEquals("3", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testIsPresent_edgeCase() {
        Student student1 = new Student(1, "John Doe");
        Student student2 = new Student(2, "Jane Doe");

        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);

        when(studentList.iterator()).thenReturn(students.iterator());

        String input = "-1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        studentService.isPresent();

        assertEquals("-1", outputStreamCaptor.toString().trim());
    }
}

