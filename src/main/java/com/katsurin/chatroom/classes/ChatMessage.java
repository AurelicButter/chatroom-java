package com.katsurin.chatroom.classes;

public class ChatMessage {
    public String username;
    public String message;
    public boolean isCommand = false;

    public ChatMessage(String username, String message) {
        this.username = username;
        this.message = message;
    }

    public ChatMessage(String username, String message, boolean isCommand) {
        this.username = username;
        this.message = message;
        this.isCommand = isCommand;
    }

    public String printMessage() {
        return "[" + this.username + "] " + this.message;
    }

    @Override
    public String toString() {
        return "ChatMessage: { " +
                "username: " + this.username +
                ", message: " + this.message +
                ", isCommand: " + this.isCommand + " }";
    }
}
