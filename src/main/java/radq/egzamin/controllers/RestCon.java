package radq.egzamin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import radq.egzamin.entity.Reservation;
import radq.egzamin.entity.Room;
import radq.egzamin.repo.ReservationRepo;
import radq.egzamin.repo.RoomRepo;
import radq.egzamin.services.ServiceRap;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;

@RestController
public class RestCon {

    //TODO raport zyskow z danego dnia

    private final ServiceRap serviceRap;
    private final RoomRepo roomRepo;
    private final ReservationRepo reservationRepo;

    @Autowired
    public RestCon(ServiceRap serviceRap, RoomRepo roomRepo, ReservationRepo reservationRepo) {
        this.serviceRap = serviceRap;
        this.roomRepo = roomRepo;
        this.reservationRepo = reservationRepo;
    }

    //np. 2021-06-25
    @GetMapping("/raport/money/{day}")
    public int getMoney(@PathVariable(name="day")String day){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale(Locale.GERMANY);
        LocalDate date = LocalDate.parse(day, formatter);
        return this.serviceRap.getMoney(date);
    }

    @PostMapping("/api/rezerwacja/{roomId}")
    public boolean makeReservation(@PathVariable(name = "roomId") Long roomId,
                                   @RequestBody Reservation reservation)
    {
        Room room = roomRepo.findById(roomId).get();
        reservation.setRoom(room);
        reservationRepo.save(reservation);
        return true;
    }
}
