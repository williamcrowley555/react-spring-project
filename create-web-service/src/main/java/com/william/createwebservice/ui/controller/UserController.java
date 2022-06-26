package com.william.createwebservice.ui.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.william.createwebservice.gcp_pubsub.publisher.UserAddedPublisher;
import com.william.createwebservice.gcp_pubsub.publisher.UserDeletedPublisher;
import com.william.createwebservice.gcp_pubsub.publisher.UserUpdatedPublisher;
import com.william.createwebservice.service.UserService;
import com.william.createwebservice.shared.dto.UserDTO;
import com.william.createwebservice.ui.model.request.UserDetailsRequest;
import com.william.createwebservice.ui.model.response.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600, allowCredentials = "true", allowedHeaders = "*",
//        methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT}
//)
@RestController
@RequestMapping(path = "api/users")
public class UserController {

    @Autowired
    private UserDeletedPublisher userDeletedPublisher;

    @Autowired
    private UserUpdatedPublisher userUpdatedPublisher;

    @Autowired
    private UserAddedPublisher userAddedPublisher;


    @Autowired
    private UserService userService;

    @Operation(summary = "Get a list of users based on page and limit parameters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns a list of users based on page and limit parameters",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "No users found",
                    content = @Content) })
    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<?> getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam(value = "limit", defaultValue = "25") int limit) {
        List<UserResponse> returnValue = new ArrayList<>();

        List<UserDTO> users = userService.getUsers(page, limit);

        for (UserDTO userDTO : users) {
            ModelMapper modelMapper = new ModelMapper();
            UserResponse userModel = modelMapper.map(userDTO, UserResponse.class);
            returnValue.add(userModel);
        }

        return ResponseEntity.ok(returnValue);
    }

    @Operation(summary = "Get an user by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content) })
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getUser(@PathVariable String id) {
        ModelMapper modelMapper = new ModelMapper();
        UserDTO userDTO = userService.getUserByUserId(id);
        UserResponse returnValue = modelMapper.map(userDTO, UserResponse.class);

        return ResponseEntity.ok(returnValue);
    }

    @Operation(summary = "Create a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDetailsRequest.class)) }),
            @ApiResponse(responseCode = "404", description = "Not available",
                    content = @Content) })
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDetailsRequest userDetails) throws Exception {
        ModelMapper modelMapper = new ModelMapper();
        UserDTO userDTO = modelMapper.map(userDetails, UserDTO.class);

        UserDTO createdUser = userService.createUser(userDTO);
        UserResponse returnValue = modelMapper.map(createdUser, UserResponse.class);

        userAddedPublisher.publish(new ObjectMapper().writeValueAsString(returnValue));

        return ResponseEntity.ok(returnValue);
    }

    @Operation(summary = "Update an existing user by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDetailsRequest.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content) })
    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UserDetailsRequest userDetails, @PathVariable String id) throws JsonProcessingException {
        ModelMapper modelMapper = new ModelMapper();

        UserDTO userDTO = modelMapper.map(userDetails, UserDTO.class);

        UserDTO updateUser = userService.updateUser(id, userDTO);
        UserResponse returnValue = modelMapper.map(updateUser, UserResponse.class);

        System.out.println("Update user" + returnValue);

        userUpdatedPublisher.publish(new ObjectMapper().writeValueAsString(returnValue));

        return ResponseEntity.ok(returnValue);
    }

    @Operation(summary = "Delete an existing user by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted successfully",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content) })
    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable String id) throws Exception {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        userService.deleteUser(id);
        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
//        messagingGateway.sendToPubSub(new ObjectMapper().writeValueAsString(id));

        UserResponse deletedUser = new UserResponse();
        deletedUser.setUserId(id);

        userDeletedPublisher.publish(new ObjectMapper().writeValueAsString(deletedUser));

        return ResponseEntity.ok(returnValue);
    }

    @Operation(summary = "Get a list of users by role name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns a list of users by role name",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "No users found",
                    content = @Content) })
    @GetMapping(path = "/roles/{roleName}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<?> getUsersByRoles(@PathVariable String roleName) {
        List<UserResponse> returnValue = new ArrayList<>();

        List<UserDTO> users = userService.getUsersByRoleName(roleName);

        ModelMapper modelMapper = new ModelMapper();
        for (UserDTO userDTO : users) {
            UserResponse userModel = modelMapper.map(userDTO, UserResponse.class);
            returnValue.add(userModel);
        }

        return ResponseEntity.ok(returnValue);
    }
}
