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
public class NameTest {

    @InjectMocks
    private NameService nameService;

    @Test
    public void testGetName_Success() {
        // Arrange
        String expectedName = "John Doe";
        nameService.setName(expectedName);

        // Act
        String actualName = nameService.getName();

        // Assert
        assertEquals(expectedName, actualName, "The name should be equal to the expected name");
    }

    @Test
    public void testGetName_NullName() {
        // Arrange
        nameService.setName(null);

        // Act
        String actualName = nameService.getName();

        // Assert
        assertEquals(null, actualName, "The name should be null");
    }

    @Test
    public void testGetName_EmptyName() {
        // Arrange
        nameService.setName("");

        // Act
        String actualName = nameService.getName();

        // Assert
        assertEquals("", actualName, "The name should be an empty string");
    }

    @Test
    public void testGetName_LongName() {
        // Arrange
        String longName = "ThisIsAVeryLongNameThatExceedsTheMaximumAllowedLengthForAName";
        assertThrows(IllegalArgumentException.class, () -> nameService.setName(longName), "The name should throw an IllegalArgumentException");
    }
}

