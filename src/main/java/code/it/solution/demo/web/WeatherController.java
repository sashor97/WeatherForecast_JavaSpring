package code.it.solution.demo.web;

import code.it.solution.demo.models.OpenWeather;
import code.it.solution.demo.service.OpenWeatherService;
import code.it.solution.demo.util.Costants;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/weather")
@CrossOrigin({"*","localhost:3000"})
public class WeatherController {

    private final OpenWeatherService openWeatherService;

    public WeatherController(OpenWeatherService openWeatherService) {
        this.openWeatherService = openWeatherService;
    }

    @GetMapping("/all")
    public void getResults() {
        this.openWeatherService.deleteAll(); /*NOTE: this will delete old data from database , so after call api you will get the newest data in database*/
        this.openWeatherService.saveAll(this.openWeatherService.evaluate(Costants.KICHEVO));
        this.openWeatherService.saveAll(this.openWeatherService.evaluate(Costants.SKOPJE));
        this.openWeatherService.saveAll(this.openWeatherService.evaluate(Costants.BEROVO));
    }

    @GetMapping("/filterByTemp/{temp}")
    public List<OpenWeather> getFiltered(@PathVariable("temp") Integer temp){
        Double maxTemp=temp.doubleValue();
        return this.openWeatherService.filterByTemperature(maxTemp);
    }
    @GetMapping("/aboveFixedTemp")
    public List<OpenWeather> getAboveTemp(){
             return this.openWeatherService.filterByTemperatureAbove25();
    }


}
