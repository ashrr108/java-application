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

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> myClass.setId(invalidId), "Setting a negative ID should throw an exception.");
    }

    @Test
    public void testSetId_maxValue() {
        // Arrange
        int maxId = Integer.MAX_VALUE;

        // Act
        myClass.setId(maxId);
        int actualId = myClass.getId();

        // Assert
        assertEquals(maxId, actualId, "The ID should be set correctly when using the maximum integer value.");
    }

    @Test
    public void testSetId_minValue() {
        // Arrange
        int minId = Integer.MIN_VALUE;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> myClass.setId(minId), "Setting the minimum integer value as ID should throw an exception.");
    }
}

