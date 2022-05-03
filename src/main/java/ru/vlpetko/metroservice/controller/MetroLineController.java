package ru.vlpetko.metroservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vlpetko.metroservice.controller.dto.LineDto;
import ru.vlpetko.metroservice.controller.dto.LineWithStationsDto;
import ru.vlpetko.metroservice.mapper.MetroLineMapper;
import ru.vlpetko.metroservice.mapper.MetroStationMapper;
import ru.vlpetko.metroservice.model.MetroLine;
import ru.vlpetko.metroservice.model.Station;
import ru.vlpetko.metroservice.model.criteria.MetroLineCriteria;
import ru.vlpetko.metroservice.service.MetroLineService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping({"/api"})
public class MetroLineController {

    private final static String TOTAL_LINES = "totalLines";
    private final static String TOTAL_PAGES = "totalPages";
    private final static String METRO_LINES = "metroLines";
    private final static String STATIONS = "metroStations";
    private final MetroLineService metroLineService;

//    @GetMapping("/lines")
//    public List<LineDto> getLines() {
//        return MetroLineMapper.INSTANCE.mapToLineDtoList(metroLineService.getAllLines());
//    }

    @GetMapping("/lines")
    public ResponseEntity<Map<String, Object>> getLines(
            @RequestParam(defaultValue = "0", value = "pageNo", required = false) Integer pageNo,
            @RequestParam(defaultValue = "5", value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "orderLinesBy", required = false) String sortBy
    ) {
        try {
            Map<String, Object> response = new HashMap<>();
            MetroLineCriteria metroLineCriteria = MetroLineCriteria.builder()
                    .pageNo(pageNo)
                    .pageSize(pageSize)
                    .sortBy(sortBy)
                    .build();
            Page<MetroLine> metroLines = metroLineService.getAllLines(metroLineCriteria);
            if (metroLines != null & metroLines.hasContent()) {
                response.put(TOTAL_LINES, metroLines.getTotalElements());
                response.put(TOTAL_PAGES, metroLines.getTotalPages());
                response.put(METRO_LINES, MetroLineMapper.INSTANCE.mapToLineDtoList(metroLines.getContent()));
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PostMapping("/line/current/{lineId}")
//    public LineWithStationsDto getLineById(@PathVariable Long lineId) {
//        return MetroLineMapper.INSTANCE.mapToLineWithStationsdto(metroLineService.getLineById(lineId));
//    }

    @PostMapping("/line/current/{lineId}")
    public ResponseEntity<Map<String, Object>> getLineById(
            @PathVariable Long lineId,
            @RequestParam(defaultValue = "0", value = "pageNo", required = false) Integer pageNo,
            @RequestParam(defaultValue = "5", value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "orderLinesBy", required = false) String sortBy
    ) {
        try {
            Map<String, Object> response = new HashMap<>();
            MetroLineCriteria metroLineCriteria = MetroLineCriteria.builder()
                    .pageNo(pageNo)
                    .pageSize(pageSize)
                    .sortBy(sortBy)
                    .build();
            Page<Station> stations = metroLineService.getLineById(lineId, metroLineCriteria);
            if (stations != null & stations.hasContent()) {
                response.put(TOTAL_LINES, stations.getTotalElements());
                response.put(TOTAL_PAGES, stations.getTotalPages());
                response.put(STATIONS, MetroStationMapper.INSTANCE.mapToListStationDto(stations.getContent()));
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (
                Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
