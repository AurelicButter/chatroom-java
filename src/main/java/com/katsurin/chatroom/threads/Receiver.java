package com.katsurin.chatroom.threads;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Receiver {
    private Thread thread;
    private PrintWriter output;
    private BufferedReader input;
    private boolean isClosed = false;
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);

    public Receiver(BufferedReader input, PrintWriter output) {
        this.input = input;
        this.output = output;

        thread = new Thread(new Runnable() {
            String msg;

            @Override
            public void run() {
                try {
                    msg = input.readLine();

                    while (msg != null && !msg.isEmpty()) {
                        System.out.println(msg);
                        msg = input.readLine();
                    }

                    System.out.println("Client disconnected");
                    output.close();
                    stopThread();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void startThread(PropertyChangeListener l) {
        addPropertyChangeListener(l);
        thread.start();
    }

    public void stopThread() {
        changes.firePropertyChange("disconnectedThread", this.isClosed, true);
        this.isClosed = true;
    }

    public Boolean closedThread() { return this.isClosed; }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        changes.removePropertyChangeListener(l);
    }
}