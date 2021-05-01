package edu.utn.mailapi.domain;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Entity
@Table(name ="attachments")
public class Attachment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer id;

    @Column
    String name;
    @Column
    String path;
    @Column
    String extension;
    @ManyToOne
    @JoinColumn(name ="id_message", nullable = false, updatable = false)
    Message message;

    @Override
    public String toString() {
        return "Attachment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", extension='" + extension + '\'' +
                '}';
    }
}
