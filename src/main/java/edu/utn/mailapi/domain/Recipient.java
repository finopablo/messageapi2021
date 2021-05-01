package edu.utn.mailapi.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "recipients")
public class Recipient {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    @JoinColumn(name ="user", nullable = false, updatable = false)
    User user;

    @Enumerated(EnumType.STRING)
    RecipientType type;


    @ManyToOne
    @JoinColumn(name ="message", nullable = false, updatable = false)
    Message message;

    public Recipient(User user, RecipientType type) {
        this.user = user;
        this.type = type;
    }


    @Override
    public String toString() {
        return "Recipient{" +
                "id=" + id +
                ", user=" + user +
                ", type=" + type +
                '}';
    }

}
