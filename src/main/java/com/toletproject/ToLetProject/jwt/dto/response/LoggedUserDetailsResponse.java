package com.toletproject.ToLetProject.jwt.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class LoggedUserDetailsResponse {

    private String userName;

    private List<String> userRole;

    private Boolean isAuthenticated;
}