package com.katsurin.chatroom.commands;

import com.katsurin.chatroom.classes.ChatMessage;
import com.katsurin.chatroom.classes.ChatCommand;

import java.io.PrintWriter;

public class Exit extends ChatCommand {
    public static final String description = "Disconnect from the current chat room.";

    public static void run(ChatMessage command, PrintWriter output) {
        output.println("[System] " + command.getUsername() + " has disconnected from the room...");
    }
}