package org.fibi.usos.dto.user.group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DefinedGroupRequestDto {
    private String name;
    private String description;
    private Long degreeCourseId;
}