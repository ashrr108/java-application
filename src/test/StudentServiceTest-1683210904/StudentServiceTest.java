import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
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
        student1 = new Student(1, "John", 25);
        student2 = new Student(2, "Jane", 22);

        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);

        when(studentList.iterator()).thenReturn(students.iterator());
    }

    @Test
    public void listStudent_Success() {
        studentService.listStudent();

        verify(studentList, times(1)).iterator();
    }

    @Test
    public void listStudent_EmptyList() {
        when(studentList.iterator()).thenReturn(new ArrayList<Student>().iterator());

        studentService.listStudent();

        verify(studentList, times(1)).iterator();
    }

    @Test
    public void listStudent_NullList() {
        when(studentList.iterator()).thenReturn(null);

        studentService.listStudent();

        verify(studentList, times(1)).iterator();
    }
}

