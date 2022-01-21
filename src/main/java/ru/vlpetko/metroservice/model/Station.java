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
@Table(name = "stations")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stationId;

    private String line;

    private int year;

    private String quarter;

    private int incomingPassengers;

    private int outgoingPassengers;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "line_id")
    private MetroLine metroLine;


    @Override
    public String toString() {
        return "Station{" +
                "stationId=" + stationId +
                ", line='" + line + '\'' +
                ", year=" + year +
                ", quarter='" + quarter + '\'' +
                ", incomingPassengers=" + incomingPassengers +
                ", outgoingPassengers=" + outgoingPassengers +
                '}';
    }
}
