package com.vkopendoh.lunchapp.to;

import java.time.LocalDateTime;
import java.util.Objects;

public class MessageTo {
    private String message;
    private LocalDateTime created;

    public MessageTo() {
    }

    public MessageTo(String message) {
        this.message = message;
        this.created = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MessageTo)) return false;
        MessageTo messageTo = (MessageTo) o;
        return Objects.equals(message, messageTo.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public String toString() {
        return "MessageTo{" +
                "message='" + message + '\'' +
                ", created=" + created +
                '}';
    }
}
