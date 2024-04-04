package com.katsurin.chatroom.commands;

import com.katsurin.chatroom.classes.*;
import com.katsurin.chatroom.core.CommandRunner;

import java.io.*;

public class Help extends ChatCommand {
    public static final String description = "Get a list of all commands you can use.";

    public static void run(ChatMessage command, PrintWriter output) {
        System.out.println("=== Command List ===");

        for (CommandInfo c: CommandRunner.getCommandList().values()) {
            System.out.println(c.toString());
        }
    }
}
