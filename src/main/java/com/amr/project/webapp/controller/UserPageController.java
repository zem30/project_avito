package com.amr.project.webapp.controller;

import com.amr.project.converter.UserMapper;
import com.amr.project.model.dto.UserDto;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
@RequiredArgsConstructor
public class UserPageController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping(value = "/user/{id}", produces = "image/png")
    public String getUserPage(@PathVariable("id") Long id, Model model) {
        User user = userService.getByKey(id);
        UserDto userDto = userMapper.userToDto(user);
        System.err.println(userDto);
        model.addAttribute("user", userDto);
        return "UserPage";
    }

    @GetMapping("/user/{id}/userlist")
    public String getUserList(@PathVariable("id") Long id, Model model){
        User user = userService.getByKey(id);
        UserDto userDto = userMapper.userToDto(user);
        System.err.println(userDto);
        model.addAttribute("user", userDto);
        return "/shopOwner/shop_add_discount";
    }

}
