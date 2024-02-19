package tn.esprit.coco.entity;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomName;

    @ManyToMany(mappedBy = "rooms")
    private List<User> users;

    @OneToMany(mappedBy = "room")
    private List<Message> messages;

}
