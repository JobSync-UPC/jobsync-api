package com.jobsync.jobysncapi.security.service;

import com.jobsync.jobysncapi.security.domain.model.entity.User;
import com.jobsync.jobysncapi.security.domain.persistence.UserRepository;

import com.jobsync.jobysncapi.security.service.dto.UpdateUserRequestDto;
import com.jobsync.jobysncapi.shared.clients.CloudinaryClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final CloudinaryClient cloudinaryClient;

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User disableUser(Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setEnabled(Boolean.FALSE);
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User enableUser(Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setEnabled(Boolean.TRUE);
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateUser(Long id, UpdateUserRequestDto updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setFirstname(updatedUser.getFirstname());
                    user.setLastname(updatedUser.getLastname());
                    user.setPhoneNumber(updatedUser.getPhoneNumber());
                    user.setCountry(updatedUser.getCountry());
                    user.setProfilePictureUrl(updatedUser.getProfilePictureUrl());

                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateProfilePicture(Long id, MultipartFile file) {
        // make this async
        String profilePictureUrl = cloudinaryClient.uploadImage(file);


        return userRepository.findById(id)
                .map(user -> {
                    user.setProfilePictureUrl(profilePictureUrl);
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
