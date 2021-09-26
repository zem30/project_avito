package com.amr.project.moderatorControllerTest;

import com.amr.project.AbstractIntegrationTest;
import com.github.database.rider.core.api.dataset.DataSet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@DataSet(value = {"Item.xml"} , cleanBefore = true)
public class ItemModeratorControllerTest extends AbstractIntegrationTest {

    private final String url = "/moderator/api/items/";
    private final MockMvc mockMvc;

    @Autowired
    public ItemModeratorControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    public void getAllUnmoderatedItemsTest() throws Exception {
        mockMvc.perform(get(url + "getUnmoderatedItems"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(6));
    }


}
