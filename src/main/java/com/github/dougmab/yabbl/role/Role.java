package com.github.dougmab.yabbl.role;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "tb_roles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Role implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authority;

    public enum Authorities {
        ADMIN(1L),
        USER(2L);

        private final Long id;

        Authorities(Long id) {
            this.id = id;
        }
    }

}
