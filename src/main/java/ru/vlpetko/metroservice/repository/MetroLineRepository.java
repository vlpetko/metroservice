package ru.vlpetko.metroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.vlpetko.metroservice.model.MetroLine;

import java.util.List;
import java.util.Optional;

@Repository
public interface MetroLineRepository extends JpaRepository<MetroLine, Long> {

    @Query("SELECT m FROM MetroLine m")
    List<MetroLine> findAllLines();

    @Query(value = " SELECT l FROM MetroLine l "
            + "left join fetch l.stations "
            + " WHERE l.lineId = :lineId ")
    Optional<MetroLine> getOneLineById(@Param("lineId") Long lineId);
}
