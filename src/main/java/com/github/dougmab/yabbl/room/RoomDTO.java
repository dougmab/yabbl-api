package com.github.dougmab.yabbl.room;

import com.github.dougmab.yabbl.avatar.StyledAvatarDTO;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomDTO {
    private String id;
    private String name;
    private String description;
    private StyledAvatarDTO avatar;

    public RoomDTO(Room room) {
        this.id = room.getId().toString();
        this.name = room.getName();
        this.description = room.getDescription();
        this.avatar = new StyledAvatarDTO(room.getAvatar());
    }
}
