package com.katsurin.chatroom.classes;

import java.io.PrintWriter;

public abstract class ChatCommand {
    String description;

    public static void run(ChatMessage command, PrintWriter output) {
        System.out.println(command.message + " has not been implemented yet...");
    }
}
