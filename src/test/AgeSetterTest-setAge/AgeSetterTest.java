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
public class AgeSetterTest {

    @InjectMocks
    private AgeSetter ageSetter;

    @Test
    public void testSetAge_success() {
        // Arrange
        int expectedAge = 25;

        // Act
        ageSetter.setAge(expectedAge);
        int actualAge = ageSetter.getAge();

        // Assert
        assertEquals(expectedAge, actualAge, "The age should be set correctly.");
    }

    @Test
    public void testSetAge_negativeAge() {
        // Arrange
        int invalidAge = -5;

        // Act
        ageSetter.setAge(invalidAge);
        int actualAge = ageSetter.getAge();

        // Assert
        assertEquals(0, actualAge, "The age should not be set to a negative value.");
    }

    @Test
    public void testSetAge_edgeCase_minValue() {
        // Arrange
        int minValue = Integer.MIN_VALUE;

        // Act
        ageSetter.setAge(minValue);
        int actualAge = ageSetter.getAge();

        // Assert
        assertEquals(0, actualAge, "The age should not be set to the minimum integer value.");
    }

    @Test
    public void testSetAge_edgeCase_maxValue() {
        // Arrange
        int maxValue = Integer.MAX_VALUE;

        // Act
        ageSetter.setAge(maxValue);
        int actualAge = ageSetter.getAge();

        // Assert
        assertEquals(maxValue, actualAge, "The age should be set to the maximum integer value.");
    }
}

