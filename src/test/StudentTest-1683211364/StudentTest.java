import java.util.ArrayList;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class StudentTest {

    @InjectMocks
    private Student student;

    @Mock
    private Scanner scanner;

    @BeforeEach
    public void setUp() {
        student = new Student(1, "John Doe", 25);
    }

    @Test
    public void testConstructor_success() {
        assertEquals(1, student.getId());
        assertEquals("John Doe", student.getName());
        assertEquals(25, student.getAge());
    }

    @Test
    public void testConstructor_failure() {
        assertThrows(IllegalArgumentException.class, () -> new Student(-1, "John Doe", 25));
        assertThrows(IllegalArgumentException.class, () -> new Student(1, "", 25));
        assertThrows(IllegalArgumentException.class, () -> new Student(1, "John Doe", -1));
    }

    @Test
    public void testConstructor_edgeCases() {
        assertThrows(IllegalArgumentException.class, () -> new Student(Integer.MAX_VALUE, "John Doe", 25));
        assertThrows(IllegalArgumentException.class, () -> new Student(1, null, 25));
        assertThrows(IllegalArgumentException.class, () -> new Student(1, "John Doe", Integer.MAX_VALUE));
    }

    @Test
    public void testScannerInput_success() {
        when(scanner.nextInt()).thenReturn(1).thenReturn(25);
        when(scanner.nextLine()).thenReturn("John Doe");

        Student studentFromScanner = new Student(scanner.nextInt(), scanner.nextLine(), scanner.nextInt());

        assertEquals(1, studentFromScanner.getId());
        assertEquals("John Doe", studentFromScanner.getName());
        assertEquals(25, studentFromScanner.getAge());
    }

    @Test
    public void testScannerInput_failure() {
        when(scanner.nextInt()).thenReturn(-1).thenReturn(25);
        when(scanner.nextLine()).thenReturn("John Doe");

        assertThrows(IllegalArgumentException.class, () -> new Student(scanner.nextInt(), scanner.nextLine(), scanner.nextInt()));
    }

    @Test
    public void testScannerInput_edgeCases() {
        when(scanner.nextInt()).thenReturn(Integer.MAX_VALUE).thenReturn(25);
        when(scanner.nextLine()).thenReturn("John Doe");

        assertThrows(IllegalArgumentException.class, () -> new Student(scanner.nextInt(), scanner.nextLine(), scanner.nextInt()));
    }
}

