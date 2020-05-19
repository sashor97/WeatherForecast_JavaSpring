# Временска прогноза
Краток опис: Целта на оваа веб апликација е да се направи временска прогноза за 3 македонски градови по случаен избор и да се прикажат
во табела само оние градови заедно со датумите кога дневната максимална температура е поголема од 25 степени. Backend делот за оваа апликација е изработен во Java Spring додека Frontend делот е изработен во ReactJS

# Упатство за стартување:
- Сите потребни параметри за конекција со базата се поставени во фајлот application.properties
- Најпрво треба да се стартува MySQL сервер кој ќе слуша на порта 3306.
- Името на базата треба да биде openweather
- Потребно е да се креира корисник со сите дозволи врз базата со username:root/password:
- Доколку има потреба да се промени името на базата или корисникот тоа треба да се направи во application.properties
- Доколку користите IntelliJ IDEA Ultimate Edition, самото IDE има вграден Spring Initializr кој ќе го конфигурира backend делот. Потребно е само да ја стартувате апликацијата во Run или Debug mode.
- Откако ќе се изврши успешно потребно е на web browser да се пристапи до URL-to http://localhost:8080/weather/all со цел да се прочитаат податоците од OpenWeatherMap API и да се запишат во база.
- Упатството за стартување на ReactJS апликацијата е дадено на https://github.com/sashor97/Forecast_ReactJS.git при што прво мора да направите npm install во terminal со цел да ги инсталирате сите потребни depedency и библиотеки. па потоа да направите npm start.
