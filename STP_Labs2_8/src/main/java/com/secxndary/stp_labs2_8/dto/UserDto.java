package com.secxndary.stp_labs2_8.dto;
import com.secxndary.stp_labs2_8.validator.CellPassword;
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
