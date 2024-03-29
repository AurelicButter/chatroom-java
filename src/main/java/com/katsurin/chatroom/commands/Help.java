package com.katsurin.chatroom.commands;

import com.katsurin.chatroom.classes.*;

import java.io.*;
import java.util.ArrayList;

public class Help extends ChatCommand {
    public static final String description = "Get a list of all commands you can use.";
    private static ArrayList<CommandInfo> commandList = new ArrayList<CommandInfo>();

    public static void run(ChatMessage command, PrintWriter output) {
        if (commandList.size() == 0) {
            Help.initCommandList();
        }
        
        System.out.println("=== Command List ===");

        for (CommandInfo c: commandList) {
            System.out.println("/" + c.name.toLowerCase() + ": " + c.description);
        }
    }

    private static void initCommandList() {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream("com/katsurin/chatroom/commands");
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        reader.lines().filter(line -> line.endsWith(".class")).forEach(line -> getClassInfo(line));
    }

    private static void getClassInfo(String classname) {
        try {
            Class foundClass = Class.forName(
                    "com.katsurin.chatroom.commands." + classname.substring(0, classname.lastIndexOf("."))
            );

            String description = (String) foundClass.getField("description").get(foundClass);
            commandList.add(new CommandInfo(classname.substring(0, classname.lastIndexOf(".")), description));
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
