package com.github.dougmab.yabbl.user;

import com.github.dougmab.yabbl.avatar.StyledAvatarDTO;
import com.github.dougmab.yabbl.role.RoleDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class UserDTO {
    public Long id;
    public String nickname;
    public String handle;
    public StyledAvatarDTO avatar;
    public Set<RoleDTO> roles = new HashSet<>();

    public UserDTO(User user) {
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.handle = user.getHandle();
        this.avatar = new StyledAvatarDTO(user.getAvatar());
        user.getRoles().forEach(role -> {
            roles.add(new RoleDTO(role));
        });
    }
}
