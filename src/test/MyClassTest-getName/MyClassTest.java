import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MyClassTest {

    @InjectMocks
    private MyClass myClass;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetName_success() {
        // Arrange
        String expectedName = "John Doe";
        myClass.setName(expectedName);

        // Act
        String actualName = myClass.getName();

        // Assert
        assertEquals(expectedName, actualName, "The name should be equal to the expected name");
    }

    @Test
    public void testGetName_emptyString() {
        // Arrange
        String expectedName = "";
        myClass.setName(expectedName);

        // Act
        String actualName = myClass.getName();

        // Assert
        assertEquals(expectedName, actualName, "The name should be an empty string");
    }

    @Test
    public void testGetName_nullValue() {
        // Arrange
        myClass.setName(null);

        // Act
        String actualName = myClass.getName();

        // Assert
        assertEquals(null, actualName, "The name should be null");
    }
}
