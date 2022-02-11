package ru.vlpetko.metroservice.service.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class MetroLineDto {

    @JsonProperty("Line")
    private String line;

    @JsonProperty("NumberOfStations")
    private int numberOfStations;

    @JsonProperty("MetroLineLength")
    private double metroLineLength;

    @JsonProperty("NumberOfCarriages")
    private int numberOfCarriages;

    @JsonProperty("global_id")
    private int globalId;

    @Override
    public String toString() {
        return "MetroLineDto{"
                + "line='" + line + '\''
                + ", numberOfStations=" + numberOfStations
                + ", metroLineLength=" + metroLineLength
                + ", numberOfCarriages=" + numberOfCarriages
                + ", globalId=" + globalId
                + '}';
    }
}
