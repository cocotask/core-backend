package com.cocotask.core.user.service;

import com.cocotask.core.exception.NoContentException;
import com.cocotask.core.exception.NotFoundException;
import com.cocotask.core.user.domain.User;
import com.cocotask.core.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User addUser(User userInput) {
        return userRepository.save(userInput);
    }

    public List<User> getAllUsers() throws Exception {
        List<User> users = userRepository.findAll();
        if (CollectionUtils.isEmpty(users)) {
            throw new NotFoundException();
        }

        return users;
    }

    public User getUser(Long userUid) throws Exception {
        User user = userRepository.findOne(userUid);
        if (StringUtils.isEmpty(user)) {
            throw new NoContentException();
        }

        return user;
    }

    public User updateUser(Long userUid, User userInput) {
        User user = userRepository.findOne(userUid);
        user.setUserName(userInput.getUserName());

        return userRepository.saveAndFlush(user);
    }

    public void deleteUser(Long userUid) {
        userRepository.delete(userUid);
    }
}
