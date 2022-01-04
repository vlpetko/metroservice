package ru.vlpetko.metroservice.service.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class MetroLineUnitDtoJson {

    @JsonProperty("global_id")
    private int globalId;

    @JsonProperty("Number")
    private int number;

    @JsonProperty("Cells")
    private ru.vlpetko.metroservice.service.client.dto.MetroLineDto metroLineDto;

    @Override
    public String toString() {
        return "MetroLineUnitDtoJson{"
                + "global_id=" + globalId
                + ", Number=" + number
                + ", metroLineDto=" + metroLineDto
                + '}';
    }
}
