import java.util.ArrayList;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private ArrayList<Student> studentList;

    @BeforeEach
    public void setUp() {
        studentList = new ArrayList<>();
        studentList.add(new Student(1, "John", 20));
        studentList.add(new Student(2, "Jane", 22));
        studentList.add(new Student(3, "Doe", 19));
    }

    @Test
    public void testListStudent_success() {
        when(studentService.getStudentList()).thenReturn(studentList);

        studentService.listStudent();

        verify(studentService, times(1)).getStudentList();
        verifyNoMoreInteractions(studentService);
    }

    @Test
    public void testListStudent_emptyList() {
        when(studentService.getStudentList()).thenReturn(new ArrayList<>());

        studentService.listStudent();

        verify(studentService, times(1)).getStudentList();
        verifyNoMoreInteractions(studentService);
    }

    @Test
    public void testListStudent_nullList() {
        when(studentService.getStudentList()).thenReturn(null);

        assertThrows(NullPointerException.class, () -> studentService.listStudent());

        verify(studentService, times(1)).getStudentList();
        verifyNoMoreInteractions(studentService);
    }

    @Test
    public void testListStudent_invalidAge() {
        studentList.add(new Student(4, "Invalid", -1));
        when(studentService.getStudentList()).thenReturn(studentList);

        assertThrows(IllegalArgumentException.class, () -> studentService.listStudent());

        verify(studentService, times(1)).getStudentList();
        verifyNoMoreInteractions(studentService);
    }
}

