package com.secxndary.filiusmeretrixproject.repository;
import com.secxndary.filiusmeretrixproject.entity.Play;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface PlayRepository extends JpaRepository<Play, String> {

    Play findByName(String name);

    @Override
    List<Play> findAll();
}

