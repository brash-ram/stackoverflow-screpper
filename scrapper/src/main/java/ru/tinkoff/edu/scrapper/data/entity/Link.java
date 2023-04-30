package ru.tinkoff.edu.scrapper.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.net.URI;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "links")
@SequenceGenerator(name = "links_id_seq", sequenceName = "links_id_seq", allocationSize=1)
public class Link {

    @Id
    @GeneratedValue(generator = "links_id_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "url")
    private URI url;

    @ManyToOne()
    @JoinColumn(name = "chat")
    private Chat chat;

    @Column(name = "last_update")
    private Timestamp lastUpdate;
}
