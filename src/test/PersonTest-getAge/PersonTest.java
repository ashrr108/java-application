import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PersonTest {

    @InjectMocks
    private Person person;

    @BeforeEach
    public void setUp() {
        person.setAge(25);
    }

    @Test
    public void testGetAge_success() {
        int expectedAge = 25;
        int actualAge = person.getAge();
        assertEquals(expectedAge, actualAge, "The age should be 25");
    }

    @Test
    public void testGetAge_failure() {
        int unexpectedAge = 30;
        int actualAge = person.getAge();
        assertNotEquals(unexpectedAge, actualAge, "The age should not be 30");
    }

    @Test
    public void testGetAge_edgeCase_negativeAge() {
        person.setAge(-5);
        int expectedAge = 0;
        int actualAge = person.getAge();
        assertEquals(expectedAge, actualAge, "The age should be 0 for negative input");
    }

    @Test
    public void testGetAge_edgeCase_maxInteger() {
        person.setAge(Integer.MAX_VALUE);
        int expectedAge = Integer.MAX_VALUE;
        int actualAge = person.getAge();
        assertEquals(expectedAge, actualAge, "The age should be equal to Integer.MAX_VALUE");
    }
}
