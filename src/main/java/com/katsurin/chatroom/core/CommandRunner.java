package com.katsurin.chatroom.core;

import com.katsurin.chatroom.classes.*;

import java.io.*;
import java.lang.reflect.Method;
import java.util.HashMap;

public class CommandRunner {
    private static HashMap<String, CommandInfo> commandMap = new HashMap<>();

    public static void initCommands() {
        if (commandMap.size() > 0) return;

        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream("com/katsurin/chatroom/commands");
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        reader.lines().filter(line -> line.endsWith(".class")).forEach(line -> getClassInfo(line));
    }

    public static void runCommand(ChatMessage command, PrintWriter output) {
        CommandInfo target = commandMap.get(command.commandName);

        if (target == null) {
            System.out.println("Client does not recognize that command. Please use /help for a list of possible commands.");
            return;
        }

        try {
            Method method = target.command.getMethod("run", ChatMessage.class, PrintWriter.class);
            method.invoke(target.command, command, output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static HashMap<String, CommandInfo> getCommandList() {
        return commandMap;
    }

    private static void getClassInfo(String classname) {
        classname = classname.substring(0, classname.lastIndexOf("."));
        try {
            Class<ChatCommand> foundClass = (Class<ChatCommand>) Class.forName(
                    "com.katsurin.chatroom.commands." + classname
            );

            CommandInfo command = new CommandInfo(classname, foundClass);
            commandMap.put(classname.toLowerCase(), command);
        } catch (ClassNotFoundException e) {
            System.out.println("[Debug] Failed to retrieve class " + classname);
        } catch (NoSuchFieldException e) {
            System.out.println("[Debug] Failed to parse description field for class " + classname);
        } catch (IllegalAccessException e) {
            System.out.println("[Debug] Failed to access commands package");
            throw new RuntimeException(e);
        }
    }
}
