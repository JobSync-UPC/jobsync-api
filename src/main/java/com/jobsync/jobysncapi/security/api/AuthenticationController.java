package com.jobsync.jobysncapi.security.api;

import com.jobsync.jobysncapi.security.service.communication.AuthenticationRequest;
import com.jobsync.jobysncapi.security.service.communication.AuthenticationResponse;
import com.jobsync.jobysncapi.security.service.communication.RegisterRequest;
import com.jobsync.jobysncapi.security.domain.model.entity.Role;
import com.jobsync.jobysncapi.security.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication", description = "Register recruiter, applicant, and login user")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Operation(summary = "Register a recruiter", responses = {
            @ApiResponse(description = "Successfully registered a recruiter",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RegisterRequest.class)))
    })
    @PostMapping("/register-recruiter")
    public ResponseEntity<AuthenticationResponse> registerRecruiter(
            @RequestBody RegisterRequest registerRequest
    ) {
        return ResponseEntity.ok(authenticationService.register(registerRequest, Role.ROLE_RECRUITER));
    }

    @Operation(summary = "Register an applicant", responses = {
            @ApiResponse(description = "Successfully registered a recruiter",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RegisterRequest.class)))
    })
    @PostMapping("/register-applicant")
    public ResponseEntity<AuthenticationResponse> registerApplicant(
            @RequestBody RegisterRequest registerRequest
    ) {
        return ResponseEntity.ok(authenticationService.register(registerRequest, Role.ROLE_APPLICANT));
    }

    @Operation(summary = "User login", responses = {
            @ApiResponse(description = "Successfully logged in",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RegisterRequest.class)))
    })
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationRequest loginRequest
    ) {
        return ResponseEntity.ok(authenticationService.login(loginRequest));
    }
}