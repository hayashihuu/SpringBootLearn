package com.syun.springboottest03;

import java.io.Serializable;

public class Book implements Serializable {
    private String isbn;
    private String title;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Book() {

    }

    public Book(String isbn, String title) {

        this.isbn = isbn;
        this.title = title;
    }
}
