package com.example.authentication.userServiceTest;

import com.fasterxml.jackson.core.JsonFactoryBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.json.JSONString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserServiceTest {

//    @Autowired
//    @Resource
//    private MockMvc mockMvc;

    @Test
    public void testRegisterAndLoginUser() {

        // Register an account
//        HashMap<String, String> register = new HashMap<>();
//        register.put("username", "yuruojie");
//        register.put("password", "1234567");
//        register.put("name", "xiaotudou");
//
//        mockMvc.perform(post("/api/v1/auth/register")
//                        .contentType("application/json")
//                        .content(asJsonString(register)))
//                .andDo(print()).andExpect(status().isCreated());


        // Login
//        HashMap<String, String> login = new HashMap<>();
//        login.put("username", "yuruojie");
//        login.put("password", "1234567");
//
//        String response = mockMvc.perform(post("/api/v1/auth/authenticate")
//                .contentType("application/json").content(asJsonString(login)))
//                .andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        // Request /user api expected a deny
//        mockMvc.perform(get("/api/v1/users"))
//                .andExpect(status().isForbidden());

        // Get returned token from login request
//        JSONObject jsonObject = new JSONObject(response);
//        String token = jsonObject.get("token").toString();


        // Get the user information and request again
//        mockMvc.perform(get("/api/v1/users").header("Authorization", "Bearer " + token))
//                .andDo(print()).andExpect(status().isOk());


        // Update user's name


    }

    @Test
    public void testLoginUser() {

    }

    @Test
    public void testLogoutUser() {

    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
