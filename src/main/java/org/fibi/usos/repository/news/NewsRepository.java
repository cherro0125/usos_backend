package org.fibi.usos.repository.news;

import org.fibi.usos.model.news.NewsModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface NewsRepository extends CrudRepository<NewsModel,Long> {
    Optional<Collection<NewsModel>> findNewsModelsByCreatedById(Long creatorId);

}
