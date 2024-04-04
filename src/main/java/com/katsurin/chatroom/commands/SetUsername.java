package com.katsurin.chatroom.commands;

import com.katsurin.chatroom.classes.ChatCommand;
import com.katsurin.chatroom.classes.ChatMessage;

import java.io.PrintWriter;

public class SetUsername extends ChatCommand {
    public static final String description = "Update your username.";

    public static void run(ChatMessage command, PrintWriter output) {
        output.println("[System] " + command.getUsername() + " has updated their username to " + command.message);
        command.client.updateUsername(command.message);
    }
}