package com.secxndary.filiusmeretrixproject.mapper;
import com.secxndary.filiusmeretrixproject.dto.FeedbackDto;
import com.secxndary.filiusmeretrixproject.dto.TicketDto;
import com.secxndary.filiusmeretrixproject.entity.Feedback;
import com.secxndary.filiusmeretrixproject.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface FeedbackMapper {

    FeedbackMapper INSTANCE = Mappers.getMapper(FeedbackMapper.class);

    FeedbackDto toDTO(Feedback feedback);

    Feedback fromDTO(FeedbackDto feedbackDto);
}