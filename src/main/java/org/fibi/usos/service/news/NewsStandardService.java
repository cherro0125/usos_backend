package org.fibi.usos.service.news;

import org.fibi.usos.dto.news.NewsRequestDto;
import org.fibi.usos.dto.news.NewsResponseDto;
import org.fibi.usos.model.news.NewsModel;
import org.fibi.usos.repository.news.NewsRepository;
import org.fibi.usos.repository.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Service
public class NewsStandardService implements NewsService {

    private NewsRepository newsRepository;
    private UserRepository userRepository;

    public NewsStandardService(NewsRepository newsRepository, UserRepository userRepository) {
        this.newsRepository = newsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<NewsModel> getById(Long id){
        return newsRepository.findById(id);
    }

    @Override
    public Optional<Collection<NewsModel>> getByCreatorId(Long creatorId) {
        return newsRepository.findNewsModelsByCreatedById(creatorId);
    }

    @Override
    public Optional<Iterable<NewsModel>> getAll() {
        Iterable<NewsModel> models = newsRepository.findAll();
        return models.iterator().hasNext() ? Optional.of(models) : Optional.empty();
    }

    @Override
    public Optional<NewsModel> createNews(NewsRequestDto dto) {
        NewsModel news = new NewsModel();
        userRepository.findById(dto.getCreatedById()).ifPresent(news::setCreatedBy);
        news.setDescription(dto.getDescription());
        news.setTitle(dto.getTitle());
        news.setCreatedAt(new Date());
        return Optional.of(newsRepository.save(news));
    }

}
