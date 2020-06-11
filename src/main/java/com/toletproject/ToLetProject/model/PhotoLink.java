package com.toletproject.ToLetProject.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter

@Entity
@Table
public class PhotoLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String photoLink;
}
