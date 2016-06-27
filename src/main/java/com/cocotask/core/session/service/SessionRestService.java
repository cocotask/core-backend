package com.cocotask.core.session.service;

import com.cocotask.core.session.domain.SessionRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class SessionRestService {
    private static final Logger logger = LoggerFactory.getLogger(SessionRestService.class);

    public SessionRest getRestSession(HttpSession session) {
        Long loginUserUid = (Long) session.getAttribute(SessionRest.LOGIN_USER_UID);
        String logninUserName = (String) session.getAttribute(SessionRest.LOGIN_USER_NAME);

        SessionRest sessionRest = new SessionRest();
        sessionRest.setUserUid(loginUserUid);
        sessionRest.setLoginUserName(logninUserName);

        logger.debug(sessionRest.toString());

        return sessionRest;
    }
}
