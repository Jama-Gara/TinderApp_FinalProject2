package com.example.registrationlogindemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "senderId", referencedColumnName = "id")
    private User sender;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiverId", referencedColumnName = "id")
    private Favorites receiver;
    @Column(name = "msg_txt")
    private String msg_text;
    @ManyToMany(mappedBy="messages")
    private List<Favorites> favorites;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id) && Objects.equals(sender, message.sender) && Objects.equals(receiver, message.receiver) && Objects.equals(msg_text, message.msg_text) && Objects.equals(favorites, message.favorites);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, sender, receiver, msg_text, favorites);
    }
    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", msg_text='" + msg_text + '\'' +
                ", favorites=" + favorites +
                '}';
    }
}
