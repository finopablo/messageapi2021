package edu.utn.mailapi.domain;

import java.util.Objects;

public class Recipient {
    User user;
    RecipientType type;

    public Recipient(User user, RecipientType type) {
        this.user = user;
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RecipientType getType() {
        return type;
    }

    public void setType(RecipientType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipient recipient = (Recipient) o;
        return Objects.equals(user, recipient.user) &&
                type == recipient.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, type);
    }

    @Override
    public String toString() {
        return "Recipient{" +
                "user=" + user +
                ", type=" + type +
                '}';
    }
}
