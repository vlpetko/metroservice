package ru.vlpetko.metroservice.service.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.vlpetko.metroservice.mapper.MetroLineUnitMapper;
import ru.vlpetko.metroservice.mapper.MetroStationMapper;
import ru.vlpetko.metroservice.model.MetroLine;
import ru.vlpetko.metroservice.model.MetroLineUnit;
import ru.vlpetko.metroservice.model.Station;
import ru.vlpetko.metroservice.repository.MetroLineUnitRepository;
import ru.vlpetko.metroservice.service.client.dto.MetroLineUnitDtoJson;
import ru.vlpetko.metroservice.service.client.dto.MetroStationUnitDto;
import ru.vlpetko.metroservice.service.client.dto.StationDto;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class MosDataApiClient {

    @Value("${apiLineUrl}")
    String apiLineUrl;
    @Value("${apiKey}")
    String apiKey;
    @Value("${apiStationUrl}")
    String apiStationUrl;

    private final RestTemplate restTemplate;

    private final MetroLineUnitRepository metroLineUnitRepository;

    final static String FILTER = "&$filter=Cells/Line";

    @Transactional
    public void getAndSaveData() {
        getDataFromOpenSource().forEach(metroLineUnitRepository::save);
    }

    /**
     * Метод осуществляет получение данных по заданному URL путем конвертации JSON в Entity
     *
     * @param
     * @return List<MetroStation>
     * @throws
     */
    public List<MetroLineUnit> getDataFromOpenSource() {
        List<MetroLineUnitDtoJson> resultJson;
        List<MetroLineUnit> result = new ArrayList<>();

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        log.info("lineUrl: " + apiLineUrl + apiKey);
        ResponseEntity<MetroLineUnitDtoJson[]> responseEntity =
                restTemplate.exchange(apiLineUrl + apiKey, HttpMethod.GET, entity,
                        MetroLineUnitDtoJson[].class);

        log.info("server status: " + responseEntity.getStatusCode());
        if (responseEntity.getStatusCode() == HttpStatus.valueOf(200)) {

            resultJson = List.of(Objects.requireNonNull(responseEntity.getBody()));
            for (MetroLineUnitDtoJson unitDtoJson : resultJson) {
                MetroLineUnit unit = MetroLineUnitMapper.INSTANCE.mapToMetroLineUnit(unitDtoJson);
                MetroLine metroLine = unit.getMetroLine();
                metroLine.setStations(getStationUnit(metroLine));
                metroLine.setMetroLineUnit(unit);
                unit.setMetroLine(metroLine);
                result.add(unit);
                System.out.println(unit);
            }
        }
        return result;
    }

    /**
     * Метод осуществляет получение данных по заданному URL путем конвертации JSON в Entity
     *
     * @param  metroLine
     * @return List<Station>
     * @throws
     */
    public List<Station> getStationUnit(MetroLine metroLine) {
        List<MetroStationUnitDto> resultJson;
        List<Station> result = new ArrayList<>();
        String param = " eq " + "/'" + metroLine.getLine() + "/'";

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        log.info("stationUrl: " + apiStationUrl + apiKey + FILTER + param);
        ResponseEntity<MetroStationUnitDto[]> responseEntity = restTemplate.exchange(apiStationUrl + apiKey + FILTER
                + param, HttpMethod.GET, entity, MetroStationUnitDto[].class);
        resultJson = List.of(Objects.requireNonNull(responseEntity.getBody()));

        log.info("server status: " + responseEntity.getStatusCode());
        if (responseEntity.getStatusCode() == HttpStatus.valueOf(200)) {

            for (MetroStationUnitDto unitDto : resultJson) {
                StationDto stationDto = unitDto.getStationDto();
                Station station = MetroStationMapper.INSTANCE.mapToStation(stationDto);
                station.setMetroLine(metroLine);
                if (station.getLine().equals(metroLine.getLine())) {
                    result.add(station);
                }
            }
        }
        return result;
    }
}
