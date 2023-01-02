package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.model.UserForm;
import com.example.demo.domain.service.UsersRegisterService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class RegisterController {
    
    @Autowired
    private UsersRegisterService usersRegisterService;

    @GetMapping("/form")
    private String readForm(@ModelAttribute UserForm userForm) {
        return "form";
    }

    @PostMapping("/form")
    private String confirm(@Validated(UserForm.Groups.class) @ModelAttribute UserForm user, BindingResult result, Model model) {

        if (result.hasErrors()) {
            // エラーがある場合、form.htmlに戻る
            return "form";
        }

     // 登録処理
        if(usersRegisterService.isValid(user, result)) {
            if (result.hasErrors()) {
                // エラーがある場合、form.htmlに戻る
                return "form";
            }
        } 
            
        usersRegisterService.register(user);
            
        return "confirm";
                
    }
    
}
