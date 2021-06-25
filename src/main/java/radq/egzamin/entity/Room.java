package radq.egzamin.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    //private RoomType type;
    private int people; //ile osób

    @OneToMany
    private List<Reservation> reservationList;

    private int price;
    private boolean available;

    //dodatkowe rzeczy
    private boolean petsFriendly;
    private boolean canSmoke;
    private boolean parking; //miejsce parkingowe dla konkretnego pokoju
    private boolean prettyViewFromWindow; //okno z widokem

    private void setAvailable(LocalDate firstDay, LocalDate LastDay){
        this.available = this.reservationList.stream()
                .anyMatch(reservation -> reservation.getStartDate()
                                .isBefore(firstDay) && reservation.getEndDate().isAfter(LastDay));
    }

    public Room() {
    }

    public Room(int people, int price, boolean available, boolean petsFriendly, boolean canSmoke, boolean parking, boolean prettyViewFromWindow) {
        this.people = people;
        this.price = price;
        this.available = available;
        this.petsFriendly = petsFriendly;
        this.canSmoke = canSmoke;
        this.parking = parking;
        this.prettyViewFromWindow = prettyViewFromWindow;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

   /* public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    } */

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isPetsFriendly() {
        return petsFriendly;
    }

    public void setPetsFriendly(boolean petsFriendly) {
        this.petsFriendly = petsFriendly;
    }

    public boolean isCanSmoke() {
        return canSmoke;
    }

    public void setCanSmoke(boolean canSmoke) {
        this.canSmoke = canSmoke;
    }

    public boolean isParking() {
        return parking;
    }

    public void setParking(boolean parking) {
        this.parking = parking;
    }

    public boolean isPrettyViewFromWindow() {
        return prettyViewFromWindow;
    }

    public void setPrettyViewFromWindow(boolean prettyViewFromWindow) {
        this.prettyViewFromWindow = prettyViewFromWindow;
    }
}
