package ru.tinkoff.edu.scrapper.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "chats")
@SequenceGenerator(name = "chats_id_seq", sequenceName = "chats_id_seq", allocationSize=1)
public class Chat {

    @Id
    @GeneratedValue(generator = "chats_id_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "chat_id")
    private Long chatId;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "links",
            joinColumns = @JoinColumn(name = "chat"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    private List<Link> links;
}