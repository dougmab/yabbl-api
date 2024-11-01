package com.github.dougmab.yabbl.chat;

import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.time.Instant;
import java.util.Objects;

@AllArgsConstructor
@Controller
public class ChatController {

    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat/sendMessage")
    public void receiveChannelMessage(@Payload ChatMessage chatMessage, Principal principal) {
        chatMessage.setDate(Instant.now().toString());
        chatMessage.setSender(principal.getName());

        messagingTemplate.convertAndSend("/topic/chat/" + chatMessage.getChannelId(), chatMessage);
    }

    @MessageMapping("/chat/sendPrivateMessage")
    public void receivePrivateMessage(@Payload ChatPrivateMessage privateMessage, Principal principal) {
        privateMessage.setSender(principal.getName());
        privateMessage.setDate(Instant.now().toString());
        messagingTemplate.convertAndSendToUser(privateMessage.getReceiver(), "queue/messages", privateMessage);
        // return message to sender
        messagingTemplate.convertAndSendToUser(privateMessage.getSender(), "queue/messages", privateMessage);
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(
            @Payload ChatMessage chatMessage,
            SimpMessageHeaderAccessor headerAccessor
    ) {
        Objects.requireNonNull(headerAccessor.getSessionAttributes()).put("username", chatMessage.getSender());
        return chatMessage;
    }
}
