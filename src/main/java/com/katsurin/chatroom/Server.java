package com.katsurin.chatroom;

import com.katsurin.chatroom.threads.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server implements PropertyChangeListener {
    private final Scanner sc = new Scanner(System.in);
    private ServerSocket serverSocket = null;
    private Socket clientSocket = null;
    private BufferedReader in = null;
    private PrintWriter out = null;

    public Server () {
        try {
            serverSocket = new ServerSocket(5000);
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onStart() {
        Sender senderThread = new Sender(sc, out, "Server");
        senderThread.start();

        Receiver receiver = new Receiver(in, out);
        receiver.startThread(this);
    }

    public void propertyChange (PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("disconnectedThread")) {
            try {
                clientSocket.close();
                serverSocket.close();
            } catch (IOException e) {
                System.out.println("Server failed to close connections");
                e.printStackTrace();
            }
        }
    }

    public static void main (String[] args) {
        Server chatroom = new Server();
        chatroom.onStart();
    }
}
