package com.katsurin.chatroom.commands;

import com.katsurin.chatroom.classes.ChatCommand;
import com.katsurin.chatroom.classes.ChatMessage;

import java.io.PrintWriter;

public class Ping extends ChatCommand {
    public static final String description = "Pong!";

    public static void run(ChatMessage command, PrintWriter output) {
        System.out.println("Pong!");
    }
}