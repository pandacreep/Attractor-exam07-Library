package com.pandacreep.library.service;

import com.pandacreep.library.model.Book;
import com.pandacreep.library.model.User;
import com.pandacreep.library.repo.BooksRepository;
import com.pandacreep.library.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BooksRepository booksRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public String login(String email) {
        userRepository.findAll()
                .forEach(user -> {
                    user.setLogged(false);
                    userRepository.save(user);
                });
        User user = userRepository.findById(email).get();
        user.setLogged(true);
        userRepository.save(user);
        return "redirect:/profile";
    }

    public User getLoggedUserOrNull() {
        for (User user : userRepository.findAll()) {
            if (user.isLogged()) return user;
        }
        return null;
    }

    public User register(String email, String nameFirst, String nameLast, String phone) {
        Optional<User> userOpt = userRepository.findById(email);
        if (userOpt.isPresent())
            return null;
        userRepository.findAll()
                .forEach(user -> {
                    user.setLogged(false);
                    userRepository.save(user);
                });
        User user = new User(email, nameFirst, nameLast, phone, true);
        userRepository.save(user);
        return user;
    }

    public String getBook(String email, String bookId) {
        var userOpt = userRepository.findById(email);
        if (userOpt.isEmpty())
            return "No such email was found. You are you new, you can register";
        User user = userOpt.get();
        var bookOpt = booksRepository.findById(bookId);
        if (bookOpt.isEmpty())
            return "No such book was found";
        if (user.getBooks().size() >= 3)
            return "You cannot take more than tree books";
        Book book = bookOpt.get();
        user.getBooks().add(book);
        book.setAvailable(false);
        booksRepository.save(book);
        userRepository.save(user);
        return "Book was given to you";
    }

    public String returnBook(String bookId) {
        User user = getLoggedUserOrNull();
        var bookOpt = booksRepository.findById(bookId);
        if (bookOpt.isEmpty())
            return "No such book was found";
        Book book = bookOpt.get();
        book.setAvailable(true);
        boolean remove = user.getBooks().remove(book);
        List<Book> books = user.getBooks();
        int index = 0;
        for (int i = 0; i < books.size(); i++) {
            if(books.get(i).getId() == bookId) index = i;
        }
        System.out.println(book);
        books.remove(index);
        books.forEach(System.out::println);
        booksRepository.save(book);
        userRepository.save(user);
        return "Book was returned to the library";
    }
}
