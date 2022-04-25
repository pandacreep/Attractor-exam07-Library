package com.pandacreep.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Data
public class Category {
    private String id = UUID.randomUUID().toString();
    private String name;

    public Category(String name) {
        this.name = name;
    }
}
