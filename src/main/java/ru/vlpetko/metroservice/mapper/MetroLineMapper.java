package ru.vlpetko.metroservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.vlpetko.metroservice.controller.dto.LineDto;
import ru.vlpetko.metroservice.controller.dto.LineWithStationsDto;
import ru.vlpetko.metroservice.model.MetroLine;

import java.util.List;

@Mapper(uses = {MetroLineUnitMapper.class, MetroStationMapper.class})
public interface MetroLineMapper {

    MetroLineMapper INSTANCE = Mappers.getMapper(MetroLineMapper.class);

    List<LineDto> mapToLineDtoList(List<MetroLine> metroLine);

    @Mapping(target = "lineId", source = "lineId")
    LineWithStationsDto mapToLineWithStationsdto(MetroLine metroLine);

}
