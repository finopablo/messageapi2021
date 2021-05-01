package edu.utn.mailapi.persistence;

import edu.utn.mailapi.domain.Message;
import edu.utn.mailapi.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageDao extends CrudRepository<Message, Integer> {
/*    List<Message> getReceivedByUser(User user);
    List<Message> getSentByUser(User user);*/

    List<Message> findAllByFrom(User user);

}
