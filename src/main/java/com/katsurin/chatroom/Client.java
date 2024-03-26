package com.katsurin.chatroom;

import com.katsurin.chatroom.enums.ChatRoomEvents;
import com.katsurin.chatroom.threads.*;

import java.beans.*;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client implements PropertyChangeListener {
    private final Scanner sc = new Scanner(System.in);
    private Socket clientSocket = null;
    private BufferedReader in = null;
    private PrintWriter out = null;

    public Client() {
        try {
            clientSocket = new Socket("127.0.0.1", 5000);
            out = new PrintWriter(clientSocket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onStart() {
        Sender senderThread = new Sender(sc, out, "Client");
        senderThread.start();

        Receiver receiver = new Receiver(in, out);
        receiver.startThread(this);

        System.out.println("[System] Connection established");
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(ChatRoomEvents.DISCONNECTEDTHREAD.toString())) {
            System.out.println("Server unavailable");
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.out.println("Failed to close client out of server.");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Client chatClient = new Client();
        chatClient.onStart();
    }
}
