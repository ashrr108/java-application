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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private ArrayList<Student> studentList;

    @Mock
    private Scanner input;

    private Student student1;
    private Student student2;

    @BeforeEach
    public void setUp() {
        student1 = new Student(1, "John Doe", 25);
        student2 = new Student(2, "Jane Doe", 22);
        studentList.add(student1);
        studentList.add(student2);
    }

    @Test
    public void testUpdateStudentSuccess() {
        when(input.nextInt()).thenReturn(1).thenReturn(28);
        when(input.next()).thenReturn("John Smith");
        when(studentList.size()).thenReturn(2);
        when(studentList.get(anyInt())).thenReturn(student1).thenReturn(student2);

        studentService.updateStudent();

        assertEquals("John Smith", student1.getName());
        assertEquals(28, student1.getAge());
        verify(input, times(2)).nextInt();
        verify(input, times(1)).next();
    }

    @Test
    public void testUpdateStudentNotFound() {
        when(input.nextInt()).thenReturn(3);
        when(studentList.size()).thenReturn(2);
        when(studentList.get(anyInt())).thenReturn(student1).thenReturn(student2);

        studentService.updateStudent();

        assertEquals("John Doe", student1.getName());
        assertEquals(25, student1.getAge());
        verify(input, times(1)).nextInt();
        verify(input, times(0)).next();
    }

    @Test
    public void testUpdateStudentInvalidInput() {
        when(input.nextInt()).thenThrow(new IllegalArgumentException("Invalid input"));
        when(studentList.size()).thenReturn(2);
        when(studentList.get(anyInt())).thenReturn(student1).thenReturn(student2);

        try {
            studentService.updateStudent();
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid input", e.getMessage());
        }

        assertEquals("John Doe", student1.getName());
        assertEquals(25, student1.getAge());
        verify(input, times(1)).nextInt();
        verify(input, times(0)).next();
    }
}

