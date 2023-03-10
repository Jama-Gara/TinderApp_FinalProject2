package com.example.registrationlogindemo.entity;

import lombok.*;
import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "favorites")
public class Favorites implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user1_id", referencedColumnName = "id")
    private User likedBy;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user2_id", referencedColumnName = "id")
    private User likedUser;
    @ManyToMany(mappedBy="favorites")
    private List<User> users;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "messages_favorites",
            joinColumns = { @JoinColumn(name = "user2_id") },
            inverseJoinColumns = { @JoinColumn(name = "msg_id") }
    )
    private List<Message> messages = new ArrayList<>();

    public Favorites(String status, User likedBy, User likedUser) {
        this.status = status;
        this.likedBy = likedBy;
        this.likedUser = likedUser;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Favorites favorites = (Favorites) o;
        return Objects.equals(id, favorites.id) && Objects.equals(status, favorites.status) && Objects.equals(likedBy, favorites.likedBy) && Objects.equals(likedUser, favorites.likedUser) && Objects.equals(users, favorites.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, likedBy, likedUser, users);
    }

    @Override
    public String toString() {
        return "Favorites{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", likedBy=" + likedBy +
                ", likedUser=" + likedUser +
                ", users=" + users +
                '}';
    }
}
