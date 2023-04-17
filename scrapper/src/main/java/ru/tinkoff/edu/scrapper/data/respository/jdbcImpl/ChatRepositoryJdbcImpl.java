package ru.tinkoff.edu.scrapper.data.respository.jdbcImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.scrapper.data.entity.Chat;
import ru.tinkoff.edu.scrapper.data.entity.Link;
import ru.tinkoff.edu.scrapper.data.respository.ChatRepository;
import ru.tinkoff.edu.scrapper.utils.JdbcMapper;

import java.net.URISyntaxException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
@RequiredArgsConstructor
@Transactional
public class ChatRepositoryJdbcImpl implements ChatRepository {

    private final String INSERT = "INSERT INTO chats (chat_id) VALUES (?)";
    private final String DELETE = "DELETE FROM chats WHERE id = ?";
    private final String FIND_BY_ID = "SELECT c.id id, c.chat_id chat_id, l.id link_id, l.url url" +
            " FROM chats AS c LEFT JOIN links AS l ON c.id = l.chat" +
            " WHERE c.id = ?";
    private final String FIND_BY_CHAT_ID = "SELECT c.id id, c.chat_id chat_id, l.id link_id, l.url url" +
            " FROM chats AS c LEFT JOIN links AS l ON c.id = l.chat" +
            " WHERE c.chat_id = ?";
    private final String FIND_ALL = "SELECT c.id id, c.chat_id chat_id, l.id link_id, l.url url" +
            " FROM chats AS c LEFT JOIN links AS l ON c.id = l.chat";

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Chat findByChatId(Long tgChatId) {
        return jdbcTemplate.query(FIND_BY_CHAT_ID, rs -> {
            return mapListChats(rs).get(0);
        }, tgChatId);
    }

    @Override
    public Chat findById(Long id) {
        return jdbcTemplate.query(FIND_BY_ID, rs -> {
            return mapListChats(rs).get(0);
        }, id);
    }

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
        return jdbcTemplate.query(FIND_ALL, this::mapListChats);
    }

    private List<Chat> mapListChats(ResultSet rs) throws SQLException {
        Map<Long, Chat> resultMap = new LinkedHashMap<>();

        while (rs.next()) {

            Long id = rs.getLong("id");

            Chat chat = resultMap.getOrDefault(id, JdbcMapper.mapChat(rs));

            try {
                if (rs.getString("url") != null) {
                    Set<Link> links = chat.getLinks();
                    links.add(JdbcMapper.mapLink(rs).setChat(chat));
                }
            } catch (URISyntaxException e) {
                e.printStackTrace();
                continue;
            }

            resultMap.put(id, chat);
        }

        return new ArrayList<>(resultMap.values());
    }
}
