package code.it.solution.demo.service.implementations;

import code.it.solution.demo.models.OpenWeather;
import code.it.solution.demo.repository.OpenWeatherRepository;
import code.it.solution.demo.service.OpenWeatherService;
import code.it.solution.demo.util.Costants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class OpenWeatherServiceImpl implements OpenWeatherService {

    @Autowired
    private RestTemplate restTemplate;
    private final OpenWeatherRepository openWeatherRepository;

    OpenWeatherServiceImpl(OpenWeatherRepository openWeatherRepository) {
        this.openWeatherRepository = openWeatherRepository;
    }

    @Override
    public OpenWeather save(OpenWeather openWeather) {
        return this.openWeatherRepository.save(openWeather);
    }

    @Override
    public List<OpenWeather> filterByTemperature(Double maxTemperature) {
        return this.openWeatherRepository.filterByTemperature(maxTemperature);
    }

    @Override
    public List<OpenWeather> filterByTemperatureAbove25() {
        return this.openWeatherRepository.filterByTemperatureAbove25();
    }

    @Override
    public void saveAll(List<OpenWeather> weatherList) {
        weatherList.forEach(this::save);
    }

    @Override
    public List<OpenWeather> evaluate(String cityName) {
        try {
            String result = restTemplate.getForObject(String.format(Costants.URL_PATTERN, cityName), String.class, 200);
            JSONObject resultJson = new JSONObject(result);
            JSONObject city = new JSONObject(resultJson.getString("city"));
            JSONArray list = resultJson.getJSONArray("list");
            return IntStream
                    .range(0, list.length())
                    .mapToObj(i -> getJsonObjectFromJsonArray(list, i))
                    .filter(Objects::nonNull)
                    .map(dayTemp -> mapWeatherJsonObjectToWeatherModel(dayTemp, city))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public void deleteAll() {
        this.openWeatherRepository.deleteAll();
    }

    @Override
    public long count() {
        return this.openWeatherRepository.count();
    }

    private JSONObject getJsonObjectFromJsonArray(JSONArray list, int i) {
        try {
            return list.getJSONObject(i);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private OpenWeather mapWeatherJsonObjectToWeatherModel(JSONObject dayTemp, JSONObject city) {
        try {
            String datum = dayTemp.getString("dt");
            JSONObject temp = dayTemp.getJSONObject("temp");
            String max = temp.getString("max");
            Date date = new Date(Integer.parseInt(datum) * 1000L);
            return OpenWeather.builder()
                    .city_name(city.getString("name"))
                    .date(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                    .maxTemp(Double.parseDouble(max))
                    .build();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
