package com.secxndary.filiusmeretrixproject.mapper;
import com.secxndary.filiusmeretrixproject.dto.TicketDto;
import com.secxndary.filiusmeretrixproject.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface TicketMapper {

    TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);

    TicketDto toDTO(Ticket ticket);

    Ticket fromDTO(TicketDto ticketDto);
}
