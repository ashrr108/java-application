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
    private Scanner scanner;

    private List<Student> studentList;

    @BeforeEach
    public void setUp() {
        studentList = new ArrayList<>();
        studentList.add(new Student(1, "John Doe"));
        studentList.add(new Student(2, "Jane Doe"));
        studentService.setStudentList(studentList);
    }

    @Test
    public void testDeleteStudent_success() {
        when(scanner.nextInt()).thenReturn(1);

        studentService.deleteStudent();

        assertEquals(1, studentList.size());
        assertEquals(2, studentList.get(0).getId());
    }

    @Test
    public void testDeleteStudent_failure() {
        when(scanner.nextInt()).thenReturn(3);

        studentService.deleteStudent();

        assertEquals(2, studentList.size());
    }

    @Test
    public void testDeleteStudent_edgeCase_emptyList() {
        studentList.clear();
        when(scanner.nextInt()).thenReturn(1);

        studentService.deleteStudent();

        assertEquals(0, studentList.size());
    }

    @Test
    public void testDeleteStudent_edgeCase_negativeId() {
        when(scanner.nextInt()).thenReturn(-1);

        studentService.deleteStudent();

        assertEquals(2, studentList.size());
    }
}

