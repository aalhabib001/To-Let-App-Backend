package com.toletproject.ToLetProject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TempDTO {
    String id;
    String name;
    String nameLocal;
    String parentId;
    String displayName;
}
