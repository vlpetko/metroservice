package ru.vlpetko.metroservice.model;

import lombok.Data;

@Data
public class MetroLine {

    private String line;

    private int numberOfStations;

    private double metroLineLength;

    private int numberOfCarriages;

}
