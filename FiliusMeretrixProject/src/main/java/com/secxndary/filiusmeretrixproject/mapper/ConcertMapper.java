package com.secxndary.filiusmeretrixproject.mapper;
import com.secxndary.filiusmeretrixproject.dto.ConcertDto;
import com.secxndary.filiusmeretrixproject.entity.Concert;
import org.springframework.stereotype.Component;


@Component
public class ConcertMapper {

    public ConcertDto toDTO(Concert concert) {
        return new ConcertDto(concert.getTitle(), concert.getArtist(), concert.getAddress(), concert.getDate(), concert.getCapacity());
    }

    public Concert fromDTO(ConcertDto concertDto) {
        return new Concert(concertDto.getTitle(), concertDto.getArtist(), concertDto.getAddress(), concertDto.getDate(), concertDto.getCapacity());
    }
}
