package com.jobsync.jobysncapi.security.api;

import com.jobsync.jobysncapi.security.service.communication.AuthenticationRequest;
import com.jobsync.jobysncapi.security.service.communication.AuthenticationResponse;
import com.jobsync.jobysncapi.security.service.communication.RegisterRequest;
import com.jobsync.jobysncapi.security.domain.model.entity.Role;
import com.jobsync.jobysncapi.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register-recruiter")
    public ResponseEntity<AuthenticationResponse> registerRecruiter(
            @RequestBody RegisterRequest registerRequest
    ) {
        return ResponseEntity.ok(authenticationService.register(registerRequest, Role.ROLE_RECRUITER));
    }

    @PostMapping("/register-applicant")
    public ResponseEntity<AuthenticationResponse> registerApplicant(
            @RequestBody RegisterRequest registerRequest
    ) {
        return ResponseEntity.ok(authenticationService.register(registerRequest, Role.ROLE_APPLICANT));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationRequest loginRequest
    ) {
        return ResponseEntity.ok(authenticationService.login(loginRequest));
    }
}
