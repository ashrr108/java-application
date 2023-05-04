import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
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
    public void testListStudent_success() {
        when(studentList.size()).thenReturn(2);
        when(studentList.get(0)).thenReturn(student1);
        when(studentList.get(1)).thenReturn(student2);

        studentService.listStudent();

        verify(studentList, times(1)).size();
        verify(studentList, times(1)).get(0);
        verify(studentList, times(1)).get(1);
    }

    @Test
    public void testListStudent_emptyList() {
        when(studentList.size()).thenReturn(0);

        studentService.listStudent();

        verify(studentList, times(1)).size();
        verify(studentList, never()).get(anyInt());
    }

    @Test
    public void testListStudent_nullList() {
        studentList = null;

        studentService.listStudent();

        verify(studentList, never()).size();
        verify(studentList, never()).get(anyInt());
    }

    @Test
    public void testListStudent_edgeCase() {
        when(studentList.size()).thenReturn(1);
        when(studentList.get(0)).thenReturn(student1);

        studentService.listStudent();

        verify(studentList, times(1)).size();
        verify(studentList, times(1)).get(0);
        verify(studentList, never()).get(1);
    }
}
