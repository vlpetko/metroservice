package ru.vlpetko.metroservice.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class LineWithStationsDto {

    private Long lineId;

    private String line;

    private int numberOfStations;

    private double metroLineLength;

    private int numberOfCarriages;

    List<MetroStationDto> stations;
}
