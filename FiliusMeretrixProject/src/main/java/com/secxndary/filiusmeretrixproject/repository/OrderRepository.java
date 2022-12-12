package com.secxndary.filiusmeretrixproject.repository;
import com.secxndary.filiusmeretrixproject.entity.Concert;
import com.secxndary.filiusmeretrixproject.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface OrderRepository extends JpaRepository<Order, String> {

    void deleteByConcert(Concert concert);

    Order getById(Long id);

    @Query("SELECT o FROM Order o WHERE o.id = ?1")
    Optional<Order> findById(Long id);

    @Override
    List<Order> findAll();

    Order deleteById(Long id);
}
