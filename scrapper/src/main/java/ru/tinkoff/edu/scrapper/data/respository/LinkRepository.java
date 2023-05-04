package ru.tinkoff.edu.scrapper.data.respository;

import ru.tinkoff.edu.scrapper.data.entity.Link;

import java.sql.Timestamp;
import java.util.List;

public interface LinkRepository {
    Link save(Link link);

    void remove(Long id);

    List<Link> findAll();

    void updateLastUpdate(Long id, Timestamp timestamp);

    List<Link> findAllBefore(Timestamp borderTime);
}
