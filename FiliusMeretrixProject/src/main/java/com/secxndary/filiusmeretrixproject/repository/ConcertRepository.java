package com.secxndary.filiusmeretrixproject.repository;
import com.secxndary.filiusmeretrixproject.entity.Concert;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ConcertRepository extends JpaRepository<Concert, String> {

    Concert findByTitle(String title);

    @Override
    List<Concert> findAll();
}
