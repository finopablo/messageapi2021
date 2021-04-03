package edu.utn.mailapi.persistence;

import edu.utn.mailapi.domain.Message;
import edu.utn.mailapi.domain.User;

import java.util.List;

public interface MessageDao extends Dao<Message, String> {
    List<Message> getReceivedByUser(User user);
    List<Message> getSentByUser(User user);
}
