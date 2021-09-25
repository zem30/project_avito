package com.amr.project;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.junit5.api.DBRider;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.dataset.ReplacementDataSetLoader;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProjectApplication.class})
@AutoConfigureMockMvc
@DBRider
@DBUnit(allowEmptyFields = true)
@DbUnitConfiguration(databaseConnection = "h2DataSource", dataSetLoader = ReplacementDataSetLoader.class)
@TestPropertySource(
        locations = "classpath:/application-integrationtest.properties")
public abstract class AbstractApiTest {

    @Autowired
    public MockMvc mockMvc;

    public ObjectMapper objectMapper = new ObjectMapper();

}
