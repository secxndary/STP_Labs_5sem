package com.secxndary.filiusmeretrixproject.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayDto {

    private String name;

    private String description;

    private Float price;

    private String address;

    private String date;
}
