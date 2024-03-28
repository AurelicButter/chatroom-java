package com.katsurin.chatroom.core;

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
        try {
            String userInput;

            new Thread(clientRunner).start();
            System.out.println("[System] Connection established");
            out.println("[System] " + username + " has connected to the room!");

            do {
                userInput = scanner.nextLine();
                if (userInput.equals("exit")) {
                    out.println("[System] " + username + " has disconnected from the room...");
                    break;
                }
                out.println("[" + username + "]" + " " + userInput);
            } while (!userInput.equals("exit"));
        } catch (Exception e) {
            System.out.println("Exception occurred in client main: " + e.getStackTrace());
        }
    }

    public static void main(String[] args) {
        Client currClient = new Client(5000);
        currClient.onStart();
    }
}
