package edu.utn.mailapi.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Entity
@Table(name = "users")
public class User {
    @Id
    String username;

    @Column
    String password;
    @Column
    String name;

    @Column
    String lastName;

    @Column
    LocalDate birthDate;

}
