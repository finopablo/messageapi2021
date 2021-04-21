package edu.utn.mailapi.persistence.mysql;

import edu.utn.mailapi.domain.Message;
import edu.utn.mailapi.domain.Recipient;
import edu.utn.mailapi.domain.RecipientType;
import edu.utn.mailapi.domain.User;
import edu.utn.mailapi.exceptions.DatabaseConnectionException;
import edu.utn.mailapi.persistence.MessageDao;
import edu.utn.mailapi.persistence.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository
public class MessageMySqlDao implements MessageDao {

    Connection conn;
    UserDao userDao;

    @Autowired
    public MessageMySqlDao(Connection conn, @Qualifier("userMySqlDao") UserDao userDao) {
        this.userDao = userDao;
        this.conn = conn;
    }


    @Override
    public List<Message> getReceivedByUser(User user) {
        return null;
    }

    @Override
    public List<Message> getSentByUser(User user) {
        return null;
    }

    @Override
    public Message save(Message message) {
        try {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement("INSERT INTO messages(date,message, from_username, subject ) values (now() , ? , ?, ? )", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, message.getBody());
            ps.setString(2, message.getFrom().getUserName());
            ps.setString(3, message.getSubject());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                message.setId(rs.getInt(1));
            } else {
                throw new DatabaseConnectionException();
            }

            for (Recipient recipient : message.getTo()) {
                PreparedStatement psRecipients = conn.prepareStatement("insert into recipients (recipient_type, message, user) values (?,?,?) ");
                psRecipients.setString(1, recipient.getType().getDescription());
                psRecipients.setInt(2, message.getId());
                psRecipients.setString(3, recipient.getUser().getUserName());
                psRecipients.execute();
            }
            conn.commit();
            return message;
        } catch (SQLException sqlException) {
            try {
                conn.rollback();
            } finally {
                throw new DatabaseConnectionException();
            }
        }
    }

    @Override
    public void remove(Message o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Message get(Integer id) {
        try {
            PreparedStatement ps = conn.prepareStatement("select * from messages where id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User userFrom = userDao.get(rs.getString("from_username"));
                List<Recipient> recipients = this.getRecipients(rs.getInt("id"));
                return new Message(rs.getInt("id"),
                        userFrom,
                        rs.getString("subject"),
                        rs.getString("message"),
                        rs.getDate("date").toLocalDate(),
                        recipients,
                        new ArrayList<>());
            } else {
                return null;
            }


        } catch (SQLException e) {
            throw new DatabaseConnectionException();
        }
    }

    private List<Recipient> getRecipients(Integer messageId) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("select * from recipients where message = ?");
        ps.setInt(1, messageId);
        ResultSet rs = ps.executeQuery();
        List<Recipient> recipients = new ArrayList<>();
        while (rs.next()) {
            recipients.add(new Recipient(userDao.get(rs.getString("user")), RecipientType.valueOf(rs.getString("recipient_type"))));
        }
        return recipients;
    }

    @Override
    public List<Message> getAll() {
        throw new UnsupportedOperationException();
    }
}
