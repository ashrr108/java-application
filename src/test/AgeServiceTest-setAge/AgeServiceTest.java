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
public class AgeServiceTest {

    @InjectMocks
    private AgeService ageService;

    @Test
    public void testSetAge_success() {
        // Arrange
        int age = 25;

        // Act
        ageService.setAge(age);

        // Assert
        assertEquals(age, ageService.getAge(), "The age should be set correctly.");
    }

    @Test
    public void testSetAge_negativeAge() {
        // Arrange
        int age = -5;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> ageService.setAge(age), "Setting a negative age should throw an exception.");
    }

    @Test
    public void testSetAge_ageBoundary() {
        // Arrange
        int age = 150;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> ageService.setAge(age), "Setting an age above the boundary should throw an exception.");
    }

}

