package ru.vlpetko.metroservice.service;

import ru.vlpetko.metroservice.model.MetroLine;

import java.util.List;

public interface MetroLineService {

   List<MetroLine> getAllLines();

   MetroLine getLineById(Long lineId);
}
