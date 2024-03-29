package com.katsurin.chatroom.core;

import com.katsurin.chatroom.classes.ChatMessage;
import com.katsurin.chatroom.commands.*;
import java.io.PrintWriter;

public class CommandRunner {
    public static void runCommand(ChatMessage command, PrintWriter output) {
        switch (command.message) {
            case "help":
                Help.run(command, output);
                break;
            case "exit":
                Exit.run(command, output);
                break;
            default:
                System.out.println("Client does not recognize that command. Please use /help for a list of possible commands.");
                break;
        }
    }
}
