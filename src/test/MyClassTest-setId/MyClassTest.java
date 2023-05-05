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
public class MyClassTest {

    @InjectMocks
    private MyClass myClass;

    @BeforeEach
    public void setUp() {
        myClass = new MyClass();
    }

    @Test
    public void testSetId_positive() {
        int id = 1;
        myClass.setId(id);
        assertEquals(id, myClass.getId(), "The id should be set correctly.");
    }

    @Test
    public void testSetId_negative() {
        int id = -1;
        assertThrows(IllegalArgumentException.class, () -> myClass.setId(id), "The id should not be negative.");
    }

    @Test
    public void testSetId_zero() {
        int id = 0;
        myClass.setId(id);
        assertEquals(id, myClass.getId(), "The id should be set correctly.");
    }

    @Test
    public void testSetId_maxValue() {
        int id = Integer.MAX_VALUE;
        myClass.setId(id);
        assertEquals(id, myClass.getId(), "The id should be set correctly.");
    }

    @Test
    public void testSetId_minValue() {
        int id = Integer.MIN_VALUE;
        assertThrows(IllegalArgumentException.class, () -> myClass.setId(id), "The id should not be Integer.MIN_VALUE.");
    }
}

