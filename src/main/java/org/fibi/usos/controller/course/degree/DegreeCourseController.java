package org.fibi.usos.controller.course.degree;

import org.fibi.usos.annotation.RequireRole;
import org.fibi.usos.dto.course.degree.DegreeCourseResponseDto;
import org.fibi.usos.dto.news.NewsResponseDto;
import org.fibi.usos.model.degree.DegreeCourseModel;
import org.fibi.usos.model.news.NewsModel;
import org.fibi.usos.model.user.UserRole;
import org.fibi.usos.service.degree.DegreeCourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RestController
public class DegreeCourseController {
    private DegreeCourseService degreeCourseService;


    public DegreeCourseController(DegreeCourseService degreeCourseService) {
        this.degreeCourseService = degreeCourseService;
    }

    @RequireRole({UserRole.DEAN,UserRole.RECTOR})
    @GetMapping("/degreeCourses")
    public ResponseEntity<List<DegreeCourseResponseDto>> getAll() {
        List<DegreeCourseResponseDto> res = new LinkedList<>();
        Optional<Iterable<DegreeCourseModel>> models = degreeCourseService.getAll();
        models.ifPresent( it -> it.forEach(i -> res.add(i.mapToResponseDto())));
        return ResponseEntity.ok(res);
    }

}
