package com.example.registrationlogindemo.repository;

import com.example.registrationlogindemo.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Override
    Message save(Message message);
}
