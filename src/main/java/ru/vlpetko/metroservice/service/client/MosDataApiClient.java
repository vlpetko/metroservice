package ru.vlpetko.metroservice.service.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.vlpetko.metroservice.model.MetroLine;
import ru.vlpetko.metroservice.model.MetroLineUnit;
import ru.vlpetko.metroservice.repository.MetroLineUnitRepository;
import ru.vlpetko.metroservice.service.client.dto.MetroLineDto;
import ru.vlpetko.metroservice.service.client.dto.MetroLineUnitDtoJson;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MosDataApiClient {

    @Value("${apiLineUrl}")
    String apiLineUrl;
    @Value("${apiKey}")
    String apiKey;

    private final MetroLineUnitRepository metroLineUnitRepository;

    @Transactional
    public void getAndSaveData() {
        List<MetroLineUnit> data = getDataFromOpenSource();
        for (MetroLineUnit unit:data
             ) {
            metroLineUnitRepository.save(unit);
        }
    }

    public List<MetroLineUnit> getDataFromOpenSource() {
        List<MetroLineUnitDtoJson> resultJson;
        List<MetroLineUnit> result = new ArrayList<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            resultJson = mapper.readValue(
                    new URL(apiLineUrl + apiKey),
                    new TypeReference<>() {
                    });
            for (MetroLineUnitDtoJson unitDtoJson: resultJson) {
                MetroLineUnit unit = new MetroLineUnit();
                unit.setNumber(unitDtoJson.getNumber());
                MetroLine metroLine = new MetroLine();
                MetroLineDto lineDto = unitDtoJson.getMetroLineDto();
                metroLine.setLine(lineDto.getLine());
                metroLine.setNumberOfStations(lineDto.getNumberOfStations());
                metroLine.setMetroLineLength(lineDto.getMetroLineLength());
                metroLine.setNumberOfCarriages(lineDto.getNumberOfCarriages());
                metroLine.setMetroLineUnit(unit);
                unit.setMetroLine(metroLine);
                result.add(unit);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;

    }
}
