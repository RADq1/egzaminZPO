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

    //wyliczenie sumy pieniedzy dla danego dnia
    public int getMoney(LocalDate day){
        return this.roomRepo.findAll().stream() //wyszukujemy wszystkie pokoje
                .filter(room -> { //filtrujemy po spelnionym warunku
                    room.setAvailable(day,day); //ustawiamy czy na dany dzien pokoj jest dostepny
                    return !room.isAvailable(); //patrzymy tylko na zajete
                })
                .map(room -> room.getPrice()) //zmiana typu z listy pokoi na liste intów
                .reduce(0, Integer::sum); //sumujemy
    }

    /*//wyciaganie listy dostepnych pokoi
    public List<Room> getAvailableRooms(LocalDate firstDate, LocalDate lastDate){
        List <Room> availableRooms = this.roomRepo.findAll().stream()
                .filter(room -> { //wyszukujemy tylko te, ktore spelniaja warunek
                    room.setAvailable(firstDate, lastDate); //ustawiamy, czy na dane dni pokoj jest dostepny
                    return room.isAvailable(); //wyciagamy tylko wolne pokoje
                })
                .collect(Collectors.toList());
        availableRooms.forEach(x-> System.out.println(x.getRoomId()));
        return availableRooms;
    }*/

    //wyciaganie listy dostepnych pokoi
    public List<Room> getAvailableRooms(LocalDate firstDate, LocalDate lastDate, int people, boolean petsFriendly, boolean canSmoke, boolean parking, boolean prettyViewFromWindow){
        List <Room> availableRooms = this.roomRepo.findAll().stream()
                .filter(room -> room.isPetsFriendly() == petsFriendly)
                .filter(room -> room.isPrettyViewFromWindow() == prettyViewFromWindow)
                .filter(room -> room.isParking() == parking)
                .filter(room -> room.isCanSmoke() == canSmoke)
                .filter(room -> room.getPeople() == people)
                .filter(room -> { //wyszukujemy tylko te, ktore spelniaja warunek
                    room.setAvailable(firstDate, lastDate); //ustawiamy, czy na dane dni pokoj jest dostepny
                    return room.isAvailable(); //wyciagamy tylko wolne pokoje
                })
                .collect(Collectors.toList());
        availableRooms.forEach(x-> System.out.println(x.getRoomId()));
        return availableRooms;
    }

}
