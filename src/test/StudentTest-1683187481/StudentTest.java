import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class StudentTest {

    @InjectMocks
    private Student student;

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
    public void testConstructor_negativeId() {
        assertThrows(IllegalArgumentException.class, () -> new Student(-1, "John Doe", 25));
    }

    @Test
    public void testConstructor_nullName() {
        assertThrows(NullPointerException.class, () -> new Student(1, null, 25));
    }

    @Test
    public void testConstructor_emptyName() {
        assertThrows(IllegalArgumentException.class, () -> new Student(1, "", 25));
    }

    @Test
    public void testConstructor_negativeAge() {
        assertThrows(IllegalArgumentException.class, () -> new Student(1, "John Doe", -1));
    }

    @Test
    public void testConstructor_ageBoundary() {
        assertThrows(IllegalArgumentException.class, () -> new Student(1, "John Doe", 150));
    }
}

