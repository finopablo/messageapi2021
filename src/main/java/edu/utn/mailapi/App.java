package edu.utn.mailapi;

import edu.utn.mailapi.controller.MessageController;
import edu.utn.mailapi.controller.UserController;
import edu.utn.mailapi.domain.Message;
import edu.utn.mailapi.domain.Recipient;
import edu.utn.mailapi.domain.RecipientType;
import edu.utn.mailapi.domain.User;
import lombok.SneakyThrows;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@SpringBootApplication
public class App implements CommandLineRunner {


    @Autowired
    private UserController userController;

    @Autowired
    private MessageController messageController;


    public static void main(String[] args) throws Exception {
        //disabled banner, don't want to see the spring logo
        SpringApplication app = new SpringApplication(App.class);
        app.run(args);
    }

    @SneakyThrows
    @Override
    public void run(String... args) throws Exception {
        registerUsers();
        User loggedUser = userController.login("finopablo", "1234");

        List<Recipient> to = new ArrayList<>();
        to.add(Recipient.builder().user(loggedUser).type(RecipientType.TO).build());
        to.add(Recipient.builder().user(userController.get("gianottigerman")).type(RecipientType.BCC).build());
        Message m = messageController.send(loggedUser, to, "Este mail es para la gente que esta mirando ", "Mail Loko", null);

        Message m2 = messageController.getMessageById(m.getId());
        log.info("Mensaje {}", m);
        log.info("Mensaje attachments {}", m.getAttachments());
        log.info("Aplicacion inicializada !!");
    }

    public void registerUsers() {
        /**Registrar ususarios*/
        userController.register("finopablo", "1234", "Pablo2", "Fino2", LocalDate.of(1980, 05, 17));
        userController.register("gianottigerman", "1234", "German2", "Gianotti2", LocalDate.of(1980, 05, 17));
        userController.register("davidnavarro", "1234", "David2", "Navarro2", LocalDate.of(1980, 05, 17));
    }


}
