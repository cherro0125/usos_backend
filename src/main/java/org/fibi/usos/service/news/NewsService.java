package org.fibi.usos.service.news;

import org.fibi.usos.dto.news.NewsRequestDto;
import org.fibi.usos.dto.news.NewsResponseDto;
import org.fibi.usos.model.news.NewsModel;

import java.util.Collection;
import java.util.Optional;

public interface NewsService {
    Optional<NewsModel> getById(Long id);
    Optional<Collection<NewsModel>> getByCreatorId(Long creatorId);
    Optional<Iterable<NewsModel>> getAll();
    Optional<NewsModel> createNews(NewsRequestDto dto);

}
