package edu.utn.mailapi.controller;


import edu.utn.mailapi.domain.Attachment;
import edu.utn.mailapi.domain.Message;
import edu.utn.mailapi.domain.Recipient;
import edu.utn.mailapi.domain.User;
import edu.utn.mailapi.persistence.MessageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;


@Controller
public class MessageController {


    MessageDao messageDao;

    @Autowired
    public MessageController(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public Message send(Message message) {
        return messageDao.save(message);
    }

    public Message send(User from, List<Recipient> to, String body, String subject, List<Attachment> attachments) {
        Message m = Message.builder().from(from).subject(subject).body(body).date(LocalDate.now()).attachments(attachments).build();
        m.setTo(to);

        return messageDao.save(m);
    }

    public void remove(Message message) {
        messageDao.delete(message);
    }

    public List<Message> getReceivedByUser(User user) {
        return null; // messageDao.getReceivedByUser(user);
    }


    public List<Message> getSentByUser(User user) {
        return messageDao.findAllByFrom(user);
    }


    public Message getMessageById(Integer id) {
        return messageDao.findById(id).get();
    }

}
