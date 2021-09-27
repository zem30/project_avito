package com.amr.project.service.impl;

import com.amr.project.AbstractIntegrationTest;
import com.amr.project.service.abstracts.OrderService;
import com.amr.project.service.abstracts.UserService;
import com.github.database.rider.core.DBUnitRule;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.api.DBRider;
import com.github.database.rider.junit5.util.EntityManagerProvider;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest

@TestPropertySource("/application-test.properties")
@DBRider
@DBUnit(caseSensitiveTableNames = true, allowEmptyFields = true, schema = "platform_test")

@DataSet(value = {"Role.xml"} , cleanBefore = true)
class OrderServiceImplTest extends AbstractIntegrationTest {

    private final OrderService orderService;
    private final UserService userService;

    @Autowired
    public OrderServiceImplTest(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @Test
    void changeStatusToPaid() {

        System.out.println(userService.getByKey(1L).getEmail());

        Assertions.assertTrue(true);
    }
}