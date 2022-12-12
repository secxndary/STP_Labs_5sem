package com.secxndary.filiusmeretrixproject.dto;
import com.secxndary.filiusmeretrixproject.validator.CellDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConcertDto {

    private String title;

    private String artist;

    private String address;

    @CellDate
    private LocalDate date;

    private int capacity;
}
