import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AgeTest {

    @InjectMocks
    private Person person;

    @BeforeEach
    public void setUp() {
        person = new Person();
    }

    @Test
    public void testGetAge_success() {
        person.setAge(25);
        int age = person.getAge();
        assertEquals(25, age, "The age should be 25");
    }

    @Test
    public void testGetAge_negativeAge() {
        person.setAge(-5);
        assertThrows(IllegalArgumentException.class, () -> person.getAge(), "Age cannot be negative");
    }

    @Test
    public void testGetAge_ageBoundary() {
        person.setAge(Integer.MAX_VALUE);
        int age = person.getAge();
        assertEquals(Integer.MAX_VALUE, age, "The age should be equal to Integer.MAX_VALUE");
    }

    @Test
    public void testGetAge_zeroAge() {
        person.setAge(0);
        int age = person.getAge();
        assertEquals(0, age, "The age should be 0");
    }
}

