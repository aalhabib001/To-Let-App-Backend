package com.toletproject.ToLetProject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@Getter
@Setter

@Entity
@Table
public class AreaNames {
    @Id
    String areaName;
}
