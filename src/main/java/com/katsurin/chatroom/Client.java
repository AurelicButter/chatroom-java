package com.katsurin.chatroom;

import com.katsurin.chatroom.threads.Sender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        final Socket clientSocket;
        final BufferedReader in;
        final PrintWriter out;
        final Scanner sc = new Scanner(System.in);

        try {
            clientSocket = new Socket("127.0.0.1", 5000);
            out = new PrintWriter(clientSocket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            (new Sender(sc, out, "Client")).start();

            Thread recieve = new Thread(new Runnable() {
                String msg;

                @Override
                public void run() {
                    try {
                        msg = in.readLine();

                        while (msg != null && !msg.isEmpty()) {
                            System.out.println(msg);
                            msg = in.readLine();
                        }

                        System.out.println("Server unavailable");
                        out.close();
                        clientSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            recieve.start();
            System.out.println("[System] Connection established");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
