package com.cocotask.core.session.rest;

import com.cocotask.core.session.domain.SessionRest;
import com.cocotask.core.session.service.SessionRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/rest")
public class SessionRestController {
    @Autowired
    private SessionRestService sessionRestService;

    @GetMapping("/session")
    public SessionRest readSession(HttpSession session) {
        SessionRest sessionRest = sessionRestService.getRestSession(session);

        return sessionRest;
    }
}
