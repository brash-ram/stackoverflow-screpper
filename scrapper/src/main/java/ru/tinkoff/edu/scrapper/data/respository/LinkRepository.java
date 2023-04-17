package ru.tinkoff.edu.scrapper.data.respository;

import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.scrapper.data.entity.Link;

import java.util.List;

@Repository
public interface LinkRepository {
    Link save(Link link);

    void remove(Long id);

    List<Link> findAll();
}