package com.pandacreep.library.controller;

import com.pandacreep.library.model.User;
import com.pandacreep.library.service.BooksService;
import com.pandacreep.library.service.CategoriesService;
import com.pandacreep.library.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@AllArgsConstructor
public class BooksController {
    private final BooksService booksService;
    private final UserService userService;
    private final CategoriesService categoriesService;

    @GetMapping("/")
    public String showMain(@RequestParam(defaultValue = "1") int page, Model model) {
        int numberOfRecords = booksService.findCount();
        int pageSize = 4;
        int numberOfPages = numberOfRecords / pageSize;
        if (numberOfRecords % pageSize != 0) {
            numberOfPages++;
        }
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("dateAdd").descending());
        model.addAttribute("books", booksService.findAll(pageable));
        model.addAttribute("page", String.valueOf(page));
        if (page < numberOfPages) {
            model.addAttribute("linkNext", "?page=" + (page + 1));
        }
        if (page > 1) {
            model.addAttribute("linkPrevious", "?page=" + (page - 1));
        }
        return "index";
    }

    @GetMapping("/add")
    public String addBook(Model model) {
        model.addAttribute("categories", categoriesService.takeCategories());
        return "books-add";
    }

    @PostMapping("/addBook")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String add(@RequestParam String name,
                      @RequestParam String author,
                      @RequestParam int year,
                      @RequestParam String description,
                      @RequestParam MultipartFile file,
                      @RequestParam String category) {
        booksService.add(name, author, year, description, file, category);
        return "redirect:/";
    }

    @PostMapping("/getBook")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String getBook(@RequestParam String email,
                          @RequestParam String bookId,
                          Model model) {
        model.addAttribute("info", userService.getBook(email, bookId));
        return "info";
    }

    @GetMapping("/books/{id}")
    public String showDetails(@PathVariable String id, Model model) {
        model.addAttribute("book", booksService.findById(id));
        return "books-details";
    }

    @GetMapping("/get/{id}")
    public String getBook(@PathVariable String id, Model model) {
        model.addAttribute("book", booksService.findById(id));
        return "books-get";
    }

    @GetMapping("/return/{id}")
    public String returnBook(@PathVariable String id, Model model) {
        model.addAttribute("info", userService.returnBook(id));
        return "info";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("users", userService.findAll());
        return "login";
    }

    @GetMapping("/taken")
    public String showTaken(Model model) {
        model.addAttribute("books", booksService.findTaken());
        return "books-taken";
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String login(@RequestParam String email) {
        return userService.login(email);
    }

    @GetMapping("/profile")
    public String showProfile(Model model) {
        User loggedUser = userService.getLoggedUserOrNull();
        if (loggedUser == null) {
            model.addAttribute("users", userService.findAll());
            return "/login";
        }
        model.addAttribute("user", loggedUser);
        return "/profile";
    }

    @PostMapping("/registerUser")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String register(@RequestParam String email,
                           @RequestParam String nameFirst,
                           @RequestParam String nameLast,
                           @RequestParam String phone) {
        User register = userService.register(email, nameFirst, nameLast, phone);
        return "redirect:/profile";
    }
}
