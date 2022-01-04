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
@Table(name = "metro_line_unit")
public class MetroLineUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long unitId;

    private int number;

    @OneToOne(mappedBy = "metroLineUnit", cascade = CascadeType.ALL)
    private MetroLine metroLine;

}
