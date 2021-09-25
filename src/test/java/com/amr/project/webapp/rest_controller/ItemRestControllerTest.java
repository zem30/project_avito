package com.amr.project.webapp.rest_controller;

import com.amr.project.AbstractApiTest;
import com.amr.project.converter.ItemMapper;
import com.amr.project.model.dto.ItemDto;
import com.amr.project.service.impl.ItemServiceImpl;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ItemRestControllerTest extends AbstractApiTest {

    @Autowired
    private ItemServiceImpl itemService;

    @Autowired
    private ItemMapper itemMapper;

    @Test
    @DataSet(cleanBefore = true,value = "dataset/testApiItem/TestItemPersist.xml")
    @ExpectedDataSet(value = "dataset/testApiItem/expected/TestItemPersist.xml")
    public void testItemPersist() throws Exception {

        ItemDto itemDto = ItemDto.builder().name("Test").shopName("Alibaba").description("Test")
                .count(2).rating(0d).price(new BigDecimal(200)).categoriesName(new String[]{"Sport"}).build();

        mockMvc.perform(
                post("/shop/item")
                        .content(objectMapper.writeValueAsString(itemDto))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().is2xxSuccessful());

    }

    @Test
    @DataSet(cleanBefore = true,value = "dataset/testApiItem/TestItemDelete.xml")
    public void testItemDelete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/shop/item/{id}", 1))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @DataSet(cleanBefore = true, value = "dataset/testApiItem/TestItemUpdate.xml")
    @ExpectedDataSet(value = "dataset/testApiItem/expected/TestItemUpdate.xml")
    public void testItemUpdate() throws Exception {
        ItemDto itemDto = itemMapper.itemToDto(itemService.getByKey(1l));
        itemDto.setShopName("Alibaba");
        itemDto.setCategoriesName(new String[]{"Sport"});
        itemDto.setName("TestTest");

        mockMvc.perform(MockMvcRequestBuilders.put("/shop/item")
                .content(objectMapper.writeValueAsString(itemDto)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

}

