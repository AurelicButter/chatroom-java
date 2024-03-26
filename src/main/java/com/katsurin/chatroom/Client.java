package com.katsurin.chatroom;

import com.katsurin.chatroom.enums.ChatRoomEvents;
import com.katsurin.chatroom.threads.*;

import java.beans.*;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client implements PropertyChangeListener {
    private Socket socket = null;
    private BufferedReader in = null;
    private PrintWriter out = null;
    private String username = "";
    private ClientSender clientRunner = null;

    public Client(Integer serverPort) {
        try {
            socket = new Socket("127.0.0.1", serverPort);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            clientRunner = new ClientSender(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(ChatRoomEvents.DISCONNECTEDTHREAD.toString())) {
            System.out.println("[System] You have disconnected from the chatroom");
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Failed to close client out of server.");
                e.printStackTrace();
            }
        }
    }

    public void updateUsername(String username) {
        this.username = username;
    }

    public void onStart() {
        /*Sender senderThread = new Sender(out, "Client");
        senderThread.start();

        Receiver receiver = new Receiver(in, out);
        receiver.startThread(this);*/

        try {
            Scanner scanner = new Scanner(System.in);
            String userInput;

            new Thread(clientRunner).start();
            System.out.println("[System] Connection established");

            if (username.isEmpty()) {
                System.out.print("Enter your name ");
                updateUsername(scanner.nextLine());
            }

            do {
                userInput = scanner.nextLine();
                out.println("[" + username + "]" + ": " + userInput);
                if (userInput.equals("exit")) {
                    break;
                }
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
