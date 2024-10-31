package com.isima.dons.services;

import com.isima.dons.entities.Message;

import java.util.List;

public interface MessageService {

    List<Message> getAllMessages();

    Message getMessageById(Long id);

    Message createMessage(Message message);

    Message updateMessage(Long id, Message updatedMessage);

    void deleteMessage(Long id);
}
