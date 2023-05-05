import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private List<Student> studentList;

    @Mock
    private Scanner scanner;

    private Student student1;
    private Student student2;

    @BeforeEach
    public void setUp() {
        student1 = new Student(1, "John Doe");
        student2 = new Student(2, "Jane Doe");

        studentList.add(student1);
        studentList.add(student2);
    }

    @Test
    public void testDeleteStudent_success() {
        when(scanner.nextInt()).thenReturn(1);

        studentService.deleteStudent();

        verify(studentList, times(1)).remove(0);
        assertEquals(1, studentList.size());
    }

    @Test
    public void testDeleteStudent_studentNotFound() {
        when(scanner.nextInt()).thenReturn(3);

        studentService.deleteStudent();

        verify(studentList, never()).remove(anyInt());
        assertEquals(2, studentList.size());
    }

    @Test
    public void testDeleteStudent_invalidId() {
        when(scanner.nextInt()).thenReturn(-1);

        studentService.deleteStudent();

        verify(studentList, never()).remove(anyInt());
        assertEquals(2, studentList.size());
    }

    @Test
    public void testDeleteStudent_emptyList() {
        studentList.clear();

        when(scanner.nextInt()).thenReturn(1);

        studentService.deleteStudent();

        verify(studentList, never()).remove(anyInt());
        assertEquals(0, studentList.size());
    }
}

