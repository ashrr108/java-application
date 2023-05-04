import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MyClassTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void setName_validName_setsNameSuccessfully() throws Exception {
        String validName = "John Doe";

        mockMvc.perform(post("/myClass/setName")
                .contentType("application/json")
                .content("{\"name\":\"" + validName + "\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(validName));
    }

    @Test
    public void setName_nullName_throwsIllegalArgumentException() throws Exception {
        String nullName = null;

        mockMvc.perform(post("/myClass/setName")
                .contentType("application/json")
                .content("{\"name\":" + nullName + "}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("IllegalArgumentException"));
    }

    @Test
    public void setName_emptyName_throwsIllegalArgumentException() throws Exception {
        String emptyName = "";

        mockMvc.perform(post("/myClass/setName")
                .contentType("application/json")
                .content("{\"name\":\"" + emptyName + "\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("IllegalArgumentException"));
    }

    @Test
    public void setName_nameWithInvalidCharacters_throwsIllegalArgumentException() throws Exception {
        String invalidName = "John@Doe";

        mockMvc.perform(post("/myClass/setName")
                .contentType("application/json")
                .content("{\"name\":\"" + invalidName + "\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("IllegalArgumentException"));
    }
}
