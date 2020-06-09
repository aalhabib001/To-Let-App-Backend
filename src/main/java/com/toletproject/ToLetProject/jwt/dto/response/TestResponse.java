package com.toletproject.ToLetProject.jwt.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class TestResponse {
    String username;
    String email;
    String name;
    String phoneNo;
    Set<String> role;
}
