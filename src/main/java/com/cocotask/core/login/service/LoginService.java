package com.cocotask.core.login.service;

import com.cocotask.core.exception.NotFoundException;
import com.cocotask.core.session.domain.SessionRest;
import com.cocotask.core.user.domain.User;
import com.cocotask.core.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpSession;

@Service
@Transactional
public class LoginService {
    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    private UserService userService;

    public User validLoginUser(User userInput) throws Exception {
        User user = userService.getUserByUserEmail(userInput.getUserEmail());

        if (ObjectUtils.isEmpty(user)) {
            throw new NotFoundException();
        }

        if (!user.getPassword().equals(userInput.getPassword())) {
            throw new NotFoundException();
        }

        return user;
    }

    public User doLogin(User userInput, HttpSession session) throws Exception {
        User user = validLoginUser(userInput);

        session.setAttribute(SessionRest.LOGIN_USER_UID, user.getUid());
        session.setAttribute(SessionRest.LOGIN_USER_NAME, user.getUserName());

        return user;
    }

    public void doLogout(HttpSession session) {
        session.invalidate();
    }
}
