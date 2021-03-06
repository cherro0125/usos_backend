package org.fibi.usos.controller.news;

import org.fibi.usos.annotation.RequireRole;
import org.fibi.usos.dto.news.NewsRequestDto;
import org.fibi.usos.dto.news.NewsResponseDto;
import org.fibi.usos.model.news.NewsModel;
import org.fibi.usos.model.user.UserRole;
import org.fibi.usos.service.email.EmailSenderService;
import org.fibi.usos.service.news.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/news")
public class NewsController {

    private NewsService newsService;


    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @RequireRole({UserRole.LECTURER,UserRole.DEAN,UserRole.RECTOR})
    @PostMapping("/add")
    public ResponseEntity<NewsResponseDto> createNews(@RequestBody NewsRequestDto dto) {
        NewsResponseDto res = new NewsResponseDto();
        Optional<NewsModel> model = newsService.createNews(dto);
        if(model.isPresent()){
            newsService.notifyByEmail(model.get());
            res = model.get().mapToDto();
        }
        return ResponseEntity.ok(res);
    }

    @RequireRole({UserRole.LECTURER,UserRole.DEAN,UserRole.RECTOR,UserRole.STUDENT,UserRole.PORTER})
    @GetMapping("/all")
    public ResponseEntity<List<NewsResponseDto>> getAll() {
        List<NewsResponseDto> res = new LinkedList<>();
        Optional<Iterable<NewsModel>> models = newsService.getAll();
        models.ifPresent( it -> it.forEach(i -> res.add(i.mapToDto())));
        return ResponseEntity.ok(res);
    }

    @RequireRole({UserRole.LECTURER,UserRole.DEAN,UserRole.RECTOR,UserRole.STUDENT,UserRole.PORTER})
    @GetMapping("/{id}")
    public ResponseEntity<NewsResponseDto> getById(@PathVariable(name = "id")Long id){
        AtomicReference<NewsResponseDto> res = new AtomicReference<>(new NewsResponseDto());
        newsService.getById(id).ifPresent( model -> res.set(model.mapToDto()));
        return ResponseEntity.ok(res.get());
    }

    @RequireRole({UserRole.LECTURER,UserRole.DEAN,UserRole.RECTOR,UserRole.STUDENT,UserRole.PORTER})
    @GetMapping("/user/{id}")
    public ResponseEntity<List<NewsResponseDto>> getByCreatorId(@PathVariable(name = "id")Long id){
        List<NewsResponseDto> res = new ArrayList<>();
        newsService.getByCreatorId(id).ifPresent( it -> it.forEach( i -> res.add(i.mapToDto())));
        return ResponseEntity.ok(res);
    }


}
