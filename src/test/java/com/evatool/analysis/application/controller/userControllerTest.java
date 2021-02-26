package com.evatool.analysis.application.controller;

import com.evatool.analysis.api.interfaces.UserController;
import com.evatool.analysis.model.Stakeholder;
import com.evatool.analysis.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static com.evatool.analysis.common.TestDataGenerator.getUser;
import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class userControllerTest {

    @Autowired
    private UserController userController;

    @Test
    public void testStakeholderController() {

//        //create user
//        User user = userController.addUser(getUser());
//
//        //check is user created
//        assertThat(userController.getUserById(user.getUserId()).get()).isNotNull();
//
//        //change user title
//        String testTitle = "Stani";
//        user.setUserName(testTitle);
//        userController.updateUser(user);
//
//        //check is user title changed
//        Optional<User> userAfterUpdate = userController.getUserById(user.getUserId());
//
//        assertThat(userAfterUpdate.get().getUserName()).isEqualTo(testTitle);
//
//        assertThat(userAfterUpdate.get().getUserName()).isEqualTo(testTitle);
//
//        // delete user
//        UUID idUser = userAfterUpdate.get().getUserId();
//        userController.deleteUser(idUser);
//
//        //check is user deleted
//        Optional<User> deletedUser = userController.getUserById(idUser);
//        assertThat(deletedUser.isEmpty()).isEqualTo(true);
    }



}
