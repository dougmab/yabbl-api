package com.github.dougmab.yabbl.room;

import com.github.dougmab.yabbl.avatar.AvatarRepository;
import com.github.dougmab.yabbl.avatar.StyledAvatar;
import com.github.dougmab.yabbl.avatar.StyledAvatarDTO;
import com.github.dougmab.yabbl.user.User;
import com.github.dougmab.yabbl.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@Service
public class RoomService {

    private final RoomRepository repository;
    private final AvatarRepository avatarRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public RoomDTO getRoom(UUID id) {
        Room room = repository.findById(id).orElseThrow(EntityNotFoundException::new);

        return new RoomDTO(room);
    }

    @Transactional
    public RoomDTO createRoom(@Valid RoomDTO dto, Long userId) {
        StyledAvatar avatar = new StyledAvatar(dto.getAvatar());
        avatarRepository.save(avatar);

        User currentUserReference = userRepository.getReferenceById(userId);

        Room room = Room.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .avatar(avatar)
                .users(Set.of(currentUserReference))
                .owner(currentUserReference)
                .build();

        repository.save(room);

        return new RoomDTO(room);
    }
}
