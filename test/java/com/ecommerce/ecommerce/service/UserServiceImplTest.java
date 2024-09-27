package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.response.UserResponse;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.enums.RoleType;
import com.ecommerce.ecommerce.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        user.setRole(RoleType.USER);
    }

    @Test
    public void testGetUserById() {

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));


        UserResponse userResponse = userService.getUserById(1L);


        assertNotNull(userResponse);
        assertEquals("testuser", userResponse.username());
        assertEquals("test@example.com", userResponse.email());
        assertEquals(RoleType.USER, userResponse.role());


        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetUserByIdNotFound() {

        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userService.getUserById(1L));


        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void testAddUser() {

        when(userRepository.save(any(User.class))).thenReturn(user);


        UserResponse userResponse = new UserResponse(null, "testuser", "test@example.com", "password", RoleType.USER);
        UserResponse savedUserResponse = userService.addUser(userResponse);

        assertNotNull(savedUserResponse);
        assertEquals("testuser", savedUserResponse.username());
        assertEquals("test@example.com", savedUserResponse.email());
        assertEquals(RoleType.USER, savedUserResponse.role());


        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testUpdateUser() {

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);


        UserResponse updatedUserResponse = new UserResponse(1L, "updatedUser", "updated@example.com", "password", RoleType.USER);
        UserResponse result = userService.updateUser(1L, updatedUserResponse);


        assertNotNull(result);
        assertEquals("updatedUser", result.username());
        assertEquals("updated@example.com", result.email());


        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testDeleteUser() {

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));


        userService.deleteUser(1L);

        // Repository delete methodunun çağrıldığından emin ol
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    public void testGetAllUsers() {

        List<User> users = Arrays.asList(user, new User());
        when(userRepository.findAll()).thenReturn(users);


        List<UserResponse> userResponses = userService.getAllUsers();

        assertNotNull(userResponses);
        assertEquals(2, userResponses.size());

        verify(userRepository, times(1)).findAll();
    }
}
