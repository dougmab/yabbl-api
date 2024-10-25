package com.github.dougmab.yabbl.role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoleDTO {
    private Long id;
    private String name;

    public RoleDTO(Role role) {
        this.id = role.getId();
        this.name = role.getAuthority();
    }
}
