package ru.vlpetko.metroservice.service;

import org.springframework.data.domain.Page;
import ru.vlpetko.metroservice.model.MetroLine;
import ru.vlpetko.metroservice.model.Station;
import ru.vlpetko.metroservice.model.criteria.MetroLineCriteria;

import java.util.List;

public interface MetroLineService {

   Page<MetroLine> getAllLines(MetroLineCriteria metroLineCriteria);

   Page<Station> getLineById(Long lineId, MetroLineCriteria metroLineCriteria);
}
