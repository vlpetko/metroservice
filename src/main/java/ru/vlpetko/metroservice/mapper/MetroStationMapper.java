package ru.vlpetko.metroservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.vlpetko.metroservice.model.Station;
import ru.vlpetko.metroservice.service.client.dto.StationDto;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Mapper(uses = MetroLineMapper.class)
public interface MetroStationMapper {

    MetroStationMapper INSTANCE = Mappers.getMapper(MetroStationMapper.class);

    @Mapping(source = "year", target = "year", qualifiedByName = "convertIntToDate")
    @Mapping(source = "quarter", target = "quarter", qualifiedByName = "getIntQuarter")
    Station mapToStation(StationDto stationDto);

    default int getIntQuarter(String quarterNumber) {
        final Map<String, Integer> quarters = new HashMap<>();
        quarters.put("I квартал", 1);
        quarters.put("II квартал", 2);
        quarters.put("III квартал", 3);
        quarters.put("IV квартал", 4);
        return quarters.get(quarterNumber);
    }

    default LocalDate convertIntToDate(int year) {
        LocalDate date = LocalDate.of(year, 1, 1);
        return date;
    }
}
