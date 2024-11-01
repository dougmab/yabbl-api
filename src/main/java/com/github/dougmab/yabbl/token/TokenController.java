package com.github.dougmab.yabbl.token;

import com.github.dougmab.yabbl.payload.ApiResponse;
import com.github.dougmab.yabbl.user.UserLoginDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("/auth")
public class TokenController {

    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<TokenDTO>> login(@Valid @RequestBody UserLoginDTO loginDto) {
        TokenDTO token = tokenService.generateToken(loginDto);

        log.info("User logged in: {}", token.getUser().getHandle());
        return ResponseEntity.ok(ApiResponse.ok("Login successful", token));
    }
}
