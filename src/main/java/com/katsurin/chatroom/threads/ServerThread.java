package com.katsurin.chatroom.threads;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread {
    private Socket socket;
    private ArrayList<ServerThread> threadList;
    private PrintWriter output;

    public ServerThread(Socket socket, ArrayList<ServerThread> threads) {
        this.socket = socket;
        this.threadList = threads;
    }

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            output = new PrintWriter(socket.getOutputStream(), true);

            while(true) {
                String outputStr = input.readLine();

                if (outputStr.equals("exit")) {
                    break;
                }
                System.out.println(outputStr);
                printToClients(outputStr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printToClients(String output) {
        for (ServerThread s: threadList) {
            s.output.println(output);
        }
    }
}