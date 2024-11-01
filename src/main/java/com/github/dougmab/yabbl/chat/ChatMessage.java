package com.github.dougmab.yabbl.chat;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage implements SignedMessage {
    private String content;
    private String sender;
    private String channelId;
    private String date;
    private MessageType type;

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }
}
