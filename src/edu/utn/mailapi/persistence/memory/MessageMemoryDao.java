package edu.utn.mailapi.persistence.memory;


import edu.utn.mailapi.domain.Message;
import edu.utn.mailapi.domain.User;
import edu.utn.mailapi.persistence.MessageDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class MessageMemoryDao implements MessageDao {
    List<Message> messageList = new ArrayList<>();

    @Override
    public List<Message> getReceivedByUser(User user) {
        return messageList.stream().filter(message -> message.getTo().stream().filter(recipient -> recipient.getUser().equals(user)).count() != 0).collect(Collectors.toList());
    }

    @Override
    public List<Message> getSentByUser(User user) {
        return messageList.stream().filter(message -> message.getFrom().equals(user)).collect(Collectors.toList());
    }

    @Override
    public Message save(Message o) {
        o.setUuid(UUID.randomUUID().toString());
        messageList.add(o);
        return o;
    }

    @Override
    public void remove(Message o) {
        messageList.remove(o);
    }

    @Override
    public Message get(String s) {
        Optional<Message> optMessage = messageList.stream().filter(message -> message.getUuid().equals(s)).findFirst();
        return optMessage.orElseGet(null);
    }

    @Override
    public List<Message> getAll() {
        throw new UnsupportedOperationException("GetAll is not implemented for Messages! Please go to UTN to learn how to develop");
    }
}
