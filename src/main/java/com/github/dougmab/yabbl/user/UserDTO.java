package com.github.dougmab.yabbl.user;

import com.github.dougmab.yabbl.role.RoleDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class UserDTO {
    public Long id;
    public String username;
    public String handle;
    public Set<RoleDTO> roles = new HashSet<>();

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.handle = user.getHandle();
        user.getRoles().forEach(role -> {
            roles.add(new RoleDTO(role));
        });
    }

    public UserDTO(String username, String handle) {
        this.username = username;
        this.handle = handle;
    }
}
