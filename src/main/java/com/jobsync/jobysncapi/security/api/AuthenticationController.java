package com.jobsync.jobysncapi.security.api;

import com.jobsync.jobysncapi.security.service.dto.AuthenticationRequestDto;
import com.jobsync.jobysncapi.security.service.dto.AuthenticationResponseDto;
import com.jobsync.jobysncapi.security.service.dto.RegisterRequestDto;
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
                            schema = @Schema(implementation = RegisterRequestDto.class)))
    })
    @PostMapping("/register-recruiter")
    public ResponseEntity<AuthenticationResponseDto> registerRecruiter(
            @RequestBody RegisterRequestDto registerRequestDto
    ) {
        return ResponseEntity.ok(authenticationService.register(registerRequestDto, Role.ROLE_RECRUITER));
    }

    @Operation(summary = "Register an applicant", responses = {
            @ApiResponse(description = "Successfully registered a recruiter",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RegisterRequestDto.class)))
    })
    @PostMapping("/register-applicant")
    public ResponseEntity<AuthenticationResponseDto> registerApplicant(
            @RequestBody RegisterRequestDto registerRequestDto
    ) {
        return ResponseEntity.ok(authenticationService.register(registerRequestDto, Role.ROLE_APPLICANT));
    }

    @Operation(summary = "User login", responses = {
            @ApiResponse(description = "Successfully logged in",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RegisterRequestDto.class)))
    })
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> login(
            @RequestBody AuthenticationRequestDto loginRequest
    ) {
        return ResponseEntity.ok(authenticationService.login(loginRequest));
    }
}