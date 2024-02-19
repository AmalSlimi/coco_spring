package tn.esprit.coco.entity;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomName;

    @ManyToMany(mappedBy = "chatRooms")
    private List<User> users;

    @OneToMany(mappedBy = "chatRoom")
    private List<Message> messages;

}
