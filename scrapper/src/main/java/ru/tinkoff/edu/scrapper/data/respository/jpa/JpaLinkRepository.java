package ru.tinkoff.edu.scrapper.data.respository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.scrapper.data.entity.Link;
import ru.tinkoff.edu.scrapper.data.respository.LinkRepository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface JpaLinkRepository extends JpaRepository<Link, Long> {
    List<Link> findAllByLastUpdateLessThan(Timestamp timestamp);
}