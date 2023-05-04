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
import java.util.Scanner;

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
        String input = "a\n1\nJohn Doe\n25";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        studentManager.addStudent();

        assertEquals("Invalid input. Please enter a valid ID.\n", outputStreamCaptor.toString());
    }

    @Test
    public void testAddStudentInvalidAge() {
        String input = "1\nJohn Doe\na\n25";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        studentManager.addStudent();

        assertEquals("Invalid input. Please enter a valid age.\n", outputStreamCaptor.toString());
    }

    @Test
    public void testAddStudentEdgeCaseMaxInt() {
        String input = Integer.MAX_VALUE + "\nJohn Doe\n25";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        studentManager.addStudent();

        verify(studentList, times(1)).add(new Student(Integer.MAX_VALUE, "John Doe", 25));
        assertEquals("\n\n\n", outputStreamCaptor.toString());
    }

    @Test
    public void testAddStudentEdgeCaseMinInt() {
        String input = Integer.MIN_VALUE + "\nJohn Doe\n25";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        studentManager.addStudent();

        verify(studentList, times(1)).add(new Student(Integer.MIN_VALUE, "John Doe", 25));
        assertEquals("\n\n\n", outputStreamCaptor.toString());
    }
}

