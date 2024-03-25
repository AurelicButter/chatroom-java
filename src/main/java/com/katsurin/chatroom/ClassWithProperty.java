package com.katsurin.chatroom;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ClassWithProperty {
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);
    private boolean isOnline = false;

    public ClassWithProperty() {
    }

    public void toggleState() {
        if (!this.isOnline) {
            changes.firePropertyChange("isOnline", isOnline, (this.isOnline = !this.isOnline));
        } else {
            changes.firePropertyChange("isOffline", isOnline, (this.isOnline = !this.isOnline));
        }

    }

    public boolean getIsOnline() { return this.isOnline; }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        changes.removePropertyChangeListener(l);
    }
}