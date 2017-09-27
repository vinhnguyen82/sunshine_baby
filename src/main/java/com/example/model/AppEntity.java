package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class AppEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int spaceId;

    private String name;

    private int diskAlloc;

    private int memoryAlloc;
}
