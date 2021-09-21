package com.amr.project.webapp.rest_controller.user_rest_controller;

import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.NoResultException;
import javax.validation.Valid;
import java.util.*;

@RestController
public class UserRestController {

    private UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registrateNewUser(@Valid @RequestBody User user) {
        Map<String, Object> body = new LinkedHashMap<>();
        System.out.println(user);
        try {
            userService.findByEmail(user.getEmail());
            body.put("isExist", "User with this email exist");
            return ResponseEntity.badRequest().body(body);
        } catch (NoResultException e) {
        }
        try {
            userService.findByPhone(user.getPhone());
            body.put("isExist", "User with this phone exist");
            return ResponseEntity.badRequest().body(body);
        } catch (NoResultException e) {
        }
        try {
            userService.findByUsername(user.getUsername());
            body.put("isExist", "User with this username exist");
            return ResponseEntity.badRequest().body(body);
        } catch (NoResultException e) {
        }
        userService.persist(user);
        return ResponseEntity.ok().body("ok");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<?> validationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        Map<String, Object> body = new LinkedHashMap<>();
        Map<String, String> errors = new HashMap<>();
        fieldErrors.forEach(f -> errors.put(f.getField(), f.getDefaultMessage()));
        body.put("errors", errors);
        return ResponseEntity.badRequest().body(body);
    }



}
