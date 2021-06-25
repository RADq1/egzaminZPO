package radq.egzamin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import radq.egzamin.services.ServiceRap;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@RestController
public class RestCon {

    //TODO raport zyskow z danego dnia

    private final ServiceRap serviceRap;

    @Autowired
    public RestCon(ServiceRap serviceRap) {
        this.serviceRap = serviceRap;
    }

    //np. 2021-06-25
    @GetMapping("/raport/money/{day}")
    public int getMoney(@PathVariable(name="day")String day){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale(Locale.GERMANY);
        LocalDate date = LocalDate.parse(day, formatter);
        return this.serviceRap.getMoney(date);
    }
}
