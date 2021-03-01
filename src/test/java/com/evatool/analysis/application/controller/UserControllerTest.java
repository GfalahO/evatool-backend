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
public class UserControllerTest {

    @Autowired
    private UserController userController;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testUserController_ThrowException(){

        User user = new User("TestName", "123456789", "Test@Mail.com");

        //create User
        UserDTO userDTO = getUserDTO(user.getUserName(),user.getUserPassword(), user.getUserEmail());
        UserDTO userDTOObj = userController.addUser(userDTO).getContent();

        //check is user created
        assertThat(userController.getUserById(userDTOObj.getRootEntityID())).isNotNull();

        //change user name
        String testName = "TestName";
        userDTOObj.setUserName(testName);
        userController.updateUser(userDTOObj);

        //check is User name changed
        UserDTO userAfterUpdate = userController.getUserById(userDTOObj.getRootEntityID()).getContent();

        assertThat(userAfterUpdate.getUserName()).isEqualTo(testName);

        //delete User
        UUID id = userDTOObj.getRootEntityID();
        userController.deleteUser(id);

        //check is user deleted
        Exception exception = assertThrows(EntityNotFoundException.class, () -> userController.getUserById(userDTOObj.getRootEntityID()).getContent());






    }

}
