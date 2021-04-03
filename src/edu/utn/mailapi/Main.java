package edu.utn.mailapi;

import edu.utn.mailapi.controller.MessageController;
import edu.utn.mailapi.controller.UserController;
import edu.utn.mailapi.domain.Recipient;
import edu.utn.mailapi.domain.RecipientType;
import edu.utn.mailapi.domain.User;
import edu.utn.mailapi.exceptions.InvalidUserPasswordException;
import edu.utn.mailapi.persistence.MessageDao;
import edu.utn.mailapi.persistence.UserDao;
import edu.utn.mailapi.persistence.memory.MessageMemoryDao;
import edu.utn.mailapi.persistence.memory.UserMemoryDao;
import edu.utn.mailapi.persistence.mysql.UserMySqlDao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static UserDao userDao;
    static MessageDao messageDao;

    static UserController userController;
    static MessageController messageController;


    public static void main(String[] args) {
        userDao = new UserMySqlDao();
        messageDao = new MessageMemoryDao();

        userController = new UserController(userDao);
        messageController = new MessageController(messageDao);
        registerUsers();

       // User u = userController.get("a' or '1'='1");

        System.exit(1);
        try {
            User loggedUser = userController.login("finopablo", "1234");
            //ENVIO MENSAJE A MI MISMO
            List<Recipient> to = new ArrayList<>();
            to.add(new Recipient(loggedUser, RecipientType.TO));
            to.add(new Recipient(userController.get("gianottigerman"), RecipientType.BCC));
            messageController.send(loggedUser, to , "Hola pero!!", "ola pero2 ", null );


            //ENVIO A GERMAN !! YAY!
            List<Recipient> to2 = new ArrayList<>();
            to2.add(new Recipient(userController.get("gianottigerman"), RecipientType.TO));
            messageController.send(loggedUser, to2, "Otro mensaje", "Otro mensaje pero mas lindo", null);
            //BUSCO MI INBOX
            messageController.getSentByUser(loggedUser).forEach(o -> System.out.println(o));
        } catch (InvalidUserPasswordException e) {
            System.out.println(e.getMessage());
        }

    }


    public static void registerUsers() {
        /**Registrar ususarios*/
        User user1 = userController.register("finopablo", "1234", "Pablo2", "Fino2", LocalDate.of(1980, 05, 17));
        User user2 = userController.register("gianottigerman", "1234", "German2", "Gianotti2", LocalDate.of(1980, 05, 17));
        User user3 = userController.register("davidnavarro", "1234", "David2", "Navarro2", LocalDate.of(1980, 05, 17));
    }



}
