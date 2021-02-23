package com.evatool.analysis.api.controller;

import com.evatool.analysis.api.interfaces.UserController;
import com.evatool.analysis.model.Stakeholder;
import com.evatool.analysis.model.User;
import com.evatool.analysis.repository.UserRepository;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class UserControllerImpl implements UserController {

    @Autowired
    private UserRepository userRepository;
    Logger logger = LoggerFactory.getLogger(UserControllerImpl.class);

    @Override
    public List<User> getUserList() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public User addUser(User user) {
        logger.info("/addUser");
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        logger.info("/user");
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(UUID id) {
        logger.info("/user");
        userRepository.deleteById(id);
    }


}
