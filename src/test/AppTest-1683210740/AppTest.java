import java.util.ArrayList;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AppTest {

    @InjectMocks
    private App app;

    @Mock
    private Scanner input;

    @BeforeEach
    public void setUp() {
        App.studentList = new ArrayList<>();
    }

    @Test
    public void testAddStudent() {
        when(input.nextInt()).thenReturn(1).thenReturn(7);
        when(input.nextLine()).thenReturn("John Doe").thenReturn("12345");

        app.main(new String[]{});

        verify(input, times(2)).nextInt();
        verify(input, times(2)).nextLine();
        assert App.studentList.size() == 1;
        assert App.studentList.get(0).getName().equals("John Doe");
        assert App.studentList.get(0).getId().equals("12345");
    }

    @Test
    public void testUpdateStudent() {
        App.studentList.add(new Student("John Doe", "12345"));

        when(input.nextInt()).thenReturn(2).thenReturn(7);
        when(input.nextLine()).thenReturn("12345").thenReturn("Jane Doe");

        app.main(new String[]{});

        verify(input, times(2)).nextInt();
        verify(input, times(2)).nextLine();
        assert App.studentList.size() == 1;
        assert App.studentList.get(0).getName().equals("Jane Doe");
        assert App.studentList.get(0).getId().equals("12345");
    }

    @Test
    public void testDeleteStudent() {
        App.studentList.add(new Student("John Doe", "12345"));

        when(input.nextInt()).thenReturn(3).thenReturn(7);
        when(input.nextLine()).thenReturn("12345");

        app.main(new String[]{});

        verify(input, times(2)).nextInt();
        verify(input, times(1)).nextLine();
        assert App.studentList.isEmpty();
    }

    @Test
    public void testFetchStudent() {
        App.studentList.add(new Student("John Doe", "12345"));

        when(input.nextInt()).thenReturn(4).thenReturn(7);
        when(input.nextLine()).thenReturn("12345");

        app.main(new String[]{});

        verify(input, times(2)).nextInt();
        verify(input, times(1)).nextLine();
    }

    @Test
    public void testIsPresent() {
        App.studentList.add(new Student("John Doe", "12345"));

        when(input.nextInt()).thenReturn(5).thenReturn(7);
        when(input.nextLine()).thenReturn("12345");

        app.main(new String[]{});

        verify(input, times(2)).nextInt();
        verify(input, times(1)).nextLine();
    }

    @Test
    public void testListStudent() {
        App.studentList.add(new Student("John Doe", "12345"));

        when(input.nextInt()).thenReturn(6).thenReturn(7);

        app.main(new String[]{});

        verify(input, times(2)).nextInt();
    }

    @Test
    public void testInvalidOption() {
        when(input.nextInt()).thenReturn(8).thenReturn(7);

        app.main(new String[]{});

        verify(input, times(2)).nextInt();
    }
}

