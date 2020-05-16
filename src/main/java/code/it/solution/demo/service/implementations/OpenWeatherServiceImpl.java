package code.it.solution.demo.service.implementations;

import code.it.solution.demo.models.OpenWeather;
import code.it.solution.demo.repository.OpenWeatherRepository;
import code.it.solution.demo.service.OpenWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpenWeatherServiceImpl implements OpenWeatherService {

    private final OpenWeatherRepository openWeatherRepository;
    OpenWeatherServiceImpl(OpenWeatherRepository openWeatherRepository){
        this.openWeatherRepository=openWeatherRepository;
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
}
