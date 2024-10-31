package com.isima.dons.entities;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    @ManyToOne
    private User sender;

    @ManyToOne
    private User reciever;

    public Message(Long id, String message, User sender, User reciever) {
        this.id = id;
        this.message = message;
        this.sender = sender;
        this.reciever = reciever;
    }

    public Message() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setReciever(User reciever) {
        this.reciever = reciever;
    }
}
