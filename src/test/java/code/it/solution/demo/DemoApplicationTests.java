package code.it.solution.demo;

import code.it.solution.demo.models.OpenWeather;
import code.it.solution.demo.service.OpenWeatherService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    private OpenWeatherService openWeatherService;

    @Test
    public void whenDeleteAllFromRepository_thenRepoShouldBeEmpty(){
        this.openWeatherService.deleteAll();
        Assert.assertEquals(openWeatherService.count(),0);

    }
    @Test
    public void testingFirstResultTemp_GreaterThan25(){
        Assert.assertTrue(openWeatherService.filterByTemperatureAbove25().get(0).getMaxTemp()>=25.00);
    }

    @Test
    void testInstanceOf(){
        assertThat(openWeatherService.filterByTemperatureAbove25().get(0), instanceOf(OpenWeather.class));

    }
    @Test
    void testDidDatabaseHas48records_16RecordsForEveryCity(){
        Assert.assertEquals(openWeatherService.count(),48);
    }
    @Test
    void testThatAreCitiesWith_MaxTempAbove25InNext16Days(){
        Assert.assertTrue(openWeatherService.filterByTemperatureAbove25().size()>0);

    }

    @Test
    void contextLoads() {
    }

}
