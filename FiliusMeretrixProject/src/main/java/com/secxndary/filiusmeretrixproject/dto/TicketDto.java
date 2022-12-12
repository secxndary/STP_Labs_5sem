package com.secxndary.filiusmeretrixproject.dto;
import com.secxndary.filiusmeretrixproject.entity.Concert;
import com.secxndary.filiusmeretrixproject.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {

    private Integer row;

    private Integer place;

    private Boolean availability;

    private String concertTitle;
}
