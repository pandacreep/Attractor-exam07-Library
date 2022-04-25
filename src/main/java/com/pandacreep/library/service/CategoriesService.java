package com.pandacreep.library.service;

import com.pandacreep.library.model.Category;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class CategoriesService {
    public List<String> takeCategories() {
        var categories = Stream.of(categories())
                .map(c -> c.getName())
                .collect(Collectors.toList());
        return categories;
    }

    private Category[] categories() {
        return new Category[]{
                new Category("Fiction"),
                new Category("Children literature"),
                new Category("School books"),
                new Category("Science")
        };
    }
}
