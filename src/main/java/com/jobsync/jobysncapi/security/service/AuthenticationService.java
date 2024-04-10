package com.jobsync.jobysncapi.security.service;

import com.jobsync.jobysncapi.applicant.model.entity.Applicant;
import com.jobsync.jobysncapi.recruiter.domain.model.entity.Recruiter;
import com.jobsync.jobysncapi.security.domain.model.entity.*;
import com.jobsync.jobysncapi.applicant.model.persistence.ApplicantRepository;
import com.jobsync.jobysncapi.security.domain.persistence.UserRepository;
import com.jobsync.jobysncapi.recruiter.domain.model.persistence.RecruiterRepository;
import com.jobsync.jobysncapi.security.service.dto.AuthenticationRequestDto;
import com.jobsync.jobysncapi.security.service.dto.AuthenticationResponseDto;
import com.jobsync.jobysncapi.security.service.dto.RegisterRequestDto;
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


    public AuthenticationResponseDto register(RegisterRequestDto registerRequestDto, Role role) {

        if(userRepository.existsByEmail(registerRequestDto.getEmail())){
            throw new GlobalExceptionHandler("User","Email already exists");
        }

        if(!registerRequestDto.getPassword().matches("^(?=.*[0-9])(?=.*[a-zA-Z]).{6,}$")){
            throw new GlobalExceptionHandler("User","Password must contain at least one letter, at least one number, and be longer than six characters");
        }

        if(!registerRequestDto.getEmail().matches("^(.+)@(.+)$")){
            throw new GlobalExceptionHandler("User","Email is not valid");
        }

        if(role.equals(Role.ROLE_RECRUITER)){
            var recruiter = Recruiter.builder()
                    .firstname(registerRequestDto.getFirstname())
                    .lastname(registerRequestDto.getLastname())
                    .email(registerRequestDto.getEmail())
                    .password(passwordEncoder.encode(registerRequestDto.getPassword()))
                    .profilePictureUrl(registerRequestDto.getProfilePictureUrl())
                    .phoneNumber(registerRequestDto.getPhoneNumber())
                    .country(registerRequestDto.getCountry())
                    .role(role)
                    .enabled(Boolean.TRUE)
                    .build();
            recruiterRepository.save(recruiter);
            var jwtToken = jwtService.generateToken(recruiter);
            return AuthenticationResponseDto.builder()
                    .token(jwtToken)
                    .user(recruiter)
                    .build();
        } else {
            var applicant = Applicant.builder()
                    .firstname(registerRequestDto.getFirstname())
                    .lastname(registerRequestDto.getLastname())
                    .email(registerRequestDto.getEmail())
                    .password(passwordEncoder.encode(registerRequestDto.getPassword()))
                    .profilePictureUrl(registerRequestDto.getProfilePictureUrl())
                    .phoneNumber(registerRequestDto.getPhoneNumber())
                    .country(registerRequestDto.getCountry())
                    .role(role)
                    .enabled(Boolean.TRUE)
                    .build();
            applicantRepository.save(applicant);
            var jwtToken = jwtService.generateToken(applicant);
            return AuthenticationResponseDto.builder()
                    .token(jwtToken)
                    .user(applicant)
                    .build();
        }
    }


    public AuthenticationResponseDto login(AuthenticationRequestDto registerRequest) {

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

        var applicant = applicantRepository.findByEmail(registerRequest.getEmail()).orElse(null);
        var recruiter = recruiterRepository.findByEmail(registerRequest.getEmail()).orElse(null);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponseDto.builder()
                .token(jwtToken)
                .user(user)
                .applicant(applicant)
                .recruiter(recruiter)
                .build();
    }
}
