package ru.vlpetko.metroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vlpetko.metroservice.model.MetroLineUnit;

@Repository
public interface MetroLineUnitRepository extends JpaRepository<MetroLineUnit, Long> {
}
