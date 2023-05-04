import java.util.ArrayList;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FetchStudentTest {

    private ArrayList<Student> studentList;
    private Scanner input;
    private Student student1;
    private Student student2;

    @BeforeEach
    public void setUp() {
        studentList = new ArrayList<>();
        input = Mockito.mock(Scanner.class);
        student1 = new Student(1, "John Doe", 20);
        student2 = new Student(2, "Jane Doe", 22);
        studentList.add(student1);
        studentList.add(student2);
    }

    @Test
    public void testFetchStudent_success() {
        when(input.nextInt()).thenReturn(1);
        String expectedOutput = "1 John Doe 20";
        String actualOutput = fetchStudent(input, studentList);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testFetchStudent_failure() {
        when(input.nextInt()).thenReturn(3);
        String expectedOutput = "Student not found";
        String actualOutput = fetchStudent(input, studentList);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testFetchStudent_edgeCase() {
        when(input.nextInt()).thenReturn(Integer.MAX_VALUE);
        String expectedOutput = "Student not found";
        String actualOutput = fetchStudent(input, studentList);
        assertEquals(expectedOutput, actualOutput);
    }

    public String fetchStudent(Scanner input, ArrayList<Student> studentList) {
        System.out.println();
        int id = input.nextInt();
        for (Student student : studentList) {
            if (student.getId() == id) {
                String result = student.getId() + " " + student.getName() + " " + student.getAge();
                System.out.println(result);
                return result;
            }
        }
        System.out.println("Student not found");
        return "Student not found";
    }
}

