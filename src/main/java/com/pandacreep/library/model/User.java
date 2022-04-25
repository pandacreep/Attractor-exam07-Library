package com.pandacreep.library.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
public class User {
    @Id
    private String email;
    private String nameFirst;
    private String nameLast;
    private String phone;
    private boolean isLogged;
    private List<Book> books = new ArrayList<>();

    public User(String email, String nameFirst, String nameLast, String phone, boolean isLogged) {
        this.email = email;
        this.nameFirst = nameFirst;
        this.nameLast = nameLast;
        this.phone = phone;
        this.isLogged = isLogged;
    }
}
