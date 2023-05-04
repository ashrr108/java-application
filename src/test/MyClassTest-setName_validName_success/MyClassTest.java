import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyClassTest {

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
        assertEquals(expectedName, myClass.getName(), "The name should be set correctly");
    }

    @Test
    public void setName_nullName_throwsIllegalArgumentException() {
        // Arrange
        String nullName = null;

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> myClass.setName(nullName), "Setting a null name should throw an IllegalArgumentException");
    }

    @Test
    public void setName_emptyName_throwsIllegalArgumentException() {
        // Arrange
        String emptyName = "";

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> myClass.setName(emptyName), "Setting an empty name should throw an IllegalArgumentException");
    }

    @Test
    public void setName_whitespaceName_throwsIllegalArgumentException() {
        // Arrange
        String whitespaceName = " ";

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> myClass.setName(whitespaceName), "Setting a whitespace name should throw an IllegalArgumentException");
    }
}
