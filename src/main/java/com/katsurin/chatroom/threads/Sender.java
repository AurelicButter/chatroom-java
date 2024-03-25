package com.katsurin.chatroom.threads;

import java.io.PrintWriter;
import java.util.Scanner;

public class Sender {
    private Thread thread;
    private Scanner sc;
    private PrintWriter output;
    private String username;

    public Sender(Scanner sc, PrintWriter output, String username) {
        this.sc = sc;
        this.output = output;
        this.username = username;

        thread = new Thread(new Runnable() {
            String msg;

            @Override
            public void run() {
                while (true) {
                    msg = sc.nextLine();
                    output.println("[" + username + "] " + msg);
                    output.flush();
                }
            }
        });
    }

    public void start() {
        thread.start();
    }

    public void updateUsername(String username) {
        this.username = username;
    }
}
