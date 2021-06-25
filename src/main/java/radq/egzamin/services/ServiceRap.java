package radq.egzamin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import radq.egzamin.entity.Room;
import radq.egzamin.repo.RoomRepo;

import java.time.LocalDate;
import java.util.List;

@Service
public class ServiceRap {

    @Autowired
    private final RoomRepo roomRepo;
    public ServiceRap(RoomRepo roomRepo) {
        this.roomRepo = roomRepo;
    }

    public List<Room> getAllBooks(){
        List<Room> rooms = roomRepo.findAll();
        return rooms;
    }





    //private List<Room> rooms = roomRepo.findAll();

    //TODO ALGORYTM ZYSKÓW NA DZIEN

    public int getMoney(LocalDate day){
        return 3;
    }
}
