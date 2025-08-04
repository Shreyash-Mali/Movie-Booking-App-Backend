package com.example.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class TheaterDTO {
    private Long id;
    private String name;
    private String location;
    private Integer theaterCapacity;
    private String theaterScreenType;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getTheaterCapacity() {
        return theaterCapacity;
    }

    public void setTheaterCapacity(Integer theaterCapacity) {
        this.theaterCapacity = theaterCapacity;
    }

    public String getTheaterScreenType() {
        return theaterScreenType;
    }

    public void setTheaterScreenType(String theaterScreenType) {
        this.theaterScreenType = theaterScreenType;
    }
    public TheaterDTO(Long id, String name, String location, Integer theaterCapacity, String theaterScreenType) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.theaterCapacity = theaterCapacity;
        this.theaterScreenType = theaterScreenType;
    }
    public TheaterDTO() {
    }
}
