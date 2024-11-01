package com.github.dougmab.yabbl.avatar;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tb_avatars")
public class StyledAvatar implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 3)
    private String ascii;
    private String foregroundHexColor;
    private String backgroundHexColor;

    public StyledAvatar(StyledAvatarDTO dto) {
        ascii = dto.getAscii();
        foregroundHexColor = dto.getForegroundHexColor();
        backgroundHexColor = dto.getBackgroundHexColor();
    }
}
