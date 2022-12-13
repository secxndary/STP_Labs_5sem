package com.secxndary.filiusmeretrixproject.mapper;
import com.secxndary.filiusmeretrixproject.dto.TicketOrderDto;
import com.secxndary.filiusmeretrixproject.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface TicketOrderMapper {

    TicketOrderMapper INSTANCE = Mappers.getMapper(TicketOrderMapper.class);

    TicketOrderDto toDTO(Ticket ticket);

    Ticket fromDTO(TicketOrderDto ticketOrderDto);

}
