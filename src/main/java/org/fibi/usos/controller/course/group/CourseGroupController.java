package org.fibi.usos.controller.course.group;

import org.fibi.usos.dto.course.group.CourseGroupResponseDto;
import org.fibi.usos.model.course.group.CourseGroupModel;
import org.fibi.usos.service.course.group.CourseGroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class CourseGroupController {

    private CourseGroupService courseGroupService;

    public CourseGroupController(CourseGroupService courseGroupService) {
        this.courseGroupService = courseGroupService;
    }

    @RequestMapping(value = "/course/group/{leaderId}", method = RequestMethod.GET)
    public ResponseEntity<Collection<CourseGroupResponseDto>> getCourseGroupByLeaderId(@PathVariable("leaderId") long leaderId) {
        Optional<Collection<CourseGroupModel>> courseGroups = courseGroupService.getAllByLeaderId(leaderId);
        return courseGroups.<ResponseEntity<Collection<CourseGroupResponseDto>>>map(courseGroupModels -> ResponseEntity.ok(courseGroupModels.stream().map(CourseGroupModel::mapToResponseDto).collect(Collectors.toSet()))).orElseGet(() -> ResponseEntity.ok(null));
    }
}
