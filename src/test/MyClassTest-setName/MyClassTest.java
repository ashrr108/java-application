import org.junit.jupiter.api.BeforeEach;
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

    private String name;

    @BeforeEach
    public void setUp() {
        name = "John Doe";
    }

    @Test
    public void testSetName_success() {
        myClass.setName(name);
        assertEquals(name, myClass.getName(), "Name should be set successfully");
    }

    @Test
    public void testSetName_nullName() {
        assertThrows(NullPointerException.class, () -> myClass.setName(null), "Setting null name should throw NullPointerException");
    }

    @Test
    public void testSetName_emptyName() {
        assertThrows(IllegalArgumentException.class, () -> myClass.setName(""), "Setting empty name should throw IllegalArgumentException");
    }

    @Test
    public void testSetName_nameWithSpecialCharacters() {
        String specialCharName = "John@Doe";
        myClass.setName(specialCharName);
        assertEquals(specialCharName, myClass.getName(), "Name with special characters should be set successfully");
    }

    @Test
    public void testSetName_nameWithNumbers() {
        String numericName = "John123";
        myClass.setName(numericName);
        assertEquals(numericName, myClass.getName(), "Name with numbers should be set successfully");
    }
}

