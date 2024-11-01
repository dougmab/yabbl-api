package com.github.dougmab.yabbl.chat;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatPrivateMessage implements SignedMessage {
    private String sender;
    private String receiver;
    private String content;
    private String date;
}
