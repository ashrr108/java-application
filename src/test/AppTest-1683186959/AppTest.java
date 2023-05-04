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
public class AppTest {

    @InjectMocks
    private App app;

    @Mock
    private ArrayList<Student> studentList;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testAddStudent() {
        String input = "1\nJohn Doe\n12345\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        app.main(new String[]{});

        verify(studentList, times(1)).add(any(Student.class));
        assertEquals("Student added successfully.\n", outContent.toString());
    }

    @Test
    public void testUpdateStudent() {
        String input = "2\n12345\nJane Doe\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        app.main(new String[]{});

        verify(studentList, times(1)).set(anyInt(), any(Student.class));
        assertEquals("Student updated successfully.\n", outContent.toString());
    }

    @Test
    public void testDeleteStudent() {
        String input = "3\n12345\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        app.main(new String[]{});

        verify(studentList, times(1)).remove(anyInt());
        assertEquals("Student deleted successfully.\n", outContent.toString());
    }

    @Test
    public void testFetchStudent() {
        String input = "4\n12345\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        app.main(new String[]{});

        verify(studentList, times(1)).get(anyInt());
        assertEquals("Student fetched successfully.\n", outContent.toString());
    }

    @Test
    public void testIsPresent() {
        String input = "5\n12345\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        app.main(new String[]{});

        verify(studentList, times(1)).contains(any(Student.class));
        assertEquals("Student presence checked.\n", outContent.toString());
    }

    @Test
    public void testListStudent() {
        String input = "6\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        app.main(new String[]{});

        verify(studentList, times(1)).size();
        assertEquals("Student list displayed.\n", outContent.toString());
    }

    @Test
    public void testExit() {
        String input = "7\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        try {
            app.main(new String[]{});
        } catch (Exception e) {
            assertEquals("System exit called.", e.getMessage());
        }
    }

    @Test
    public void testInvalidInput() {
        String input = "0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        app.main(new String[]{});

        assertEquals("Invalid input.\n", outContent.toString());
    }
}
