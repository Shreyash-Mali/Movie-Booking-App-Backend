package com.example.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TheaterDTO {
    private Long id;
    private String name;
    private String location;
    private Integer theaterCapacity;
    private String theaterScreenType;

}
