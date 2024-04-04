package com.katsurin.chatroom.classes;

public class CommandInfo {
    public String name;
    public String description;
    public Class<ChatCommand> command;

    public CommandInfo(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public CommandInfo(String name, String description, Class command) {
        this.name = name;
        this.description = description;
        this.command = command;
    }

    public String toString() {
        return "/" + this.name.toLowerCase() + ": " + this.description;
    }
}
