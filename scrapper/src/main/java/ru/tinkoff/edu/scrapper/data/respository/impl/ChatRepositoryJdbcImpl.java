package ru.tinkoff.edu.scrapper.data.respository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.scrapper.data.entity.Chat;
import ru.tinkoff.edu.scrapper.data.entity.Link;
import ru.tinkoff.edu.scrapper.data.respository.ChatRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;

@Repository
@RequiredArgsConstructor
@Transactional
public class ChatRepositoryJdbcImpl implements ChatRepository {

    private final String INSERT = "INSERT INTO chats (chat_id) VALUES (?)";
    private final String DELETE = "DELETE FROM chats WHERE id = ?";
    private final String FIND_ALL = "SELECT c.id id, c.chat_id chat_id, l.id link_id, l.url url" +
            " FROM chats AS c LEFT JOIN links AS l ON c.id = l.chat";

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Chat save(Chat chat) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(INSERT, new String[] {"id"});
            ps.setLong(1, chat.getChatId());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            return chat.setId((long) keyHolder.getKey());
        } else {
            return chat;
        }
    }

    @Override
    public void remove(Long id) {
        jdbcTemplate.update(DELETE, id);
    }

    @Override
    public List<Chat> findAll() {
        return jdbcTemplate.query(FIND_ALL, rs -> {

            Map<Long, Chat> resultMap = new LinkedHashMap<>();

            while (rs.next()) {

                Long id = rs.getLong("id");

                Chat chat = resultMap.getOrDefault(id, new Chat()
                        .setId(id)
                        .setChatId(rs.getLong("chat_id"))
                        .setLinks(new HashSet<>()));

                try {
                    if (rs.getString("url") != null) {
                        Set<Link> links = chat.getLinks();
                        links.add(new Link()
                                .setId(rs.getLong("link_id"))
                                .setChat(chat)
                                .setUrl(new URI(rs.getString("url")))
                                .setLastUpdate(rs.getTimestamp("last_update"))
                        );
                    }
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                    continue;
                }

                resultMap.put(id, chat);
            }

            return new ArrayList<>(resultMap.values());
        });
    }
}
