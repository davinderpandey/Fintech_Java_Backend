package com.fintech.service.impl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.userdetails.UserDetails;

import com.fintech.dto.ResponseDTO;
import com.fintech.dto.UserDTO;
import com.fintech.entity.User;
import com.fintech.mapper.UserMapper;
import com.fintech.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testUserDetailsService() {
        String username = "test@example.com";
        User user = new User();
        when(userRepository.findByEmail(username)).thenReturn(user);

        UserDetails userDetails = userService.userDetailsService().loadUserByUsername(username);

        assertEquals(user, userDetails);
    }

    @Test
    void testSaveUser() {
        UserDTO userDto = new UserDTO();
        User user = new User();

        when(userMapper.mapToUser(userDto)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.mapToUserDto(user)).thenReturn(userDto);

        ResponseDTO response = userService.saveUser(userDto);

        assertTrue(response.isSuccess());
        assertEquals("User saved successfully", response.getMessage());
    }

    @Test
    void testGetAllUser() {
        List<User> users = Arrays.asList(new User(), new User());
        when(userRepository.findAll()).thenReturn(users);
        when(userMapper.mapToUserDtos(users)).thenReturn(Arrays.asList(new UserDTO(), new UserDTO()));

        ResponseDTO response = userService.getAllUser();

        assertTrue(response.isSuccess());
        assertEquals("All Users fetched successfully", response.getMessage());
    }

    @Test
    void testGetUserByIdFound() {
        UUID userId = UUID.randomUUID();
        User user = new User();
        Optional<User> userOptional = Optional.of(user);

        when(userRepository.findById(userId)).thenReturn(userOptional);
        when(userMapper.mapToUserDto(user)).thenReturn(new UserDTO());

        ResponseDTO response = userService.getUserById(userId);

        assertTrue(response.isSuccess());
        assertEquals("User by id fetch successfully", response.getMessage());
    }

    @Test
    void testGetUserByIdNotFound() {
        UUID userId = UUID.randomUUID();

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.getUserById(userId);
        });

        assertTrue(!exception.getMessage().contains("User not found"));
    }

    @Test
    void testGetUserByEmailFound() {
        String email = "test123@example.com";
        User user = new User();

        when(userRepository.findByEmail(email)).thenReturn(user);
        when(userMapper.mapToUserDto(user)).thenReturn(new UserDTO());

        ResponseDTO response = userService.getUserByEmail(email);

        assertTrue(response.isSuccess());
        assertEquals("User by email fetch successfully", response.getMessage());
    }

    @Test
    void testGetUserByEmailNotFound() {
        String email = "test@example.com";

        when(userRepository.findByEmail(email)).thenReturn(null);

        Exception exception = assertThrows(Exception.class, () -> {
            userService.getUserByEmail(email);
        });

        assertTrue(!exception.getMessage().contains("User not found"));
    }
}
