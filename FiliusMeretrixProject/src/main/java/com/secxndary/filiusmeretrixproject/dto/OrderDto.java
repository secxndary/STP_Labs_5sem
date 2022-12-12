package com.secxndary.filiusmeretrixproject.dto;
import com.secxndary.filiusmeretrixproject.entity.Concert;
import com.secxndary.filiusmeretrixproject.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long id;

    private Boolean is_active;

    private Concert concert;

    // TODO: maybe delete
    private User user;
}
