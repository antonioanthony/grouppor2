package com.company.Summative2AnthonyAntonio.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Book {


    private int bookId;
    private String isbn;
    private LocalDate publicDate;
    private int authorId;
    private String title;
    private int publisherId;
    private BigDecimal price;
    private int id;

    //getters and setters


    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getPublicDate() {
        return publicDate;
    }

    public void setPublicDate(LocalDate publicDate) {
        this.publicDate = publicDate;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //equality


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return getBookId() == book.getBookId() && getAuthorId() == book.getAuthorId() && getPublisherId() == book.getPublisherId() && getId() == book.getId() && Objects.equals(getIsbn(), book.getIsbn()) && Objects.equals(getPublicDate(), book.getPublicDate()) && Objects.equals(getTitle(), book.getTitle()) && Objects.equals(getPrice(), book.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBookId(), getIsbn(), getPublicDate(), getAuthorId(), getTitle(), getPublisherId(), getPrice(), getId());
    }
}


