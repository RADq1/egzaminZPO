package radq.egzamin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import radq.egzamin.entity.Room;
import radq.egzamin.repo.RoomRepo;
import radq.egzamin.services.ServiceRap;

@Controller
public class MainController {

    @Autowired
    private final RoomRepo roomRepo;

    private final ServiceRap serviceRap;
    public MainController(RoomRepo roomRepo, ServiceRap serviceRap) {
        this.roomRepo = roomRepo;
        this.serviceRap = serviceRap;
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
    }


    @GetMapping("/")
    public String hello(){
        return "home";
    }

    @GetMapping("/reservation")
    public String reservationList(Model model, Room room){
        model.addAttribute("rooms", serviceRap.getAllRooms());
        return "reservation";
    }

    @GetMapping("/reservation/{id}")
    public String detailedInformation(Model model, @PathVariable Long id)
    {
        model.addAttribute("room",roomRepo.findById(id).get());
        return "reserv";
    }

}
