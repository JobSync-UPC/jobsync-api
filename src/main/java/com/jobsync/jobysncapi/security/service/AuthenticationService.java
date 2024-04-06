package com.jobsync.jobysncapi.security.service;

import com.jobsync.jobysncapi.security.domain.model.entity.*;
import com.jobsync.jobysncapi.security.domain.persistence.ApplicantRepository;
import com.jobsync.jobysncapi.security.domain.persistence.UserRepository;
import com.jobsync.jobysncapi.security.domain.persistence.RecruiterRepository;
import com.jobsync.jobysncapi.security.service.communication.AuthenticationRequest;
import com.jobsync.jobysncapi.security.service.communication.AuthenticationResponse;
import com.jobsync.jobysncapi.security.service.communication.RegisterRequest;
import com.jobsync.jobysncapi.shared.exception.GlobalExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final RecruiterRepository recruiterRepository;

    private final ApplicantRepository applicantRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse register(RegisterRequest registerRequest, Role role) {

        if(userRepository.existsByEmail(registerRequest.getEmail())){
            throw new GlobalExceptionHandler("User","Email already exists");
        }

        if(!registerRequest.getPassword().matches("^(?=.*[0-9])(?=.*[a-zA-Z]).{6,}$")){
            throw new GlobalExceptionHandler("User","Password must contain at least one letter, at least one number, and be longer than six characters");
        }

        if(!registerRequest.getEmail().matches("^(.+)@(.+)$")){
            throw new GlobalExceptionHandler("User","Email is not valid");
        }

        if(role.equals(Role.ROLE_RECRUITER)){
            var recruiter = Recruiter.builder()
                    .firstname(registerRequest.getFirstname())
                    .lastname(registerRequest.getLastname())
                    .email(registerRequest.getEmail())
                    .password(passwordEncoder.encode(registerRequest.getPassword()))
                    .profilePictureUrl(registerRequest.getProfilePictureUrl())
                    .country(registerRequest.getCountry())
                    .role(role)
                    .enabled(Boolean.TRUE)
                    .build();
            recruiterRepository.save(recruiter);
            var jwtToken = jwtService.generateToken(recruiter);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        } else {
            var applicant = Applicant.builder()
                    .firstname(registerRequest.getFirstname())
                    .lastname(registerRequest.getLastname())
                    .email(registerRequest.getEmail())
                    .password(passwordEncoder.encode(registerRequest.getPassword()))
                    .profilePictureUrl(registerRequest.getProfilePictureUrl())
                    .country(registerRequest.getCountry())
                    .role(role)
                    .enabled(Boolean.TRUE)
                    .build();
            applicantRepository.save(applicant);
            var jwtToken = jwtService.generateToken(applicant);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }
    }


    public AuthenticationResponse login(AuthenticationRequest registerRequest) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            registerRequest.getEmail(),
                            registerRequest.getPassword()
                    )
            );
        } catch (Exception e) {
            throw new GlobalExceptionHandler("User","Email or password is incorrect");
        }

        var user = userRepository.findByEmail(registerRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Email or password is incorrect"));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
