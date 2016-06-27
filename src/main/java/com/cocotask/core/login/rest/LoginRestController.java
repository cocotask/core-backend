package com.cocotask.core.login.rest;

import com.cocotask.core.login.service.LoginService;
import com.cocotask.core.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/rest")
public class LoginRestController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public User doLogin(@RequestBody User userInput, HttpSession session) throws Exception {
        return loginService.doLogin(userInput, session);
    }
}
