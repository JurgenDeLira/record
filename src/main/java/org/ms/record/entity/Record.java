package org.ms.record.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Record extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @NotBlank
    @Size(min = 2, max = 100)
    private String artist;

    @NotNull
    @Size(max = 4)
    private Long year;

    @Size(min= 2, max = 50)
    private String genre;

    @NotBlank
    @Pattern(regexp = "Vinyl|CD|Digital|Cassette|8-track", message = "Invalid format. Allowed formats are: Vinyl, CD, Digital, Cassette, 8-track.")
    private String format;

    //Constructor

    public Record(Long id, String name, String artist, Long year, String genre, String format) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.year = year;
        this.genre = genre;
        this.format = format;
    }

    // Getters and Setters

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getArtist() {return artist;}
    public void setArtist(String artist) {this.artist = artist;}

    public Long getYear() {return year;}
    public void setYear(Long year) {this.year = year;}

    public String getGenre() {return genre;}
    public void setGenre(String genre) {this.genre = genre;}

    public String getFormat() {return format;}
    public void setFormat(String format) {this.format = format;}

}