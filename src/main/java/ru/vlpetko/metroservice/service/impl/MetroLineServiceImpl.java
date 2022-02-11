package ru.vlpetko.metroservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vlpetko.metroservice.model.MetroLine;
import ru.vlpetko.metroservice.repository.MetroLineRepository;
import ru.vlpetko.metroservice.service.MetroLineService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MetroLineServiceImpl implements MetroLineService {

    private final MetroLineRepository metroLineRepository;

    @Override
    @Transactional
    public List<MetroLine> getAllLines() {
        return metroLineRepository.findAllLines();
    }

    @Override
    @Transactional
    public MetroLine getLineById(Long lineId) {
        return metroLineRepository.getOneLineById(lineId).orElseThrow(() -> new IllegalArgumentException("Линия не найдена: " + lineId));
    }

//    @Override
//    @Transactional
//    public MetroLine getLineById(Long lineId) {
//        return metroLineRepository.findById(lineId).orElseThrow(() -> new IllegalArgumentException("Линия не найдена: " + lineId));
//    }
}
