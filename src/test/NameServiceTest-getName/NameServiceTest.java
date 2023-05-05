import java.util.ArrayList;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class NameServiceTest {

    @InjectMocks
    private NameService nameService;

    @Mock
    private Scanner scanner;

    @BeforeEach
    public void setUp() {
        nameService = new NameService(scanner);
    }

    @Test
    public void testGetName_Success() {
        when(scanner.nextLine()).thenReturn("John Doe");

        String result = nameService.getName();

        assertEquals("John Doe", result);
    }

    @Test
    public void testGetName_EmptyName() {
        when(scanner.nextLine()).thenReturn("");

        assertThrows(IllegalArgumentException.class, () -> nameService.getName());
    }

    @Test
    public void testGetName_NullName() {
        when(scanner.nextLine()).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> nameService.getName());
    }

    @Test
    public void testGetName_WhiteSpaceName() {
        when(scanner.nextLine()).thenReturn("   ");

        assertThrows(IllegalArgumentException.class, () -> nameService.getName());
    }
}

