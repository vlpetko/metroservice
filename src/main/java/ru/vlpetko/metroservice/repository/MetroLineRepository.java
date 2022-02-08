package ru.vlpetko.metroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vlpetko.metroservice.model.MetroLine;

@Repository
public interface MetroLineRepository extends JpaRepository<MetroLine, Long> {
}
