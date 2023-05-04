import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MyClassTest {

    @Autowired
    private MockMvc mockMvc;

    private MyClass myClass;

    @BeforeEach
    public void setUp() {
        myClass = new MyClass();
    }

    @Test
    public void setName_validName_setsNameSuccessfully() {
        // Arrange
        String validName = "John Doe";

        // Act
        myClass.setName(validName);

        // Assert
        assertEquals(validName, myClass.getName());
    }

    @Test
    public void setName_emptyName_throwsIllegalArgumentException() {
        // Arrange
        String emptyName = "";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> myClass.setName(emptyName));
    }

    @Test
    public void setName_nullName_throwsNullPointerException() {
        // Arrange
        String nullName = null;

        // Act & Assert
        assertThrows(NullPointerException.class, () -> myClass.setName(nullName));
    }

    @Test
    public void setName_apiEndpoint_validName_returnsSuccess() throws Exception {
        // Arrange
        String validName = "John Doe";

        // Act & Assert
        mockMvc.perform(post("/api/set-name")
                .param("name", validName))
                .andExpect(status().isOk());
    }

    @Test
    public void setName_apiEndpoint_emptyName_returnsBadRequest() throws Exception {
        // Arrange
        String emptyName = "";

        // Act & Assert
        mockMvc.perform(post("/api/set-name")
                .param("name", emptyName))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void setName_apiEndpoint_nullName_returnsBadRequest() throws Exception {
        // Arrange
        String nullName = null;

        // Act & Assert
        mockMvc.perform(post("/api/set-name")
                .param("name", nullName))
                .andExpect(status().isBadRequest());
    }
}
