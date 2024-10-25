package com.github.dougmab.yabbl.user;

import com.github.dougmab.yabbl.role.Role;
import com.github.dougmab.yabbl.role.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder passwordEncoder;

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

    @Transactional
    public UserDTO register(UserRegisterDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setHandle(dto.getHandle());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.getRoles().add(roleRepository.findByAuthority(Role.Authorities.USER.name()));

        repository.save(user);

        return new UserDTO(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByHandle(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
