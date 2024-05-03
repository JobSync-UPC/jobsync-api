package com.jobsync.jobysncapi.security.service;

import com.jobsync.jobysncapi.security.domain.model.entity.User;
import com.jobsync.jobysncapi.security.domain.persistence.UserRepository;
import com.jobsync.jobysncapi.shared.clients.CloudinaryClient;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private CloudinaryClient cloudinaryClient;

    @InjectMocks
    private UserService userService;

    // Integration Test con Cloudinary  y UserRepository
    @Test
    void updateProfilePicture() {
        // Arrange
        Long userId = 1L;
        String profilePictureUrl = "https://images.pexels.com/photos/771742/pexels-photo-771742.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500";
        MultipartFile file = mock(MultipartFile.class);

        // Mocking de servicio Cloudinary Client
        when(cloudinaryClient.uploadImage(file)).thenReturn(profilePictureUrl);

        // Mocking de servicio UserRepository
        User user = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        User updatedUser = userService.updateProfilePicture(userId, file);

        // Assert
        assertEquals(profilePictureUrl, updatedUser.getProfilePictureUrl());
        verify(cloudinaryClient, times(1)).uploadImage(file);
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(user);
    }
}