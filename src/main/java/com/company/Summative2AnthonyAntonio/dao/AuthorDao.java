package com.company.Summative2AnthonyAntonio.dao;

import com.company.Summative2AnthonyAntonio.dto.Author;

import java.util.List;

public interface AuthorDao {

    Author getAuthor (int id);
    List<Author> getAllAuthors();
    Author addAuthor(Author author);
    void updateAuthor(Author author);
    void deleteAuthor(int id);
    List<Author> getAllAuthorsFirstName(String firstname);

}
