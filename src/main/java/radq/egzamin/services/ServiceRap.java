package radq.egzamin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import radq.egzamin.entity.Room;
import radq.egzamin.repo.ReservationRepo;
import radq.egzamin.repo.RoomRepo;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceRap {

    @Autowired
    private final RoomRepo roomRepo;
    private final ReservationRepo reservationRepo;
    public ServiceRap(RoomRepo roomRepo,ReservationRepo reservationRepo) {
        this.roomRepo = roomRepo;
        this.reservationRepo = reservationRepo;
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
        List <Room> availableRooms = this.roomRepo.findAll().stream()
                .filter(room -> {
                    room.setAvailable(firstDate, lastDate);
                    return room.isAvailable();
                })
                .collect(Collectors.toList());
        availableRooms.forEach(x-> System.out.println(x.getRoomId()));
        return availableRooms;
    }


    /*public boolean isDateFree(LocalDate from, LocalDate to, Room room)
    {
        return reservationRepo.findAll().stream()
                .filter(fr -> fr.getRoom().equals(room))
                .filter(re -> re.getEndDate().compareTo(from)>0)
                .filter(ne -> ne.getStartDate().compareTo(to)<0)
                .anyMatch(fr -> fr.getRoom().equals(room));
    } */
}
