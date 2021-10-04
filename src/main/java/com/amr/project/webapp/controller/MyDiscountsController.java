package com.amr.project.webapp.controller;

import com.amr.project.converter.DiscountMapper;
import com.amr.project.service.abstracts.DiscountService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MyDiscountsController {

    @GetMapping()
    public String discountPage() {
        return "myDiscountOffersPage";
    }

}
