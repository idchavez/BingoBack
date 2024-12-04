package com.bingoback.auth.dto;

import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public record AuthCreateRolRequest(@Size(max = 2, message = "El usuario no puede tener mas de 2 roles") List<String> rolList) {
}
