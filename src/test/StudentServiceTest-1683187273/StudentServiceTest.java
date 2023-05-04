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
        student1 = new Student(1, "John Doe");
        student2 = new Student(2, "Jane Doe");
    }

    @Test
    public void testDeleteStudent_success() {
        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);

        when(studentList.size()).thenReturn(students.size());
        when(studentList.get(0)).thenReturn(student1);
        when(studentList.get(1)).thenReturn(student2);

        studentService.deleteStudent(1);

        verify(studentList, times(1)).remove(0);
        assertEquals(1, students.size());
    }

    @Test
    public void testDeleteStudent_failure() {
        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);

        when(studentList.size()).thenReturn(students.size());
        when(studentList.get(0)).thenReturn(student1);
        when(studentList.get(1)).thenReturn(student2);

        studentService.deleteStudent(3);

        verify(studentList, never()).remove(anyInt());
        assertEquals(2, students.size());
    }

    @Test
    public void testDeleteStudent_edgeCase_emptyList() {
        when(studentList.size()).thenReturn(0);

        studentService.deleteStudent(1);

        verify(studentList, never()).remove(anyInt());
    }
}
