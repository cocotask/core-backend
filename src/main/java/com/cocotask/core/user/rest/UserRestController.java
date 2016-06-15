package com.cocotask.core.user.rest;

import com.cocotask.core.user.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

@RestController
@RequestMapping("/rest")
public class UserRestController {
    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

    /**
     * 사용자 목록 데이터를 조회한다.
     * @return
     */
    @GetMapping("/users")
    public Collection<User> getUsers() {
        logger.debug("call getUsers()");

        return createUsers();
    }

    public Collection<User> createUsers() {
        List <User> users = new ArrayList();
        User antMan = new User();
        antMan.setUid(1L);
        antMan.setUserName("antMan");

        User ironMan = new User();
        ironMan.setUid(2L);
        ironMan.setUserName("ironMan");

        users.add(antMan);
        users.add(ironMan);

        return users;
    }
}
