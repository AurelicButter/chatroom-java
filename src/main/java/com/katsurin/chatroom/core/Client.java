package com.katsurin.chatroom.core;

import com.katsurin.chatroom.classes.ChatMessage;
import com.katsurin.chatroom.threads.*;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket = null;
    private PrintWriter out = null;
    private String username = "";
    private ClientSender clientRunner = null;
    private Scanner scanner = new Scanner(System.in);

    public Client(Integer serverPort) {
        if (username.isEmpty()) {
            System.out.print("Enter your name: ");
            updateUsername(scanner.nextLine());
        }

        try {
            socket = new Socket("127.0.0.1", serverPort);
            out = new PrintWriter(socket.getOutputStream(), true);
            clientRunner = new ClientSender(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateUsername(String username) {
        this.username = username;
    }

    public void onStart() {
        ChatMessage userMsg = null;
        try {
            new Thread(clientRunner).start();
            System.out.println("[System] Connection established");
            out.println("[System] " + username + " has connected to the room!");

            do {
                userMsg = CommandParser.parseInput(username, scanner.nextLine());

                if (userMsg == null) {
                    continue;
                }

                if (userMsg.isCommand) {
                    CommandRunner.runCommand(userMsg, out);

                    if (userMsg.message.equals("exit")) {
                        break;
                    }
                } else {
                    out.println(userMsg.printMessage());
                }
            } while (userMsg == null || !userMsg.message.equals("exit"));
        } catch (Exception e) {
            System.out.println("[Debug] Exception has occurred in client. See error message below for details.");

            if (userMsg == null) {
                System.out.println("[Debug] User Message is not initialized");
            } else {
                System.out.println("[Debug] " + userMsg.toString());
            }

            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client currClient = new Client(5000);
        currClient.onStart();
    }
}
