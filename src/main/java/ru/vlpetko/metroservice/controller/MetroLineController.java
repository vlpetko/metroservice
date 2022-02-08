package ru.vlpetko.metroservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.vlpetko.metroservice.controller.dto.LineDto;
import ru.vlpetko.metroservice.controller.dto.LineWithStationsDto;
import ru.vlpetko.metroservice.mapper.MetroLineMapper;
import ru.vlpetko.metroservice.service.MetroLineService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping({"/api"})
public class MetroLineController {

    private final MetroLineService metroLineService;

    @GetMapping("/lines")
    public List<LineDto> getLines() {
        return MetroLineMapper.INSTANCE.mapToLineDtoList(metroLineService.getAllLines());
    }

    @PostMapping("/line/current/{lineId}")
    public LineWithStationsDto getLineById(@PathVariable Long lineId) {
        return MetroLineMapper.INSTANCE.mapToLineWithStationsdto(metroLineService.getLineById(lineId));
    }
}
