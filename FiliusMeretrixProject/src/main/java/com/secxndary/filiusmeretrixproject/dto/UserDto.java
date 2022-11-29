package com.secxndary.filiusmeretrixproject.dto;
import com.secxndary.filiusmeretrixproject.validator.CellPassword;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotBlank
    private String username;

    @NotBlank
    private String email;

    @CellPassword
    private String password;
}
