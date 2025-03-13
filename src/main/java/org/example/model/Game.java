package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Game {
    private int id;
    private String name;
    private LocalDate releaseDate;
    private double rating;
    private double cost;
    private String description;
    private String type;
    private LocalDate creationDate;
}