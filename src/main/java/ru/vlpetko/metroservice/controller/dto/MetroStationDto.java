package ru.vlpetko.metroservice.controller.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MetroStationDto {

    private Long stationId;

    private String nameOfStation;

    private String line;

    private LocalDate year;

    private int quarter;

    private int incomingPassengers;

    private int outgoingPassengers;
}
