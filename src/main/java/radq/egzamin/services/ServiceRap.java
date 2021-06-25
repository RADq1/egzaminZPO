package radq.egzamin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import radq.egzamin.entity.Room;
import radq.egzamin.repo.RoomRepo;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceRap {

    @Autowired
    private final RoomRepo roomRepo;
    public ServiceRap(RoomRepo roomRepo) {
        this.roomRepo = roomRepo;
    }

    public List<Room> getAllRooms(){
        List<Room> rooms = roomRepo.findAll();
        return rooms;
    }

    public int getMoney(LocalDate day){
        return this.roomRepo.findAll().stream()
                .filter(room -> {
                    room.setAvailable(day,day);
                    return room.isAvailable();
                })
                .map(room -> room.getPrice())
                .reduce(0, Integer::sum);
    }

    public List<Room> getAvailableRooms(LocalDate firstDate, LocalDate lastDate){
        return this.roomRepo.findAll().stream()
                .filter(room -> {
                    room.setAvailable(firstDate, lastDate);
                    return room.isAvailable();
                })
                .collect(Collectors.toList());
    }
}
