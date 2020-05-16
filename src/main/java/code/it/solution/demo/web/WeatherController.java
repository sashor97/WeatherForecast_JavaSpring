package code.it.solution.demo.web;

import code.it.solution.demo.models.OpenWeather;
import code.it.solution.demo.service.OpenWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private RestTemplate restTemplate;

    private final OpenWeatherService openWeatherService;

    public WeatherController(OpenWeatherService openWeatherService) {
        this.openWeatherService = openWeatherService;
    }

    private List<OpenWeather> presmetaj(String rezultat,OpenWeather openWeather) {
        List<OpenWeather> lista=new ArrayList<OpenWeather>();
        try {
            openWeather=new OpenWeather();
            JSONObject obj_json = new JSONObject(rezultat);
            System.out.println(obj_json.getString("city"));
            JSONObject obj_json1 = new JSONObject(obj_json.getString("city"));
            System.out.println(obj_json1.getString("name"));
            JSONArray jsonArray = obj_json.getJSONArray("list");
            System.out.println(jsonArray.length());
           // openWeather.setCity_name(obj_json1.getString("name"));
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject daytemp = jsonArray.getJSONObject(i);
                String datum = daytemp.getString("dt");
                JSONObject temp = daytemp.getJSONObject("temp");
                String max = temp.getString("max");
                Date date = new Date(Integer.parseInt(datum) * 1000L);
                openWeather.setCity_name(obj_json1.getString("name"));
                openWeather.setDate(date.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate());
                openWeather.setMaxTemp(Double.parseDouble(max));
                lista.add(openWeather);
                openWeather=new OpenWeather();
                System.out.println(date);
                System.out.println(max);
            }
            return lista;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @GetMapping("/all")
    public void getResults(OpenWeather openWeather) {
        String result1 = restTemplate.getForObject("https://api.openweathermap.org/data/2.5/forecast/daily?q=Kichevo&cnt=16&units=metric&appid=886705b4c1182eb1c69f28eb8c520e20", String.class, 200);
        String result2 = restTemplate.getForObject("https://api.openweathermap.org/data/2.5/forecast/daily?q=Skopje&cnt=16&units=metric&appid=886705b4c1182eb1c69f28eb8c520e20", String.class, 200);
        String result3 = restTemplate.getForObject("https://api.openweathermap.org/data/2.5/forecast/daily?q=Berovo&cnt=16&units=metric&appid=886705b4c1182eb1c69f28eb8c520e20", String.class, 200);

        for (int j = 0; j < 3; j++) {
            if (j == 0) {
                List<OpenWeather> lista=presmetaj(result1,openWeather);
                for(int m=0;m<lista.size();m++) {
                    this.openWeatherService.save(lista.get(m));
                }
            }
            else if(j==1){
                List<OpenWeather> lista2=presmetaj(result2,openWeather);
                for(int m=0;m<lista2.size();m++) {
                    this.openWeatherService.save(lista2.get(m));
                }

            }
            else {
                List<OpenWeather> lista3=presmetaj(result3,openWeather);
                for(int m=0;m<lista3.size();m++) {
                    this.openWeatherService.save(lista3.get(m));
                }

            }
        }


    }
    @GetMapping("/filterByTemp/{temp}")
    public List<OpenWeather> getFiltered(@PathVariable("temp") Integer temp){
        Double maxTemp=temp.doubleValue();
        return this.openWeatherService.filterByTemperatureAbove25();
    }
}
