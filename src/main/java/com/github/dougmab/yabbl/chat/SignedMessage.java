package com.github.dougmab.yabbl.chat;

import lombok.Getter;
import lombok.Setter;

public interface SignedMessage {
    public void setSender(String sender);
    public String getSender();
}
