package ru.vlpetko.metroservice.service.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StationDto {

    @JsonProperty("NameOfStation")
    private String nameOfStation;

    @JsonProperty("Line")
    private String line;

    @JsonProperty("Year")
    private int year;

    @JsonProperty("Quarter")
    private String quarter;

    @JsonProperty("IncomingPassengers")
    private int incomingPassengers;

    @JsonProperty("OutgoingPassengers")
    private int outgoingPassengers;

    @JsonProperty("global_id")
    private int globalId;

    @Override
    public String toString() {
        return "StationDto{" +
                "nameOfStation='" + nameOfStation + '\'' +
                ", line='" + line + '\'' +
                ", year=" + year +
                ", quarter='" + quarter + '\'' +
                ", incomingPassengers=" + incomingPassengers +
                ", outgoingPassengers=" + outgoingPassengers +
                ", globalId=" + globalId +
                '}';
    }
}
