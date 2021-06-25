package radq.egzamin.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate endDate;
    public String email;
    public String firstName;
    public String lastName;

    @ManyToOne
    @JoinColumn(name = "room_id")
    public Room room;

    public Reservation() {
    }

    public Reservation(LocalDate startDate, LocalDate endDate, String email, String firstName, String lastName, Room room) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.room = room;
    }

    public Reservation(LocalDate startDate, LocalDate endDate, String email, String firstName, String lastName) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
