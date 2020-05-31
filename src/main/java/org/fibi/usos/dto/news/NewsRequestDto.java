package org.fibi.usos.dto.news;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fibi.usos.dto.user.UserDto;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsRequestDto {
    private Long id;
    private String title;
    private String description;
    private Long createdById;
}
