package ru.vlpetko.metroservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.vlpetko.metroservice.model.MetroLine;
import ru.vlpetko.metroservice.model.Station;

@Repository
public interface StationRepository extends JpaRepository<Station, Long>,
        PagingAndSortingRepository<Station, Long> {

    Page<Station> findAllByMetroLine(MetroLine metroline, Pageable pageable);
}
