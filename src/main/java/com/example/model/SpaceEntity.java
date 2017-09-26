package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class SpaceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    private String name;
    private int disk;
    private int memory;
}
