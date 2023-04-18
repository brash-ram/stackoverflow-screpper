package ru.tinkoff.edu.scrapper.data.respository.jdbcImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.scrapper.data.entity.Chat;
import ru.tinkoff.edu.scrapper.data.entity.Link;
import ru.tinkoff.edu.scrapper.data.respository.LinkRepository;
import ru.tinkoff.edu.scrapper.utils.JdbcMapper;

import java.net.URISyntaxException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class LinkRepositoryJdbcImpl implements LinkRepository {

    private final String INSERT = "INSERT INTO links (url, chat, last_update) VALUES (?, ?, ?)";
    private final String DELETE = "DELETE FROM links WHERE id = ?";
    private final String FIND_ALL = "SELECT c.id id, c.chat_id chat_id, l.id link_id, l.url url, l.last_update last_update" +
            " FROM chats AS c RIGHT JOIN links AS l ON c.id = l.chat";

    private final String UPDATE_LAST_UPDATE = "UPDATE links" +
            " SET last_update = ?" +
            " WHERE id = ?";

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void updateLastUpdate(Long id, Timestamp timestamp) {
        jdbcTemplate.update(UPDATE_LAST_UPDATE, timestamp, id);
    }

    @Override
    public Link save(Link link) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(INSERT, new String[] {"id"});
            ps.setString(1, link.getUrl().toString());
            ps.setObject(2, link.getChat() != null ? link.getChat().getId() : null);
            ps.setTimestamp(3, link.getLastUpdate());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            return link.setId((long) keyHolder.getKey());
        } else {
            return link;
        }

    }

    @Override
    public void remove(Long id) {
        jdbcTemplate.update(DELETE, id);
    }

    @Override
    public List<Link> findAll() {
        return jdbcTemplate.query(FIND_ALL, this::mapListLinks);
    }

    private List<Link> mapListLinks(ResultSet rs) {
        try {
            List<Link> links = new ArrayList<>();
            while (rs.next()) {
                var test = rs.getLong("id");
                links.add(JdbcMapper.mapLink(rs).setChat(JdbcMapper.mapChat(rs)));
            }
            return links;

        } catch (URISyntaxException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
