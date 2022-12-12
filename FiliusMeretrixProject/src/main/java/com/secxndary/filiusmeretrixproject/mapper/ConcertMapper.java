package com.secxndary.filiusmeretrixproject.mapper;
import com.secxndary.filiusmeretrixproject.dto.ConcertDto;
import com.secxndary.filiusmeretrixproject.entity.Concert;
import com.secxndary.filiusmeretrixproject.entity.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class ConcertMapper {

    public ConcertDto toDTO(Concert concert) {
        return new ConcertDto(concert.getTitle(), concert.getArtist(), concert.getAddress(), concert.getDate(), concert.getCapacity());
    }

    public Concert fromDTO(ConcertDto concertDto) {
        return new Concert(concertDto.getTitle(), concertDto.getArtist(), concertDto.getAddress(), concertDto.getDate(), concertDto.getCapacity(), new ArrayList<Order>() {
        });
    }
}
