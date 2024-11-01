package com.github.dougmab.yabbl.token;

import com.github.dougmab.yabbl.role.Role;
import com.github.dougmab.yabbl.user.User;
import com.github.dougmab.yabbl.user.UserDTO;
import com.github.dougmab.yabbl.user.UserLoginDTO;
import com.github.dougmab.yabbl.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class TokenService {

    private final JwtEncoder jwtEncoder;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public TokenService(JwtEncoder jwtEncoder, BCryptPasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.jwtEncoder = jwtEncoder;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public TokenDTO generateToken(UserLoginDTO credentials) {
        User user = userRepository.findByEmail(credentials.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Email or password is invalid!"));

        if (!user.isLoginCorrect(credentials, passwordEncoder)) {
            throw new BadCredentialsException("Email or password is invalid!");
        }

        Instant now = Instant.now();
        long expiresInSeconds = 3600L;

        String scopes = user.getRoles().stream()
                .map(Role::getAuthority)
                .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer("yabbl-api")
                .subject(user.getHandle())
                .expiresAt(now.plusSeconds(expiresInSeconds))
                .issuedAt(now)
                .claim("scope", scopes)
                .claim("id", user.getId())
                .build();

        String jwtToken = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return new TokenDTO(jwtToken, expiresInSeconds, new UserDTO(user));
    }
}
