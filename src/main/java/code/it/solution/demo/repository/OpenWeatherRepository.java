package code.it.solution.demo.repository;

import code.it.solution.demo.models.OpenWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public interface OpenWeatherRepository extends JpaRepository<OpenWeather,Long> {
    @Query("SELECT o FROM OpenWeather o WHERE o.maxTemp>=:maxTemperature")
    List<OpenWeather> filterByTemperature(@Param("maxTemperature") Double maxTemperature);
    @Query("SELECT o  FROM OpenWeather o WHERE o.maxTemp>=25.00")
    List<OpenWeather> filterByTemperatureAbove25();
}
