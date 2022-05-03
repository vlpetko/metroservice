package ru.vlpetko.metroservice.model.criteria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MetroLineCriteria {

    private Integer pageNo;

    private Integer pageSize;

    private String sortBy;
}
