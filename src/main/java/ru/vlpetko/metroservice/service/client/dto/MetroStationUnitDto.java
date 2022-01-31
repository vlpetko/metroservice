package ru.vlpetko.metroservice.service.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MetroStationUnitDto {

    @JsonProperty("global_id")
    private int globalId;

    @JsonProperty("Number")
    private int number;

    @JsonProperty("Cells")
    private StationDto stationDto;

    @Override
    public String toString() {
        return "MetroStationUnitDto{"
                + "globalId=" + globalId
                + ", number=" + number
                + ", stationDto=" + stationDto
                + '}';
    }
}
