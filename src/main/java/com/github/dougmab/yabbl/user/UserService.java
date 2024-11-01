package com.github.dougmab.yabbl.user;

import com.github.dougmab.yabbl.avatar.AvatarRepository;
import com.github.dougmab.yabbl.avatar.StyledAvatar;
import com.github.dougmab.yabbl.role.Role;
import com.github.dougmab.yabbl.role.RoleRepository;
import com.github.dougmab.yabbl.room.RoomDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder passwordEncoder;
    private final AvatarRepository avatarRepository;

    @Transactional
    public UserDTO findById(Long id) {
        User user = repository.findById(id).orElseThrow(EntityNotFoundException::new);

        return new UserDTO(user);
    }

    @Transactional(readOnly = true)
    public Page<UserDTO> findAll(Pageable pageable) {
        Page<User> users = repository.findAll(pageable);

        return users.map(UserDTO::new);
    }

    @Transactional(readOnly = true)
    public Page<UserDTO> findAllFilteredByHandle(String handle, Pageable pageable) {
        Page<User> users = repository.findByHandleContaining(handle, pageable);

        return users.map(UserDTO::new);
    }

    @Transactional
    public UserDTO register(UserRegisterDTO dto) {
        StyledAvatar userAvatar = StyledAvatar.builder()
                .ascii(":)")
                .backgroundHexColor("#FFFFFF")
                .foregroundHexColor("#000000")
                .build();

        avatarRepository.save(userAvatar);

        User user = User.builder()
                .nickname(dto.getHandle())
                .handle(dto.getHandle())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .avatar(userAvatar)
                .roles(new HashSet<>(List.of(roleRepository.findByAuthority(Role.Authorities.USER.name()))))
                .build();

        repository.save(user);

        return new UserDTO(user);
    }

    @Transactional(readOnly = true)
    public List<RoomDTO> getAllUserRooms(String handle) {
        List<RoomDTO> rooms = new LinkedList<>();

        User user = (User) loadUserByUsername(handle);
        user.getRooms().forEach(room -> rooms.add(new RoomDTO(room)));

        return rooms;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByHandle(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Transactional
    public UserDTO update(UserDTO dto, String handle) {
        // I'll not implement email and handle update rn

        User user = (User) loadUserByUsername(handle);

        user.getAvatar().setAscii(dto.getAvatar().getAscii());
        user.getAvatar().setBackgroundHexColor(dto.getAvatar().getBackgroundHexColor());
        user.getAvatar().setForegroundHexColor(dto.getAvatar().getForegroundHexColor());

        user.setNickname(dto.getNickname());

        repository.save(user);

        return new UserDTO(user);
    }
}
