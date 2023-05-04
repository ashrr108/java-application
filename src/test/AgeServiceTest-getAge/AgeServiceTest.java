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
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AgeServiceTest {

    @InjectMocks
    private AgeService ageService;

    @Mock
    private Scanner scanner;

    @BeforeEach
    public void setUp() {
        ageService = new AgeService(scanner);
    }

    @Test
    public void testGetAge_success() {
        int expectedAge = 25;
        when(scanner.nextInt()).thenReturn(expectedAge);

        int actualAge = ageService.getAge();

        assertEquals(expectedAge, actualAge, "The age should be equal to the expected age");
    }

    @Test
    public void testGetAge_negativeAge() {
        int negativeAge = -5;
        when(scanner.nextInt()).thenReturn(negativeAge);

        int actualAge = ageService.getAge();

        assertEquals(0, actualAge, "The age should be 0 when a negative age is entered");
    }

    @Test
    public void testGetAge_ageBoundary() {
        int maxAge = Integer.MAX_VALUE;
        when(scanner.nextInt()).thenReturn(maxAge);

        int actualAge = ageService.getAge();

        assertEquals(maxAge, actualAge, "The age should be equal to the maximum integer value");
    }
}

