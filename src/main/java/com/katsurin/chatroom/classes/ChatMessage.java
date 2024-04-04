package com.katsurin.chatroom.classes;

import com.katsurin.chatroom.core.Client;

public class ChatMessage {
    public String commandName = "";
    public String message;
    public boolean isCommand = false;
    public Client client;

    public ChatMessage(Client client, String message) {
        this.client = client;
        this.parseInput(message);
    }

    public String printMessage() {
        return "[" + this.client.getUsername() + "] " + this.message;
    }
    public String getUsername() { return this.client.getUsername(); }

    @Override
    public String toString() {
        return "ChatMessage: { " +
                "username: " + this.client.getUsername() +
                ", message: " + this.message +
                ", isCommand: " + this.isCommand + " }";
    }

    private void parseInput(String input) {
        if (input.isEmpty()) return;

        this.isCommand = input.startsWith("/");

        if (this.isCommand) {
            input = input.substring(1);

            String[] inputStruct = input.split(" ");

            if (inputStruct.length == 1) {
                commandName = input.toLowerCase();
                input = "";
            } else {
                commandName = inputStruct[0].toLowerCase();
                input = input.split(" ", 2)[1];
            }
        }

        this.message = input.trim();
    }
}
