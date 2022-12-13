package com.secxndary.filiusmeretrixproject.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketOrderDto {

    private Long id;

    private Integer row;

    private Integer place;

    private Boolean availability;

    private String concertTitle;

    private String userName;
}
