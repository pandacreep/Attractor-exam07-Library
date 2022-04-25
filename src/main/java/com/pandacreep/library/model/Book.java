package com.pandacreep.library.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "books")
@Data
@NoArgsConstructor(force = true)
public class Book {
    @Id
    private String id;
    private String title;
    private String author;
    private Image image;
    private String imageString;
    private int year;
    private String description;
    private LocalDate dateAdd;
    private boolean available;
    private String category;

    public Book(String id, String title, String author, Image image, int year, String description, LocalDate dateAdd) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.image = image;
        this.imageString = null;
        this.year = year;
        this.description = description;
        this.dateAdd = dateAdd;
        this.available = true;
    }

    public Book(String id, String title, String author, int year, String description, LocalDate dateAdd, String category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.image = null;
        this.imageString = null;
        this.year = year;
        this.description = description;
        this.dateAdd = dateAdd;
        this.available = true;
        this.category = category;
    }

    public Book(String id, String title, String author, String imageString, int year, String description, LocalDate dateAdd) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.image = null;
        this.imageString = imageString;
        this.year = year;
        this.description = description;
        this.dateAdd = dateAdd;
        this.available = true;
    }
}
