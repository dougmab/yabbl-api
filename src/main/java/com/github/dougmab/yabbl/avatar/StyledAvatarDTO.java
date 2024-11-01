package com.github.dougmab.yabbl.avatar;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StyledAvatarDTO {
    private String ascii;
    private String foregroundHexColor;
    private String backgroundHexColor;

    public StyledAvatarDTO(StyledAvatar styledAvatar) {
        this.ascii = styledAvatar.getAscii();
        this.foregroundHexColor = styledAvatar.getForegroundHexColor();
        this.backgroundHexColor = styledAvatar.getBackgroundHexColor();
    }
}
