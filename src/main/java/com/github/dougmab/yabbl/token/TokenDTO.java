package com.github.dougmab.yabbl.token;

import com.github.dougmab.yabbl.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TokenDTO {
    private String token;
    private Long expiresInSeconds;
    private UserDTO user;
}
