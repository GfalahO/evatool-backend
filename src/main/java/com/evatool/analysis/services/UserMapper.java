package com.evatool.analysis.services;

import com.evatool.analysis.model.User;
import com.evatool.analysis.dto.*;
import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public List<UserDTO> map(List<User> resultList) {
        List<UserDTO> userDTOList = new ArrayList<>();
        for(User user : resultList){
            userDTOList.add(map(user));
        }
        return userDTOList;
    }

    private UserDTO map(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName(user.getUserName());
        userDTO.setRootEntityID(user.getUserId());
        userDTO.setEmail(user.getUserEmail());
        userDTO.setPassword(user.getUserPassword());

        return userDTO;
    }
}
