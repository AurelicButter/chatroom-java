package com.katsurin.chatroom.core;

import com.katsurin.chatroom.classes.ChatMessage;

public class CommandParser {
    /*
     * COMMAND STRUCTURE
     *
     * [<username>]: <standard_msg>
     * [<username>]: /<command>
     */
    public static ChatMessage parseInput(String input) {
        if (input.isEmpty()) return null;

        String[] splitInput = input.split("]");

        String username = splitInput[0].substring(1).trim();

        boolean isCommand = splitInput[1].startsWith("/");

        if (isCommand) {
            splitInput[1] = splitInput[1].substring(1).toLowerCase();
        }

        return new ChatMessage(username, splitInput[1].trim(), isCommand);
    }

    public static ChatMessage parseInput(String username, String input) {
        if (input.isEmpty()) return null;

        boolean isCommand = input.startsWith("/");
        if (isCommand) {
            input = input.substring(1).toLowerCase();
        }

        return new ChatMessage(username, input.trim(), isCommand);
    }
}
