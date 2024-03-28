package com.katsurin.chatroom.core;

import com.katsurin.chatroom.classes.ChatMessage;

public class CommandParser {
    public CommandParser() {

    }

    /*
     * COMMAND STRUCTURE
     *
     * [<username>]: <standard_msg>
     * [<username>]: /<command>
     */
    public ChatMessage parseInput(String input) {
        if (input.isEmpty()) return null;

        String[] splitInput = input.split("]");

        String username = splitInput[0].substring(1).trim();

        boolean isCommand = splitInput[1].startsWith("/");

        if (isCommand) {
            splitInput[1] = splitInput[1].substring(1);
        }

        return new ChatMessage(username, splitInput[1].trim(), isCommand);
    }

    public static void main (String[] args) {
        (new CommandParser()).parseInput("[Butter] Hello world!");
        (new CommandParser()).parseInput("[System] Butter has joined the room");
        (new CommandParser()).parseInput("[Butter] /exit");
    }
}
