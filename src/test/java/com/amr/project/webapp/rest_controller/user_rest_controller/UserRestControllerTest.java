package com.amr.project.webapp.rest_controller.user_rest_controller;

import com.amr.project.AbstractApiTest;
import com.amr.project.inserttestdata.repository.UserRepository;
import com.amr.project.model.entity.User;
import com.amr.project.model.enums.Gender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

class UserRestControllerTest extends AbstractApiTest {

    private final static String REGISTRATION_URL = "/registration";
    private final static String GET_USER_SHOPS_URL = "/getUserShops/";
    private final static String GET_USER_ITEMS_URL = "/getUserOrders/";
    @Autowired
    private UserRepository userRepository;
    private User defaultUser = User.builder().firstName("first_name_test")
            .lastName("last_name_test")
            .username("username_test")
            .email("email_test@mail.ru")
            .phone("+7777777777")
            .gender(Gender.MALE)
            .password("123456789").build();
    private User userWithEmailExist = User.builder().firstName("first_name_test")
            .lastName("last_name_test")
            .username("username_test")
            .email("admin1@mail")
            .phone("+7477777777")
            .gender(Gender.MALE)
            .password("123456789").build();
    private User userWithUsernameExist = User.builder().firstName("first_name_test")
            .lastName("last_name_test")
            .username("moderator1_username")
            .email("123test@mail.ru")
            .phone("+7777778777")
            .gender(Gender.MALE)
            .password("123456789").build();
    private User userWithPhoneExist = User.builder().firstName("first_name_test")
            .lastName("last_name_test")
            .username("username_test")
            .email("email_test7@mail.ru")
            .phone("user2_phone")
            .gender(Gender.MALE)
            .password("123456789").build();
    private User userWrongFields1 = User.builder().firstName("first_name_test")
            .lastName("last_name_test")
            .username("username_test")
            .email("email_test@mail.ru")
            .phone("3")
            .gender(Gender.MALE)
            .password("123456789").build();

    @Test
    void registrationNewUserTest_then200Status() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post(REGISTRATION_URL)
                .content(asJsonString(defaultUser))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void registrationNewUserTest_ExistUser_then400Status() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post(REGISTRATION_URL)
                .content(asJsonString(userWithEmailExist))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().json("{'isExist':'User with this email exist'}"));
        mvc.perform(MockMvcRequestBuilders.post(REGISTRATION_URL)
                .content(asJsonString(userWithUsernameExist))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().json("{'isExist':'User with this username exist'}"));
        mvc.perform(MockMvcRequestBuilders.post(REGISTRATION_URL)
                .content(asJsonString(userWithPhoneExist))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().json("{'isExist':'User with this phone exist'}"));
    }

    @Test
    void registrationNewUserTest_WrongValidation_then400Status() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post(REGISTRATION_URL)
                .content(asJsonString(userWrongFields1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().json("{'errors':{'phone':'phone number must be at least 11 characters'}}"));
    }

    @Test
    void getUserShopTest_then200Status() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(GET_USER_SHOPS_URL + 5))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getUserItemsTest_then200Status() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(GET_USER_ITEMS_URL + 7))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getUserShopTest_UserNoShops_then400Status() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(GET_USER_SHOPS_URL + 1))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    void getUserItemsTest_UserNoItems_then400Status() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(GET_USER_ITEMS_URL + 1))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }


}