package com.katsurin.chatroom.core;

import com.katsurin.chatroom.threads.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server {
    private ServerSocket serverSocket = null;
    private Socket clientSocket = null;
    private BufferedReader in = null;
    private PrintWriter out = null;
    private static int serverPort = 5000;

    public Server () {
        try {
            serverSocket = new ServerSocket(serverPort);
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ArrayList<ServerThread> threadList = new ArrayList<>();
        try {
            ServerSocket serverSocket = new ServerSocket(serverPort);
            System.out.println("[System] Launched chatroom server on port " + serverPort);
            while(true) {
                Socket socket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(socket, threadList);
                threadList.add(serverThread);
                serverThread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
