package com.katsurin.chatroom.threads;

import java.io.*;
import java.net.Socket;

public class ClientReceiver implements Runnable {
    private Socket socket;
    private BufferedReader input;

    public ClientReceiver (Socket s) throws IOException {
        this.socket = s;
        this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        try {
            while(true) {
                System.out.println(input.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
