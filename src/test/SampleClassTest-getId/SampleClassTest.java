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
public class SampleClassTest {

    @InjectMocks
    private SampleClass sampleClass;

    private int id;

    @BeforeEach
    public void setUp() {
        id = 1;
        sampleClass.setId(id);
    }

    @Test
    public void testGetId_success() {
        int result = sampleClass.getId();
        assertEquals(id, result, "The returned id should match the expected value");
    }

    @Test
    public void testGetId_failure() {
        int invalidId = -1;
        sampleClass.setId(invalidId);
        assertThrows(IllegalArgumentException.class, () -> sampleClass.getId(), "The getId method should throw an exception for invalid id");
    }

    @Test
    public void testGetId_edgeCase_maxValue() {
        int maxId = Integer.MAX_VALUE;
        sampleClass.setId(maxId);
        int result = sampleClass.getId();
        assertEquals(maxId, result, "The returned id should match the maximum integer value");
    }

    @Test
    public void testGetId_edgeCase_minValue() {
        int minId = Integer.MIN_VALUE;
        sampleClass.setId(minId);
        int result = sampleClass.getId();
        assertEquals(minId, result, "The returned id should match the minimum integer value");
    }
}

