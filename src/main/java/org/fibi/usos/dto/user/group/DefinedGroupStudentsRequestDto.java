package org.fibi.usos.dto.user.group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DefinedGroupStudentsRequestDto {
    private Long definedGroupId;
    private Long[] studentIds;
}