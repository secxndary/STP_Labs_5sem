package com.secxndary.filiusmeretrixproject.repository;
import com.secxndary.filiusmeretrixproject.entity.Concert;
import com.secxndary.filiusmeretrixproject.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;


public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Ticket getById(Long id);

    @Query("SELECT t FROM Ticket t WHERE t.id = ?1")
    Optional<Ticket> findById(Long id);

    Ticket findByPlaceAndRowAndAndConcert(int place, int row, Concert concert);
}
