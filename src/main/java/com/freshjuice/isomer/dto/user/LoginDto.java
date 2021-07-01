package com.freshjuice.isomer.dto.user;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class LoginDto {
    @NotNull(message = "userName不能为空")
    private String userName;
    @NotNull(message = "password不能为空")
    private String password;
}
