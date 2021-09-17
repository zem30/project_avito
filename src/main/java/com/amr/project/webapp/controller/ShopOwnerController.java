package com.amr.project.webapp.controller;

import com.amr.project.model.entity.User;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShopOwnerController {

    @GetMapping("/myshop/userlist")
    String getPage(@CurrentSecurityContext(expression = "authentication.principal") User principal,
                   Model model) {
        model.addAttribute("user", principal);
        return "shopOwner/shop_add_discount";
    }
}
