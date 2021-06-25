package radq.egzamin.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import radq.egzamin.entity.Reservation;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Long> {
}
