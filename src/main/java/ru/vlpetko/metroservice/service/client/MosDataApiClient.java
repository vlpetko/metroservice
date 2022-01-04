package ru.vlpetko.metroservice.service.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.vlpetko.metroservice.model.MetroLine;
import ru.vlpetko.metroservice.model.MetroLineUnit;
import ru.vlpetko.metroservice.service.client.dto.MetroLineDto;
import ru.vlpetko.metroservice.service.client.dto.MetroLineUnitDtoJson;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class MosDataApiClient {

    @Value("${apiLineUrl}")
    String apiLineUrl;
    @Value("${apiKey}")
    String apiKey;

    public List<MetroLineUnit> getDataFromOpenSource() {
        List<MetroLineUnitDtoJson> resultJson;
        List<MetroLineUnit> result = new ArrayList<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            resultJson = mapper.readValue(
                    new URL(apiLineUrl + apiKey),
                    new TypeReference<>() {
                    });
            for (int i = 0; i < resultJson.size(); i++) {
                MetroLineUnitDtoJson unitDtoJson = resultJson.get(i);
                MetroLineUnit unit = new MetroLineUnit();
                unit.setNumber(unitDtoJson.getNumber());
                MetroLine metroLine = new MetroLine();
                MetroLineDto lineDto = unitDtoJson.getMetroLineDto();
                metroLine.setLine(lineDto.getLine());
                metroLine.setNumberOfStations(lineDto.getNumberOfStations());
                metroLine.setMetroLineLength(lineDto.getMetroLineLength());
                metroLine.setNumberOfCarriages(lineDto.getNumberOfCarriages());
                unit.setMetroLine(metroLine);
                result.add(unit);
                System.out.println(unit);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;

    }
}
