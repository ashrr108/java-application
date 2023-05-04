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

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private List<Student> studentList;

    private Student student1;
    private Student student2;

    @BeforeEach
    public void setUp() {
        student1 = new Student(1, "John Doe", 25);
        student2 = new Student(2, "Jane Doe", 22);
    }

    @Test
    public void testFetchStudent_success() {
        when(studentList.stream().filter(any())).thenReturn(Stream.of(student1));

        Student result = studentService.fetchStudent(1);

        assertEquals(student1, result);
        verify(studentList, times(1)).stream();
    }

    @Test
    public void testFetchStudent_failure() {
        when(studentList.stream().filter(any())).thenReturn(Stream.empty());

        Student result = studentService.fetchStudent(3);

        assertEquals(null, result);
        verify(studentList, times(1)).stream();
    }

    @Test
    public void testFetchStudent_edgeCase_emptyList() {
        when(studentList.stream().filter(any())).thenReturn(Stream.empty());

        Student result = studentService.fetchStudent(1);

        assertEquals(null, result);
        verify(studentList, times(1)).stream();
    }

    @Test
    public void testFetchStudent_edgeCase_invalidId() {
        when(studentList.stream().filter(any())).thenReturn(Stream.empty());

        Student result = studentService.fetchStudent(-1);

        assertEquals(null, result);
        verify(studentList, times(1)).stream();
    }
}
