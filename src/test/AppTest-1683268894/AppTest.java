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

    @Mock
    private ArrayList<Student> studentList;

    @BeforeEach
    public void setUp() {
        App.studentList = studentList;
    }

    @Test
    public void testAddStudent() {
        when(input.nextInt()).thenReturn(1).thenReturn(7);
        app.main(new String[]{});
        verify(studentList, times(1)).add(any(Student.class));
    }

    @Test
    public void testUpdateStudent() {
        when(input.nextInt()).thenReturn(2).thenReturn(7);
        app.main(new String[]{});
        verify(studentList, times(1)).set(anyInt(), any(Student.class));
    }

    @Test
    public void testDeleteStudent() {
        when(input.nextInt()).thenReturn(3).thenReturn(7);
        app.main(new String[]{});
        verify(studentList, times(1)).remove(anyInt());
    }

    @Test
    public void testFetchStudent() {
        when(input.nextInt()).thenReturn(4).thenReturn(7);
        app.main(new String[]{});
        verify(studentList, times(1)).get(anyInt());
    }

    @Test
    public void testIsPresent() {
        when(input.nextInt()).thenReturn(5).thenReturn(7);
        app.main(new String[]{});
        verify(studentList, times(1)).contains(any(Student.class));
    }

    @Test
    public void testListStudent() {
        when(input.nextInt()).thenReturn(6).thenReturn(7);
        app.main(new String[]{});
        verify(studentList, times(1)).forEach(any());
    }

    @Test
    public void testInvalidChoice() {
        when(input.nextInt()).thenReturn(0).thenReturn(7);
        app.main(new String[]{});
        verify(studentList, never()).add(any(Student.class));
        verify(studentList, never()).set(anyInt(), any(Student.class));
        verify(studentList, never()).remove(anyInt());
        verify(studentList, never()).get(anyInt());
        verify(studentList, never()).contains(any(Student.class));
        verify(studentList, never()).forEach(any());
    }
}

