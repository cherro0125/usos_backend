package org.fibi.usos.controller.course.degree;

import org.fibi.usos.annotation.RequireRole;
import org.fibi.usos.dto.course.degree.DegreeCourseRequestDto;
import org.fibi.usos.dto.course.degree.DegreeCourseResponseDto;
import org.fibi.usos.dto.news.NewsResponseDto;
import org.fibi.usos.entity.response.standard.StandardMessageResponse;
import org.fibi.usos.model.degree.DegreeCourseModel;
import org.fibi.usos.model.news.NewsModel;
import org.fibi.usos.model.user.UserRole;
import org.fibi.usos.service.degree.DegreeCourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/degreeCourses")
public class DegreeCourseController {

    private DegreeCourseService degreeCourseService;


    public DegreeCourseController(DegreeCourseService degreeCourseService) {
        this.degreeCourseService = degreeCourseService;
    }

    @RequireRole({UserRole.DEAN,UserRole.RECTOR})
    @GetMapping("/all")
    public ResponseEntity<List<DegreeCourseResponseDto>> getAll() {
        List<DegreeCourseResponseDto> res = new LinkedList<>();
        Optional<Iterable<DegreeCourseModel>> models = degreeCourseService.getAll();
        models.ifPresent( it -> it.forEach(i -> res.add(i.mapToResponseDto())));
        return ResponseEntity.ok(res);
    }

    @RequireRole({UserRole.DEAN,UserRole.RECTOR})
    @PostMapping("/add")
    public ResponseEntity<DegreeCourseResponseDto> createCourse(@RequestBody DegreeCourseRequestDto dto) {
        DegreeCourseResponseDto res = new DegreeCourseResponseDto();
        Optional<DegreeCourseModel> model = degreeCourseService.create(dto);
        if(model.isPresent()){
            res = model.get().mapToResponseDto();
        }
        return ResponseEntity.ok(res);
    }
    @RequireRole({UserRole.DEAN})
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<StandardMessageResponse> deleteDegreeCourse(@PathVariable("id") Long id) {
        StandardMessageResponse response = new StandardMessageResponse();
        if(degreeCourseService.delete(id))
            response.setMessage("Course deleted.");
        else
            response.setMessage("Course not found.");
        return ResponseEntity.ok(response);
    }

}
