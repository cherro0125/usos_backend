package org.fibi.usos.service.news;

import org.apache.commons.lang3.StringUtils;
import org.fibi.usos.dto.news.NewsRequestDto;
import org.fibi.usos.model.news.NewsModel;
import org.fibi.usos.model.user.UserModel;
import org.fibi.usos.repository.news.NewsRepository;
import org.fibi.usos.repository.user.UserRepository;
import org.fibi.usos.service.email.EmailSenderService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Service
public class NewsStandardService implements NewsService {

    private NewsRepository newsRepository;
    private UserRepository userRepository;
    private EmailSenderService emailSenderService;
    private final TemplateEngine templateEngine;

    public NewsStandardService(NewsRepository newsRepository, UserRepository userRepository, EmailSenderService emailSenderService, TemplateEngine templateEngine) {
        this.newsRepository = newsRepository;
        this.userRepository = userRepository;
        this.emailSenderService = emailSenderService;
        this.templateEngine = templateEngine;
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

    @Async("threadPoolTaskExecutor")
    @Override
    public void notifyByEmail(NewsModel newsModel) {
        Iterable<UserModel> users = userRepository.findAll();
        for(UserModel user : users){
            String userMail = user.getEmail();
            if(StringUtils.isBlank(userMail))
                continue;
            Context context = new Context();
            context.setVariable("title","News");
            context.setVariable("newsTitle",newsModel.getTitle());
            context.setVariable("firstName",user.getFirstName() != null ? user.getFirstName() : "" );
            context.setVariable("lastName",user.getLastName() != null ? user.getLastName() : "");
            context.setVariable("creatorFirstName",newsModel.getCreatedBy().getFirstName());
            context.setVariable("creatorLastName",newsModel.getCreatedBy().getLastName());
            String body = templateEngine.process("template",context);
            emailSenderService.sendEmail(userMail,"USOS - Read new news",body);
        }
    }

}
