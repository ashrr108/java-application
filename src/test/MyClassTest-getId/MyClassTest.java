import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class MyClassTest {

    @InjectMocks
    private MyClass myClass;

    private int id;

    @BeforeEach
    public void setUp() {
        id = 1;
        myClass.setId(id);
    }

    @Test
    public void testGetId_success() {
        int result = myClass.getId();
        assertEquals(id, result, "The id should match the expected value");
    }

    @Test
    public void testGetId_failure() {
        int invalidId = 2;
        int result = myClass.getId();
        assertEquals(id, result, "The id should not match the invalid value");
    }

    @Test
    public void testGetId_edgeCase_minValue() {
        int minValue = Integer.MIN_VALUE;
        myClass.setId(minValue);
        int result = myClass.getId();
        assertEquals(minValue, result, "The id should match the minimum integer value");
    }

    @Test
    public void testGetId_edgeCase_maxValue() {
        int maxValue = Integer.MAX_VALUE;
        myClass.setId(maxValue);
        int result = myClass.getId();
        assertEquals(maxValue, result, "The id should match the maximum integer value");
    }
}
