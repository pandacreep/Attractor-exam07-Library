package com.pandacreep.library.service;

import com.pandacreep.library.model.Book;
import com.pandacreep.library.model.Image;
import com.pandacreep.library.model.User;
import com.pandacreep.library.repo.BooksRepository;
import com.pandacreep.library.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@Service
@AllArgsConstructor
public class BooksService {
    private final BooksRepository booksRepository;
    private final UserRepository userRepository;

    public int findCount() {
        return booksRepository.findAll().size();
    }
    public List<Book> findAll(Pageable pageable) {

        var books = booksRepository.findAll(pageable).toList();
        for (Book book : books) {
            if (book.getImage() != null)
                book.setImageString(Base64.getEncoder().encodeToString(book.getImage().getImage().getData()));
        }
        return books;
    }

    public void add(String name, String author, int year, String description, MultipartFile file, String category) {
        Book book = new Book(UUID.randomUUID().toString(),
                name, author, year, description, LocalDate.now(), category);
        if (file != null) {
            Image image = addImage(file);
            book.setImage(image);
            book.setImageString(Base64.getEncoder()
                    .encodeToString(book.getImage().getImage().getData()));
        }
        booksRepository.save(book);
    }

    public Image addImage(MultipartFile file) {
        byte[] data = new byte[0];
        try {
            data = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (data.length == 0) {
            return null;
        }

        Binary bData = new Binary(data);
        Image image = Image.builder().image(bData).build();

        return image;
    }

    public Book findById(String id) {
        Optional<Book> book = booksRepository.findById(id);
        if (book.isPresent())
            return book.get();
        return null;
    }

    public Map<String, User> findTaken() {
        Map<String, User> takenBooks = new HashMap<>();
        List<User> users = userRepository.findAll();
        for (User user : users) {
            List<Book> books = user.getBooks();
            for (Book book : books) {
                takenBooks.put(book.getTitle(), user);
            }
        }
        return takenBooks;
    }
}
