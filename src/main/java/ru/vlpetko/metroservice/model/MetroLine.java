package ru.vlpetko.metroservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "metro_line")
public class MetroLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lineId;

    private String line;

    private int numberOfStations;

    private double metroLineLength;

    private int numberOfCarriages;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "unit_id")
    private MetroLineUnit metroLineUnit;

    @OneToMany(mappedBy = "metroLine", cascade = CascadeType.ALL)
    List<Station> stations;

    @Override
    public String toString() {
        return "MetroLine{" +
                "lineId=" + lineId +
                ", line='" + line + '\'' +
                ", numberOfStations=" + numberOfStations +
                ", metroLineLength=" + metroLineLength +
                ", numberOfCarriages=" + numberOfCarriages +
                '}';
    }
}
