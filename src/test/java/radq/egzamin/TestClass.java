package radq.egzamin;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import radq.egzamin.entity.Reservation;
import radq.egzamin.entity.Room;

import java.time.LocalDate;
import java.util.Arrays;

@SpringBootTest
public class TestClass {

    @Test
    public void available(){
        Room room = new Room (1L, 2, 300, false, true, false, true, false);
        Reservation reservation = new Reservation(LocalDate.parse("2021-06-26"), LocalDate.parse("2021-06-29"), "test@gmail.com", "aaa", "bbb");
        reservation.setRoom(room);
        room.setReservationList(Arrays.asList(reservation));
        room.setAvailable(LocalDate.parse("2021-07-26"), LocalDate.parse("2021-07-29"));
        //Assert.assertEquals(true, room.isAvailable());

    }
}
