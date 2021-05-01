package edu.utn.mailapi.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer id;

    @Column
    String subject;
    @Column(name = "message", length = 255)
    String body;
    @Column
    LocalDate date;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "message", fetch = FetchType.EAGER)
    List<Recipient> to;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "message",  fetch = FetchType.LAZY)
    List<Attachment> attachments;

    @ManyToOne
    @JoinColumn(name = "from_", nullable = false, updatable = false)
    User from;



    public void addAttachment(Attachment attachment) {
        attachments = Optional.ofNullable(attachments).orElse(new ArrayList<>());
        attachments.add(attachment);
        attachment.setMessage(this);
    }


    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
        this.attachments.forEach( e-> e.setMessage(this));
    }




    public void addRecipient(Recipient recipient) {
        to = Optional.ofNullable(to).orElse(new ArrayList<>());
        to.add(recipient);
        recipient.setMessage(this);
    }


    public void setTo(List<Recipient> recipients) {
        this.to = recipients;
        this.to.forEach( e-> e.setMessage(this));
    }


}
