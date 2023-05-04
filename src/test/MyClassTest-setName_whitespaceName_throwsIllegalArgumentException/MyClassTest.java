import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MyClassTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void setName_validName_setsNameSuccessfully() throws Exception {
        // Arrange
        String validName = "John Doe";

        // Act & Assert
        mockMvc.perform(post("/myClass/setName")
                .param("name", validName))
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    public void setName_whitespaceName_throwsIllegalArgumentException(String whitespaceName) {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            mockMvc.perform(post("/myClass/setName")
                    .param("name", whitespaceName))
                    .andExpect(status().isBadRequest());
        });
    }

    @Test
    public void setName_nullName_throwsIllegalArgumentException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            mockMvc.perform(post("/myClass/setName")
                    .param("name", (String) null))
                    .andExpect(status().isBadRequest());
        });
    }
}
