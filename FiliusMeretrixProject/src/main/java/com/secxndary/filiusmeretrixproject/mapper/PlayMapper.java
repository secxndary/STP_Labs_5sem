package com.secxndary.filiusmeretrixproject.mapper;
import com.secxndary.filiusmeretrixproject.dto.ConcertDto;
import com.secxndary.filiusmeretrixproject.entity.Concert;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface PlayMapper {
    PlayMapper INSTANCE = Mappers.getMapper(PlayMapper.class);

    ConcertDto toDTO(Concert play);

    Concert fromDTO(ConcertDto playDto);
}
