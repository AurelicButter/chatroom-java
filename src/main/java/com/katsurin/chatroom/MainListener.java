package com.katsurin.chatroom;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainListener implements PropertyChangeListener {
    private ClassWithProperty test;

    public MainListener() {
        test = new ClassWithProperty();
        test.addPropertyChangeListener(this);

        for (int i = 0; i < 10; i++) {
            test.toggleState();
        }
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("isOnline")) {
            System.out.println("We're online!");
        }
        System.out.println(test.getIsOnline());
    }

    public static void main(String[] args) {
        new MainListener(); // do everything in the constructor
    }
}
