package com.evatool.analysis.api.interfaces;

import com.evatool.analysis.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Api(description = "API-endpoint for user")
public interface UserController {

    @GetMapping("/user")
    @ApiOperation(value = "This method returns a list of all user")
    public List<User> getUserList();

    @GetMapping("/user/{id}")
    @ApiOperation(value = "This method returns an Optional of a user by his ID ")
    public Optional<User> getUserById(@PathVariable UUID id);

    @PostMapping("/addUser")
    @ApiOperation(value = "This method add a user")
    public User addUser(User user);

    @PutMapping("/user/{id}")
    @ApiOperation(value = "This method updated an user by his id")
    public User updateUser(@RequestBody User user);

    @DeleteMapping("/user/{id}")
    @ApiOperation(value = "This method delete an user by his id ")
    public void deleteUser(@PathVariable UUID id);
}
