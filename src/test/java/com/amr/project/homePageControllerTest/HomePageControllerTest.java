package com.amr.project.homePageControllerTest;

import com.amr.project.AbstractIntegrationTest;
import com.github.database.rider.core.api.dataset.DataSet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DataSet(value = {"Item.xml", "Item-category.xml",
        "Category.xml", "Shop.xml", "Shop-item.xml",
        "Country.xml"}, cleanBefore = true, cleanAfter = true)
public class HomePageControllerTest extends AbstractIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getHomePageController() throws Exception {
        mockMvc.perform(get("homepage"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$.length()").value(6));
    }
}