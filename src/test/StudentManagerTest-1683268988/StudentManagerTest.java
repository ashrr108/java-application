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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class StudentManagerTest {

    @InjectMocks
    private StudentManager studentManager;

    @Mock
    private ArrayList<Student> studentList;

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testAddStudentSuccess() {
        String input = "1\nJohn Doe\n25";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        studentManager.addStudent();

        verify(studentList, times(1)).add(new Student(1, "John Doe", 25));
        assertEquals("\n\n\n", outputStreamCaptor.toString());
    }

    @Test
    public void testAddStudentInvalidId() {
        String input = "invalid\n1\nJohn Doe\n25";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        studentManager.addStudent();

        verify(studentList, never()).add(any(Student.class));
        assertEquals("\n\n\n", outputStreamCaptor.toString());
    }

    @Test
    public void testAddStudentInvalidAge() {
        String input = "1\nJohn Doe\ninvalid\n25";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        studentManager.addStudent();

        verify(studentList, never()).add(any(Student.class));
        assertEquals("\n\n\n", outputStreamCaptor.toString());
    }

    @Test
    public void testAddStudentEdgeCaseMaxInt() {
        String input = "2147483647\nJohn Doe\n2147483647";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        studentManager.addStudent();

        verify(studentList, times(1)).add(new Student(2147483647, "John Doe", 2147483647));
        assertEquals("\n\n\n", outputStreamCaptor.toString());
    }

    @Test
    public void testAddStudentEdgeCaseMinInt() {
        String input = "-2147483648\nJohn Doe\n-2147483648";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        studentManager.addStudent();

        verify(studentList, times(1)).add(new Student(-2147483648, "John Doe", -2147483648));
        assertEquals("\n\n\n", outputStreamCaptor.toString());
    }
}

