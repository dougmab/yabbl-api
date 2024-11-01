package com.github.dougmab.yabbl.user;

import com.github.dougmab.yabbl.payload.ApiResponse;
import com.github.dougmab.yabbl.room.RoomDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<UserDTO>>> findAll(@RequestParam String filterByHandle, Pageable pageable) {
        if (filterByHandle == null)
            return ResponseEntity.ok(ApiResponse.ok("Users found successfully", userService.findAll(pageable)));


        return ResponseEntity.ok(ApiResponse.ok("Users found successfully", userService.findAllFilteredByHandle(filterByHandle, pageable)));
    }

    @GetMapping("/{handle}")
    public ResponseEntity<ApiResponse<UserDTO>> findByHandle(@PathVariable String handle) {
        UserDTO dto = new UserDTO((User) userService.loadUserByUsername(handle));

        return ResponseEntity.ok(ApiResponse.ok("User found successfully", dto));
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserDTO>> getCurrentUser(JwtAuthenticationToken jwt) {
        UserDTO dto = new UserDTO((User) userService.loadUserByUsername(jwt.getName()));

        return ResponseEntity.ok(ApiResponse.ok("User found successfully", dto));
    }

    @GetMapping("/me/room")
    public ResponseEntity<ApiResponse<List<RoomDTO>>> getCurrentUserRooms(JwtAuthenticationToken jwt) {
        List<RoomDTO> dto = userService.getAllUserRooms(jwt.getName());

        return ResponseEntity.ok(ApiResponse.ok("Successfully retrieved the rooms the user participates in", dto));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserDTO>> register(@Valid @RequestBody UserRegisterDTO dto) {
        UserDTO userDTO = userService.register(dto);

        log.info("User registered: {}", userDTO.getHandle());
        return ResponseEntity.ok(ApiResponse.ok("User registered successfully", userDTO));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<UserDTO>> updateUser(@Valid @RequestBody UserDTO dto, JwtAuthenticationToken jwt) {
        UserDTO userDTO = userService.update(dto, jwt.getName());

        log.info("User updated: {}", userDTO.getHandle());
        return ResponseEntity.ok(ApiResponse.ok("User updated successfully", userDTO));
    }

}
