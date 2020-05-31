package org.fibi.usos.model.news;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.fibi.usos.dto.news.NewsResponseDto;
import org.fibi.usos.model.base.BaseIdentityModel;
import org.fibi.usos.model.user.UserModel;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name = "News")
@Table(name = "newses")
@Inheritance(
        strategy = InheritanceType.JOINED
)
public class NewsModel extends BaseIdentityModel {
    private String title;
    private String description;
    @ManyToOne
    private UserModel createdBy;
    private Date createdAt;

    public NewsResponseDto mapToDto(){
        NewsResponseDto dto = new NewsResponseDto();
        dto.setId(getId());
        dto.setDescription(description);
        dto.setTitle(title);
        if(createdBy != null)
            dto.setCreatedBy(createdBy.mapToDto());
        return dto;
    }
}
