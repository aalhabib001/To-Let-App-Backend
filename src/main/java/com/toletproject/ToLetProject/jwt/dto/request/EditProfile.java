package com.toletproject.ToLetProject.jwt.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class EditProfile {

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @NotBlank
    @Size(min = 11, max = 11)
    String phoneNo;

    @NotBlank
    @Size(min = 6, max = 40)
    private String currentPassword;

    @NotBlank
    @Size(min = 6, max = 40)
    private String newPassword;
}
