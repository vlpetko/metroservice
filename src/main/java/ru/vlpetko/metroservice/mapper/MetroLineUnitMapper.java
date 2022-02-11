package ru.vlpetko.metroservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ru.vlpetko.metroservice.model.MetroLineUnit;
import ru.vlpetko.metroservice.service.client.dto.MetroLineUnitDtoJson;

@Mapper(uses = MetroLineMapper.class)
public interface MetroLineUnitMapper {
    MetroLineUnitMapper INSTANCE = Mappers.getMapper(MetroLineUnitMapper.class);

    @Mappings({
            @Mapping(target = "metroLine", source = "metroLineDto")
    })
    MetroLineUnit mapToMetroLineUnit(MetroLineUnitDtoJson metroUnitDtoJson);
}
