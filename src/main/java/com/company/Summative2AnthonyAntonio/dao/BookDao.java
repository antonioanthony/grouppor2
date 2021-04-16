package com.company.Summative2AnthonyAntonio.dao;


import com.company.Summative2AnthonyAntonio.dto.Book;

import java.util.List;

public interface BookDao {

    Book getBook(int id);
    List<Book> getAllBooks();
    Book addBook(Book book);
    List<Book> getBooksByAuthor(int authorId);
    void updateBook (Book book);
    void deleteBook(int id);
    List<Book> getAllBooksByAuthor(int authorId);


}
