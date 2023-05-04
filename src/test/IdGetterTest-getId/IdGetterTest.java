import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class IdGetterTest {

    @InjectMocks
    private MyClass myClass;

    @Test
    public void testGetId_success() {
        // Arrange
        int expectedId = 1;
        myClass.setId(expectedId);

        // Act
        int actualId = myClass.getId();

        // Assert
        assertEquals(expectedId, actualId, "The returned ID should match the expected ID");
    }

    @Test
    public void testGetId_failure() {
        // Arrange
        int unexpectedId = 2;
        myClass.setId(1);

        // Act
        int actualId = myClass.getId();

        // Assert
        assertNotEquals(unexpectedId, actualId, "The returned ID should not match the unexpected ID");
    }

    @Test
    public void testGetId_edgeCase_minValue() {
        // Arrange
        int expectedId = Integer.MIN_VALUE;
        myClass.setId(expectedId);

        // Act
        int actualId = myClass.getId();

        // Assert
        assertEquals(expectedId, actualId, "The returned ID should match the expected ID for the minimum integer value");
    }

    @Test
    public void testGetId_edgeCase_maxValue() {
        // Arrange
        int expectedId = Integer.MAX_VALUE;
        myClass.setId(expectedId);

        // Act
        int actualId = myClass.getId();

        // Assert
        assertEquals(expectedId, actualId, "The returned ID should match the expected ID for the maximum integer value");
    }
}

