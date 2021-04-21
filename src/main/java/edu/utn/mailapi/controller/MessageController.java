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
        return messageDao.save(new Message(from, subject, body, LocalDate.now(), to, attachments));
    }

    public void remove(Message message) {
        messageDao.remove(message);
    }

    public List<Message> getReceivedByUser(User user) {
        return messageDao.getReceivedByUser(user);
    }


    public List<Message> getSentByUser(User user) {
        return messageDao.getSentByUser(user);
    }


    public Message getMessageById(Integer id) {
        return messageDao.get(id);
    }

}
