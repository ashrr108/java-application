import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class IsPresentTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    private List<Student> studentList;

    @BeforeEach
    public void setUp() {
        studentList = new ArrayList<>();
        studentList.add(new Student(1, "John Doe"));
        studentList.add(new Student(2, "Jane Doe"));
        when(studentRepository.findAll()).thenReturn(studentList);
    }

    @Test
    public void testIsPresent_success() {
        InputStream sysInBackup = System.in;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        ByteArrayInputStream input = new ByteArrayInputStream("1\n".getBytes());
        System.setIn(input);

        studentService.isPresent();

        assertEquals("Student with ID 1 is present.\n", output.toString());
        System.setIn(sysInBackup);
    }

    @Test
    public void testIsPresent_failure() {
        InputStream sysInBackup = System.in;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        ByteArrayInputStream input = new ByteArrayInputStream("3\n".getBytes());
        System.setIn(input);

        studentService.isPresent();

        assertEquals("Student with ID 3 is not present.\n", output.toString());
        System.setIn(sysInBackup);
    }

    @Test
    public void testIsPresent_edgeCase() {
        InputStream sysInBackup = System.in;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        ByteArrayInputStream input = new ByteArrayInputStream("-1\n".getBytes());
        System.setIn(input);

        studentService.isPresent();

        assertEquals("Invalid ID. Please enter a valid ID.\n", output.toString());
        System.setIn(sysInBackup);
    }
}
