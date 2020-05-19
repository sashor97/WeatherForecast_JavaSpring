package code.it.solution.demo.service;

import code.it.solution.demo.models.OpenWeather;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OpenWeatherService {
    OpenWeather save(OpenWeather openWeather);
    List<OpenWeather> filterByTemperature(Double maxTemperature);
    List<OpenWeather> filterByTemperatureAbove25();
    void saveAll(List<OpenWeather> weatherList);
    List<OpenWeather> evaluate(String cityName);
    void deleteAll();
    long count();

}
