package com.thoughtworks.capacity.gtb.mvc.api;

import com.thoughtworks.capacity.gtb.mvc.repo.UserRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @AfterEach
    public void clearup() {
        UserRepo.clearup();
    }
    public void registerUser() throws Exception{
        String jsonUser = "{\"username\": \"Tom\",\"password\": \"12345\",\"email\": \"tom@qq.com\"}";
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonUser))
                .andExpect(status().isCreated());
    }

    @Test
    public void should_success_when_register() throws Exception {
        String jsonUser = "{\"username\": \"Tom\",\"password\": \"12345\",\"email\": \"tom@qq.com\"}";
        mockMvc.perform(post("/user")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(jsonUser))
                    .andExpect(status().isCreated());
    }

    @Test
    public void should_return_bad_request_when_register_given_user_has_exist() throws Exception {
        registerUser();
        String jsonUser = "{\"username\": \"Tom\",\"password\": \"12345\",\"email\": \"tom@qq.com\"}";
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonUser))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("用户已存在")));
    }
    @Test
    public void should_return_bad_request_when_register_given_username_is_empty() throws Exception {
        registerUser();
        String userNameIsEmpty = "{\"username\": \"\",\"password\": \"12345\",\"email\": \"tom@qq.com\"}";

        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userNameIsEmpty))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("用户名不能为空")));
    }

    @Test
    public void should_return_bad_request_when_register_given_username_is_wrongful() throws Exception {
        registerUser();
        String userNameWrongful = "{\"username\": \"@Tom\",\"password\": \"12345\",\"email\": \"tom@qq.com\"}";

        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userNameWrongful))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("用户名不合法")));
    }

    @Test
    public void should_return_bad_request_when_register_given_username_size_is_less() throws Exception {
        registerUser();
        String userNameOutOfLimitWithLess = "{\"username\": \"To\",\"password\": \"12345\",\"email\": \"tom@qq.com\"}";

        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userNameOutOfLimitWithLess))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("用户名不合法")));
    }

    @Test
    public void should_return_bad_request_when_register_given_username_size_is_more() throws Exception {
        registerUser();
        String userNameOutOfLimitWithMore = "{\"username\": \"Tom123456789\",\"password\": \"12345\",\"email\": \"tom@qq.com\"}";

        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userNameOutOfLimitWithMore))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("用户名不合法")));
    }

    @Test
    public void should_return_bad_request_when_register_given_password_is_empty() throws Exception {
        registerUser();
        String passWordIsEmpty = "{\"username\": \"Tom\",\"password\": \"\",\"email\": \"tom@qq.com\"}";

        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(passWordIsEmpty))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("密码不能为空")));
    }

    @Test
    public void should_return_bad_request_when_register_given_password_size_less() throws Exception {
        registerUser();
        String passWordOutOfLimitWithLess = "{\"username\": \"Tom\",\"password\": \"1234\",\"email\": \"tom@qq.com\"}";

        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(passWordOutOfLimitWithLess))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("密码不合法")));
    }
    @Test
    public void should_return_bad_request_when_register_given_password_size_is_more() throws Exception {
        registerUser();
        String passWordOutOfLimitWithMore = "{\"username\": \"Tom\",\"password\": \"1234567891012\",\"email\": \"tom@qq.com\"}";

        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(passWordOutOfLimitWithMore))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("密码不合法")));
    }

    @Test
    public void should_return_bad_request_when_register_given_eamil_is_wrongful() throws Exception {
        registerUser();
        String jsonUserEmailIsWrongful = "{\"username\": \"Tom\",\"password\": \"12345\",\"email\": \"@qq.com\"}";

        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonUserEmailIsWrongful))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("邮箱地址不合法")));
    }


    @Test
    public void should_return_user_when_login_given_corrent_username_and_password() throws Exception {
        registerUser();
        String jsonUser = "{\"username\": \"Tom\",\"password\": \"12345\"}";
        mockMvc.perform(get("/user")
                .content(jsonUser)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.username", is("Tom")));
    }

    @Test
    public void should_return_bad_request_when_login_given_username_is_empty() throws Exception {
        registerUser();
        String userNameIsEmpty = "{\"username\": \"\",\"password\": \"12345\"}";
        String userNameWrongful = "{\"username\": \"@Tom\",\"password\": \"12345\"}";

        mockMvc.perform(get("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userNameIsEmpty))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("用户名不能为空")));
    }

    @Test
    public void should_return_bad_request_when_login_given_username_is_wrongful() throws Exception {
        registerUser();
        String userNameWrongful = "{\"username\": \"@Tom\",\"password\": \"12345\"}";
        mockMvc.perform(get("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userNameWrongful))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("用户名不合法")));
    }

    @Test
    public void should_return_bad_request_when_login_given_username_size_is_less() throws Exception {
        registerUser();
        String userNameOutOfLimitWithLess = "{\"username\": \"To\",\"password\": \"12345\"}";

        mockMvc.perform(get("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userNameOutOfLimitWithLess))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("用户名不合法")));
    }

    @Test
    public void should_return_bad_request_when_login_given_username_size_is_more() throws Exception {
        registerUser();
        String userNameOutOfLimitWithMore = "{\"username\": \"Tom123456789\",\"password\": \"12345\"}";

        mockMvc.perform(get("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userNameOutOfLimitWithMore))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("用户名不合法")));
    }

    @Test
    public void should_return_bad_request_when_login_given_password_is_empty() throws Exception {
        registerUser();
        String passWordIsEmpty = "{\"username\": \"Tom\",\"password\": \"\",\"email\": \"tom@qq.com\"}";

        mockMvc.perform(get("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(passWordIsEmpty))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("密码不能为空")));
    }

    @Test
    public void should_return_bad_request_when_login_given_password_size_less() throws Exception {
        registerUser();
        String passWordOutOfLimitWithLess = "{\"username\": \"Tom\",\"password\": \"1234\",\"email\": \"tom@qq.com\"}";
        mockMvc.perform(get("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(passWordOutOfLimitWithLess))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("密码不合法")));
    }

    @Test
    public void should_return_bad_request_when_login_given_password_size_more() throws Exception {
        registerUser();
        String passWordOutOfLimitWithMore = "{\"username\": \"Tom\",\"password\": \"1234567891012\",\"email\": \"tom@qq.com\"}";

        mockMvc.perform(get("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(passWordOutOfLimitWithMore))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("密码不合法")));
    }

    @Test
    public void should_return_bad_request_when_login_given_wrong_password() throws Exception {
        registerUser();
        String jsonUser = "{\"username\": \"Tom\",\"password\": \"13457\",\"email\": \"tom@qq.com\"}";
        mockMvc.perform(get("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonUser))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("用户名或密码错误")));
    }

    @Test
    public void should_return_bad_request_when_login_given_wrong_username() throws Exception {
        registerUser();
        String jsonUser = "{\"username\": \"Tomb\",\"password\": \"13456\",\"email\": \"tom@qq.com\"}";
        mockMvc.perform(get("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonUser))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("用户名或密码错误")));
    }

    @Test
    public void should_login_success_when_login_given_corrent_username() throws Exception {
        registerUser();
        String jsonUser = "{\"username\": \"Tom\",\"password\": \"12345\"}";
        mockMvc.perform(get("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonUser))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("Tom")));
    }
}