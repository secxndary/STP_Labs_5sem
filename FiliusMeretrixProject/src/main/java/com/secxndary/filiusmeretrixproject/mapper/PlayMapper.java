package com.secxndary.filiusmeretrixproject.mapper;
import com.secxndary.filiusmeretrixproject.dto.PlayDto;
import com.secxndary.filiusmeretrixproject.entity.Play;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;


@Component
public class PlayMapper {

//    PlayMapper INSTANCE = Mappers.getMapper(PlayMapper.class);

    public PlayDto toDTO(Play play) {
        return new PlayDto(play.getName(), play.getDescription(), play.getPrice(), play.getAddress(), play.getDate());
    }

    public Play fromDTO(PlayDto playDto) {
        return new Play(playDto.getName(), playDto.getDescription(), playDto.getPrice(), playDto.getAddress(), playDto.getDate() );
    }
}
