package com.github.dougmab.yabbl.room;

import com.github.dougmab.yabbl.avatar.StyledAvatar;
import com.github.dougmab.yabbl.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Profile;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String description;
    @OneToOne
    private StyledAvatar avatar;
    @ManyToMany
    @JoinTable(
            name = "tb_rooms_users",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users;

    @ManyToOne
    private User owner;

    public Room(RoomDTO dto) {
        name = dto.getName();
        description = dto.getDescription();
        avatar = new StyledAvatar(dto.getAvatar());
    }
}
