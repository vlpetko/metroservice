package ru.vlpetko.metroservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lines")
public class MetroLineUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long unitId;

    private int number;

    @OneToOne(mappedBy = "metroLineUnit", cascade = CascadeType.ALL)
    private MetroLine metroLine;

    @Override
    public String toString() {
        return "MetroLineUnit{"
                + "unitId=" + unitId
                + ", number=" + number
                + ", metroLine=" + metroLine
                + '}';
    }
}
