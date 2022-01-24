package ru.vlpetko.metroservice.service.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.vlpetko.metroservice.model.MetroLine;
import ru.vlpetko.metroservice.model.MetroLineUnit;
import ru.vlpetko.metroservice.model.Station;
import ru.vlpetko.metroservice.repository.MetroLineUnitRepository;
import ru.vlpetko.metroservice.service.client.dto.MetroLineDto;
import ru.vlpetko.metroservice.service.client.dto.MetroLineUnitDtoJson;
import ru.vlpetko.metroservice.service.client.dto.MetroStationUnitDto;
import ru.vlpetko.metroservice.service.client.dto.StationDto;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MosDataApiClient {

    @Value("${apiLineUrl}")
    String apiLineUrl;
    @Value("${apiKey}")
    String apiKey;
    @Value("${apiStationUrl}")
    String apiStationUrl;

    private final MetroLineUnitRepository metroLineUnitRepository;

    private final Map <String, Integer> quarters = new HashMap<>();

    String filter = "&$filter=Cells/Line";

    @Transactional
    public void getAndSaveData() {
        List<MetroLineUnit> data = getDataFromOpenSource();
        for (MetroLineUnit unit: data
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
                metroLine.setStations(getStationUnit(metroLine));
                metroLine.setMetroLineUnit(unit);
                unit.setMetroLine(metroLine);
                result.add(unit);
                System.out.println(metroLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;

    }
    public List<Station> getStationUnit(MetroLine metroLine){
        List<MetroStationUnitDto> resultJson;
        List<Station> result = new ArrayList<>();
        String param = " eq " + "\'" + metroLine.getLine() + "\'";
        try{
            String enc = URLEncoder.encode(param, "UTF-8").replace("+", "%20");
            ObjectMapper mapper = new ObjectMapper();
            resultJson = mapper.readValue(new URL(apiStationUrl + apiKey + filter + enc),
                    new TypeReference<>() {
                    });
            for (MetroStationUnitDto unitDtoJson: resultJson) {
                Station station = new Station();
                StationDto stationDto = unitDtoJson.getStationDto();
                station.setLine(stationDto.getLine());
                station.setYear(convertIntToDate(stationDto.getYear()));
                station.setQuarter(getIntQuarter(stationDto.getQuarter()));
                station.setIncomingPassengers(stationDto.getIncomingPassengers());
                station.setOutgoingPassengers(stationDto.getOutgoingPassengers());
                station.setMetroLine(metroLine);
                if(station.getLine().equals(metroLine.getLine())){
                    result.add(station);
                    System.out.println(station);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private int getIntQuarter(String quarterNumber){
        quarters.put("I квартал",1);
        quarters.put("II квартал",2);
        quarters.put("III квартал",3);
        quarters.put("IV квартал",4);
        return quarters.get(quarterNumber);
    }

    private LocalDate convertIntToDate(int year){
        LocalDate date = LocalDate.of(year,1,1);
        return date;
    }
}
