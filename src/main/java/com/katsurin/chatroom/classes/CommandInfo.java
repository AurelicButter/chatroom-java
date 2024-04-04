package com.katsurin.chatroom.classes;

public class CommandInfo {
    private String name;
    private String description;
    public Class<ChatCommand> command;

    public CommandInfo(String name, Class command) throws NoSuchFieldException, IllegalAccessException {
        this.name = name;
        this.command = command;
        this.description = (String) command.getField("description").get(command);
    }

    public String getName() { return this.name; }
    public String getDescription() { return this.description; }

    public String toString() {
        return "/" + this.name.toLowerCase() + ": " + this.description;
    }
}
