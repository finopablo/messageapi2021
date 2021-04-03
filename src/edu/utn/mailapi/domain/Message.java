package edu.utn.mailapi.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Message {

    String uuid;
    User from;
    String subject;
    String body;
    LocalDate date;
    List<Recipient> to;
    List<Attachment> attachments;

    public Message(String uuid, User from, String subject, String body, LocalDate date, List<Recipient> to, List<Attachment> attachments) {
        this.uuid = uuid;
        this.from = from;
        this.subject = subject;
        this.body = body;
        this.date = date;
        this.to = to;
        this.attachments = attachments;
    }

    public Message(User from, String subject, String body, LocalDate date, List<Recipient> to, List<Attachment> attachments) {
        this.from = from;
        this.subject = subject;
        this.body = body;
        this.date = date;
        this.to = to;
        this.attachments = attachments;
    }


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Recipient> getTo() {
        return to;
    }

    public void setTo(List<Recipient> to) {
        this.to = to;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(uuid, message.uuid) &&
                Objects.equals(from, message.from) &&
                Objects.equals(subject, message.subject) &&
                Objects.equals(body, message.body) &&
                Objects.equals(date, message.date) &&
                Objects.equals(to, message.to) &&
                Objects.equals(attachments, message.attachments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, from, subject, body, date, to, attachments);
    }

    @Override
    public String toString() {
        return "Message{" +
                "uuid='" + uuid + '\'' +
                ", from=" + from +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", date=" + date +
                ", to=" + to +
                ", attachments=" + attachments +
                '}';
    }
}
