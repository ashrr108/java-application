import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

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
    public void setName_validName_success() {
        // Arrange
        String expectedName = "John Doe";

        // Act
        myClass.setName(expectedName);

        // Assert
        assertEquals(expectedName, myClass.getName(), "The name should be set successfully.");
    }

    @Test
    public void setName_nullName_throwsIllegalArgumentException() {
        // Arrange
        String nullName = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> myClass.setName(nullName), "Setting a null name should throw an IllegalArgumentException.");
    }

    @Test
    public void setName_emptyName_throwsIllegalArgumentException() {
        // Arrange
        String emptyName = "";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> myClass.setName(emptyName), "Setting an empty name should throw an IllegalArgumentException.");
    }

    @Test
    public void setName_whitespaceName_throwsIllegalArgumentException() {
        // Arrange
        String whitespaceName = " ";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> myClass.setName(whitespaceName), "Setting a whitespace name should throw an IllegalArgumentException.");
    }
}
