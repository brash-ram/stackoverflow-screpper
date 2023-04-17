package ru.tinkoff.edu.scrapper.service.jdbcImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.scrapper.data.entity.Link;
import ru.tinkoff.edu.scrapper.data.respository.LinkRepository;
import ru.tinkoff.edu.scrapper.service.ChatService;
import ru.tinkoff.edu.scrapper.service.LinkService;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JdbcLinkService implements LinkService {

    private final LinkRepository linkRepository;
    private final ChatService jdbcChatService;

    @Override
    public Link add(long tgChatId, URI url) {
        return linkRepository.save(new Link()
                .setUrl(url)
                .setChat(jdbcChatService.getByChatId(tgChatId))
        );
    }

    @Override
    public Link remove(long tgChatId, URI url) {
        Optional<Link> resultLink = jdbcChatService.getByChatId(tgChatId).getLinks()
                .stream()
                .filter(link -> url.equals(link.getUrl()))
                .findFirst();
        if (resultLink.isPresent()) {
            Link link = resultLink.get();
            linkRepository.remove(link.getId());
            return link;
        } else {
            throw new RuntimeException("URL not found");
        }
    }

    @Override
    public Collection<Link> listAll(long tgChatId) {
        return linkRepository.findAllByChat(jdbcChatService.getByChatId(tgChatId));
    }
}
