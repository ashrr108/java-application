import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    private List<Student> studentList;
    private Student student1;
    private Student student2;

    @BeforeEach
    public void setUp() {
        studentList = new ArrayList<>();
        student1 = new Student(1, "John Doe", 25);
        student2 = new Student(2, "Jane Doe", 22);
        studentList.add(student1);
        studentList.add(student2);
    }

    @Test
    public void testUpdateStudent_success() {
        int id = 1;
        String newName = "John Smith";
        int newAge = 26;

        when(studentRepository.findById(id)).thenReturn(Optional.of(student1));

        studentService.updateStudent(id, newName, newAge);

        assertEquals(newName, student1.getName());
        assertEquals(newAge, student1.getAge());
        verify(studentRepository, times(1)).findById(id);
        verify(studentRepository, times(1)).save(student1);
    }

    @Test
    public void testUpdateStudent_studentNotFound() {
        int id = 3;
        String newName = "John Smith";
        int newAge = 26;

        when(studentRepository.findById(id)).thenReturn(Optional.empty());

        studentService.updateStudent(id, newName, newAge);

        verify(studentRepository, times(1)).findById(id);
        verify(studentRepository, times(0)).save(any(Student.class));
    }

    @Test
    public void testUpdateStudent_invalidAge() {
        int id = 1;
        String newName = "John Smith";
        int newAge = -1;

        when(studentRepository.findById(id)).thenReturn(Optional.of(student1));

        studentService.updateStudent(id, newName, newAge);

        assertEquals("John Doe", student1.getName());
        assertEquals(25, student1.getAge());
        verify(studentRepository, times(1)).findById(id);
        verify(studentRepository, times(0)).save(student1);
    }
}
