package com.amr.project;

import com.amr.project.converter.ItemMapper;
import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.entity.Category;
import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.Review;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.impl.CategoryServiceImpl;
import com.amr.project.service.impl.ItemServiceImpl;
import com.amr.project.service.impl.ReviewServiceImpl;
import com.amr.project.service.impl.ShopServiceImpl;
import com.amr.project.webapp.rest_controller.ItemRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;


@SpringBootApplication(exclude = {SessionAutoConfiguration.class, SecurityAutoConfiguration.class})
@EnableAsync
public class ProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }

}