package ru.vlpetko.metroservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.vlpetko.metroservice.model.MetroLine;
import ru.vlpetko.metroservice.model.Station;
import ru.vlpetko.metroservice.model.criteria.MetroLineCriteria;
import ru.vlpetko.metroservice.repository.MetroLineRepository;
import ru.vlpetko.metroservice.repository.StationRepository;
import ru.vlpetko.metroservice.service.MetroLineService;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MetroLineServiceImpl implements MetroLineService {

    private final MetroLineRepository metroLineRepository;

    private final StationRepository stationRepository;

//    @Override
//    @Transactional
//    public List<MetroLine> getAllLines() {
//        return metroLineRepository.findAllLines();
//    }


    @Override
    @Transactional
    public Page<MetroLine> getAllLines(MetroLineCriteria metroLineCriteria) {
        return metroLineCriteria.getSortBy() == null
                ? metroLineRepository.findAllLines(PageRequest.of(metroLineCriteria.getPageNo(),
                metroLineCriteria.getPageSize()))
                : metroLineRepository.findAllLines(PageRequest.of(metroLineCriteria.getPageNo(),
                metroLineCriteria.getPageSize(),
                Sort.by(metroLineCriteria.getSortBy())));
    }

//    @Override
//    @Transactional
//    public MetroLine getLineById(Long lineId) {
//        return metroLineRepository.getOneLineById(lineId).orElseThrow(() -> new IllegalArgumentException("Линия не найдена: " + lineId));
//    }


    @Override
    @Transactional
    public Page<Station> getLineById(Long lineId, MetroLineCriteria metroLineCriteria) {
        try {
            MetroLine metroLine = metroLineRepository.getById(lineId);
            return metroLineCriteria.getSortBy() == null
                    ? stationRepository.findAllByMetroLine(metroLine, PageRequest.of(metroLineCriteria.getPageNo(),
                    metroLineCriteria.getPageSize()))
                    : stationRepository.findAllByMetroLine(metroLine, PageRequest.of(metroLineCriteria.getPageNo(),
                    metroLineCriteria.getPageSize(),
                    Sort.by(metroLineCriteria.getSortBy())));
        } catch (EntityNotFoundException e) {
            throw new IllegalArgumentException("Линия не найдена: " + lineId);
        }
    }
}
