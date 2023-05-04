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
public class AgeServiceTest {

    @InjectMocks
    private AgeService ageService;

    @BeforeEach
    public void setUp() {
        ageService = new AgeService();
    }

    @Test
    public void testSetAge_success() {
        int age = 25;
        ageService.setAge(age);
        assertEquals(age, ageService.getAge(), "The age should be set successfully.");
    }

    @Test
    public void testSetAge_negativeAge() {
        int age = -5;
        assertThrows(IllegalArgumentException.class, () -> ageService.setAge(age), "Setting a negative age should throw an exception.");
    }

    @Test
    public void testSetAge_zeroAge() {
        int age = 0;
        ageService.setAge(age);
        assertEquals(age, ageService.getAge(), "The age should be set to zero successfully.");
    }

    @Test
    public void testSetAge_maxIntegerAge() {
        int age = Integer.MAX_VALUE;
        ageService.setAge(age);
        assertEquals(age, ageService.getAge(), "The age should be set to the maximum integer value successfully.");
    }
}
