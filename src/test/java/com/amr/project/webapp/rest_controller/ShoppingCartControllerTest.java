package com.amr.project.webapp.rest_controller;

import com.amr.project.AbstractApiTest;
import com.amr.project.model.dto.CartItemDto;
import com.amr.project.model.dto.ShopDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ShoppingCartControllerTest extends AbstractApiTest {

    private final static String GET_USER_SHOPPING_CART_URL = "/shoppingCart/getUserShoppingCart";
    private final static String LOAD_LOCAL_SHOPPING_CART_TO_SERVER_URL = "/shoppingCart/loadLocalShoppingCartToServer";
    private final static String ADD_NEW_CART_ITEM_URL = "/shoppingCart/addNewCartItem";
    private final static String UPDATE_QUANTITY_URL = "/shoppingCart/updateQuantity";
    private final static String DELETE_POSITION_FROM_SHOPPING_CART_URL = "/shoppingCart/deletePositionFromCart";


    @Test
    @WithMockUser(username = "user4_username")
//    @DataSet(cleanBefore = true, value = "datasets/Cart_items.xml")
//    @ExpectedDataSet(value = "datasets/cart_items/expected/Cart_items.xml")
    void getUserShoppingCart() throws Exception {
        MvcResult mvcResult = mvc.perform(get(GET_USER_SHOPPING_CART_URL))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        List<CartItemDto> actualUserCart = new ObjectMapper().readValue(content, new TypeReference<List<CartItemDto>>(){});
//        assertEquals(expectedUserCart, actualUserCart);
    }

    @Test
    void loadLocalShoppingCartToServer() {
    }

    @Test
    void addNewCartItem() {
    }

    @Test
    void updateQuantity() {
    }

    @Test
    void deletePositionFromCart() {
    }
}