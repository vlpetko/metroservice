package ru.vlpetko.metroservice.controller.dto;

import lombok.Data;

@Data
public class LineDto {

    private Long lineId;

    private String line;

    private int numberOfStations;

    private double metroLineLength;

    private int numberOfCarriages;
}
