package radq.egzamin.controllers;

import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import radq.egzamin.entity.Reservation;
import radq.egzamin.entity.Room;
import radq.egzamin.repo.ReservationRepo;
import radq.egzamin.repo.RoomRepo;
import radq.egzamin.services.ServiceRap;

import java.time.LocalDate;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private final RoomRepo roomRepo;
    private final ReservationRepo reservationRepo;
    private final ServiceRap serviceRap;
    public MainController(RoomRepo roomRepo, ServiceRap serviceRap, ReservationRepo reservationRepo) {
        this.roomRepo = roomRepo;
        this.serviceRap = serviceRap;
        this.reservationRepo = reservationRepo;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void StartApp()
    {
        Room room1 = new Room(6, 100, true, true, true, true, true);
        Room room2 = new Room(4, 60, true, true, false, false, false);
        Room room3 = new Room(3, 40, true, true, false, false, false);
        Room room4 = new Room(4, 50, true, true, false, true, false);
        Room room5 = new Room(2, 30, true, false, false, false, false);
        roomRepo.save(room1);
        roomRepo.save(room2);
        roomRepo.save(room3);
        roomRepo.save(room4);
        roomRepo.save(room5);

        Reservation reservation1 = new Reservation(LocalDate.now().minusDays(3), LocalDate.now().plusDays(2), "1998radq@gmail.com", "Radosław", "Gackowski", room2);
        Reservation reservation2 = new Reservation(LocalDate.now().minusDays(7), LocalDate.now().plusDays(7), "1998radq@gmail.com", "Radosław", "Gackowski", room3);

        /*reservationRepo.save(reservation1);
        reservationRepo.save(reservation2); */
    }


    @GetMapping("/")
    public String hello(){
        return "home";
    }

    //wyswietlanie wszystkich pokoi
    @GetMapping("/reservation")
    public String reservationList(Model model, Room room){
        model.addAttribute("rooms", serviceRap.getAllRooms());

        return "reservation";
    }

    @GetMapping("/reservation/{id}")
    public String detailedInformation(Model model, @PathVariable Long id, Room room, Reservation reservation)
    {
        //model.addAttribute("roomId",roomRepo.findById(id).get());
        model.addAttribute("room",roomRepo.findById(id).get());
        //reservation.setRoom(room);
        //reservationRepo.save(reservation);
        model.addAttribute("reservation", reservation);

        return "accept";
    }


    @GetMapping("/date")
    public String dateUrlop(Model model, Reservation reservation){
        //model.addAttribute("reservation", reservation);
        return "reserv";
    }

 //Cannot invoke "java.time.chrono.ChronoLocalDate.toEpochDay()" because "other" is null
   /* @PostMapping("/date")
    public String dateUrlops(Model model, LocalDate firstDate, LocalDate lastDate)
    {
        model.addAttribute("reservation", serviceRap.getAvailableRooms(firstDate, lastDate));
        System.out.println(firstDate);
        System.out.println(lastDate);
        return "reservation";
    } */

   /*@PostMapping("/date")
   public String dateUrlops(Model model, Reservation reservation, Room room)
   {

       model.addAttribute("reservation", serviceRap.getAvailableRooms(reservation.startDate, reservation.endDate));
       //model.addAttribute("reservation", serviceRap.isDateFree(reservation.startDate, reservation.endDate, room));
       System.out.println(reservation.startDate);
       System.out.println(reservation.endDate);
       return "reservation";
   } */
   @PostMapping("/date")
   public String dateUrlops(Model model, @RequestParam String startDateString,
                            @RequestParam String endDateString)
   {
       LocalDate startDate = LocalDate.parse(startDateString);
       LocalDate endDate = LocalDate.parse(endDateString);
       List<Room> rooms = serviceRap.getAvailableRooms(startDate, endDate);
       model.addAttribute("rooms", rooms);
       return "reservation";
   }





}
