package tn.esprit.coco.entity;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private Date timestamp;

    @ManyToOne
    @JoinColumn(name = "chatRoom_id")
    private ChatRoom chatRoom;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}

