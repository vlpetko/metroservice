package ru.vlpetko.metroservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.vlpetko.metroservice.model.MetroLine;
import ru.vlpetko.metroservice.service.client.dto.MetroLineDto;

@Mapper(uses = {MetroLineUnitMapper.class, MetroStationMapper.class})
public interface MetroLineMapper {

    MetroLineMapper INSTANCE = Mappers.getMapper(MetroLineMapper.class);

    MetroLine mapToMetroLine(MetroLineDto metroLineDtoJson);
}
