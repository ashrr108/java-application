import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Scanner;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class NameTest {

    @InjectMocks
    private NameService nameService;

    @Test
    public void setName_success() {
        // Arrange
        String expectedName = "John Doe";

        // Act
        nameService.setName(expectedName);

        // Assert
        assertEquals(expectedName, nameService.getName());
    }

    @Test
    public void setName_nullName() {
        // Arrange
        String nullName = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> nameService.setName(nullName));
    }

    @Test
    public void setName_emptyName() {
        // Arrange
        String emptyName = "";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> nameService.setName(emptyName));
    }

    @Test
    public void setName_whitespaceName() {
        // Arrange
        String whitespaceName = " ";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> nameService.setName(whitespaceName));
    }
}

