package com.github.dougmab.yabbl.room;

import com.github.dougmab.yabbl.payload.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/room")
public class RoomController {

    private final RoomService roomService;
    private final JwtDecoder jwtDecoder;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RoomDTO>> getRoom(@PathVariable UUID id) {
        RoomDTO dto = roomService.getRoom(id);

        return ResponseEntity.ok(ApiResponse.ok("Room found successfully", dto));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RoomDTO>> createRoom(@Valid @RequestBody RoomDTO dto, JwtAuthenticationToken jwt) {
        var claims = jwt.getTokenAttributes();
        log.debug(claims.get("id").toString());
        RoomDTO roomDTO = roomService.createRoom(dto, Long.parseLong(claims.get("id").toString()));

        log.info("User {} created a new room: {}", jwt.getName(), roomDTO);
        return ResponseEntity.ok(ApiResponse.ok("Room created successfully", roomDTO));
    }
}
