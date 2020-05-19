package code.it.solution.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="open_weather")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OpenWeather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String city_name;

    private LocalDate date;

    private Double maxTemp;

}
