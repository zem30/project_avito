package com.amr.project.webapp.controller;

import com.amr.project.converter.UserMapper;
import com.amr.project.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ShopOwnerController {

    private final UserMapper userMapper;

    @GetMapping("/myshop/userlist")
    String getPage(@CurrentSecurityContext(expression = "authentication.principal") User principal,
                   Model model) {
        model.addAttribute("user",userMapper.userToUserDto(principal));
        return "shopOwner/shop_add_discount";
    }
}
