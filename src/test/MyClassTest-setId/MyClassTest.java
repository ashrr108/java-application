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

    @BeforeEach
    public void setUp() {
        myClass = new MyClass();
    }

    @Test
    public void testSetId_success() {
        // Arrange
        int expectedId = 1;

        // Act
        myClass.setId(expectedId);
        int actualId = myClass.getId();

        // Assert
        assertEquals(expectedId, actualId, "The ID should be set correctly.");
    }

    @Test
    public void testSetId_negativeValue() {
        // Arrange
        int invalidId = -1;

        // Act
        myClass.setId(invalidId);
        int actualId = myClass.getId();

        // Assert
        assertEquals(0, actualId, "The ID should not be set to a negative value.");
    }

    @Test
    public void testSetId_maxIntegerValue() {
        // Arrange
        int maxId = Integer.MAX_VALUE;

        // Act
        myClass.setId(maxId);
        int actualId = myClass.getId();

        // Assert
        assertEquals(maxId, actualId, "The ID should be set to the maximum integer value.");
    }

    @Test
    public void testSetId_minIntegerValue() {
        // Arrange
        int minId = Integer.MIN_VALUE;

        // Act
        myClass.setId(minId);
        int actualId = myClass.getId();

        // Assert
        assertEquals(0, actualId, "The ID should not be set to the minimum integer value.");
    }
}
